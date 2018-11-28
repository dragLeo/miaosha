package com.dragleo.ms.order.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dragleo.ms.common.result.AjaxResult;
import com.dragleo.ms.common.result.CodeMsg;
import com.dragleo.ms.goods.model.GoodsKey;
import com.dragleo.ms.goods.model.GoodsVo;
import com.dragleo.ms.goods.service.IGoodsService;
import com.dragleo.ms.order.model.MiaoshaOrder;
import com.dragleo.ms.order.service.IMiaoshaService;
import com.dragleo.ms.order.service.IOrderService;
import com.dragleo.ms.rabbitmq.MQSender;
import com.dragleo.ms.rabbitmq.MiaoshaMessage;
import com.dragleo.ms.redis.RedisHelper;
import com.dragleo.ms.user.model.UserVO;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean{

	@Autowired
	RedisHelper redisHelper;
	
	@Autowired
	IGoodsService goodsService;
	
	@Autowired
	IOrderService orderService;
	@Autowired
	MQSender mqSender;
	
	@Autowired
	IMiaoshaService miaoshaService;
	private HashMap<Long, Boolean> localOverMap =  new HashMap<Long, Boolean>();
	
	/**
	 * QPS:1306
	 * 5000 * 10
	 * */
    @RequestMapping("/do_miaosha")
    public AjaxResult<Integer> list(Model model,UserVO user,
    		@RequestParam("goodsId")long goodsId) {
    	if(user == null) {
    		return AjaxResult.error(CodeMsg.SESSION_ERROR);
    	}
    	//内存标记，减少redis访问
    	boolean over = localOverMap.get(goodsId);
    	if(over) {
    		return AjaxResult.error(CodeMsg.MIAO_SHA_OVER);
    	}
    	//预减库存
    	long stock = redisHelper.decr(GoodsKey.getMiaoshaGoodsStock, ""+goodsId);//10
    	if(stock < 0) {
    		 localOverMap.put(goodsId, true);
    		return AjaxResult.error(CodeMsg.MIAO_SHA_OVER);
    	}
    	//判断是否已经秒杀到了
    	MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
    	if(order != null) {
    		return AjaxResult.error(CodeMsg.REPEATE_MIAOSHA);
    	}
    	//入队
    	MiaoshaMessage mm = new MiaoshaMessage();
    	mm.setUser(user);
    	mm.setGoodsId(goodsId);
    	mqSender.sendMiaoshaMessage(mm);
    	return AjaxResult.success(0);//排队中
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		List<GoodsVo> goodsList = goodsService.listGoodsVo();
		if(goodsList == null) {
			return;
		}
		for(GoodsVo goods : goodsList) {
			redisHelper.setKey(GoodsKey.getMiaoshaGoodsStock, ""+goods.getId(), goods.getStockCount());
			localOverMap.put(goods.getId(), false);
		}
		
	}
}
