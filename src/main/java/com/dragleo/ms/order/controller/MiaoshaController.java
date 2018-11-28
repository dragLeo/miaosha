package com.dragleo.ms.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dragleo.ms.common.result.CodeMsg;
import com.dragleo.ms.goods.model.GoodsVo;
import com.dragleo.ms.goods.service.IGoodsService;
import com.dragleo.ms.order.model.MiaoshaOrder;
import com.dragleo.ms.order.model.OrderInfo;
import com.dragleo.ms.order.service.IMiaoshaService;
import com.dragleo.ms.order.service.IOrderService;
import com.dragleo.ms.redis.RedisHelper;
import com.dragleo.ms.user.model.UserVO;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

	@Autowired
	RedisHelper redisHelper;
	
	@Autowired
	IGoodsService goodsService;
	
	@Autowired
	IOrderService orderService;
	
	@Autowired
	IMiaoshaService miaoshaService;
	
	/**
	 * QPS:1306
	 * 5000 * 10
	 * */
    @RequestMapping("/do_miaosha")
    public String list(Model model,UserVO user,
    		@RequestParam("goodsId")long goodsId) {
    	model.addAttribute("user", user);
    	if(user == null) {
    		return "login";
    	}
    	//判断库存
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	int stock = goods.getStockCount();
    	if(stock <= 0) {
    		model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
    		return "miaosha_fail";
    	}
    	//判断是否已经秒杀到了
    	MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
    	if(order != null) {
    		model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
    		return "miaosha_fail";
    	}
    	//减库存 下订单 写入秒杀订单
    	OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
    	model.addAttribute("orderInfo", orderInfo);
    	model.addAttribute("goods", goods);
        return "order_detail";
    }
}
