package com.dragleo.ms.order.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dragleo.ms.goods.model.GoodsVo;
import com.dragleo.ms.order.dao.IOrderDao;
import com.dragleo.ms.order.model.MiaoshaOrder;
import com.dragleo.ms.order.model.OrderInfo;
import com.dragleo.ms.order.service.IOrderService;
import com.dragleo.ms.user.model.UserVO;
@Service
public class OrderServiceImpl implements IOrderService{

	@Autowired
	IOrderDao orderDao;


	@Override
	public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long id, long goodsId) {
		return orderDao.getMiaoshaOrderByUserIdGoodsId(id, goodsId);// TODO Auto-generated method stub
	}

	@Override
	public OrderInfo createOrder(UserVO user, GoodsVo goods) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCreateDate(new Date());
		orderInfo.setDeliveryAddrId(0L);
		orderInfo.setGoodsCount(1);
		orderInfo.setGoodsId(goods.getId());
		orderInfo.setGoodsName(goods.getGoodsName());
		orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
		orderInfo.setStatus(0);
		orderInfo.setUserId(user.getId());
		long orderId = orderDao.insert(orderInfo);
		MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
		miaoshaOrder.setGoodsId(goods.getId());
		miaoshaOrder.setOrderId(orderId);
		miaoshaOrder.setUserId(user.getId());
		orderDao.insertMiaoshaOrder(miaoshaOrder);
		return orderInfo;
	}
}
