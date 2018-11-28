package com.dragleo.ms.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dragleo.ms.goods.model.GoodsVo;
import com.dragleo.ms.goods.service.IGoodsService;
import com.dragleo.ms.order.model.OrderInfo;
import com.dragleo.ms.order.service.IMiaoshaService;
import com.dragleo.ms.order.service.IOrderService;
import com.dragleo.ms.user.model.UserVO;

@Service
public class MiaoShaoServiceImpl implements IMiaoshaService{

	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IOrderService orderService ;
	@Override
	public OrderInfo miaosha(UserVO user, GoodsVo goods) {
		//减库存 下订单 写入秒杀订单
		goodsService.reduceStock(goods);
		//order_info maiosha_order
		return orderService.createOrder(user, goods);
	}

}
