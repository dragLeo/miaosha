package com.dragleo.ms.order.service;

import com.dragleo.ms.goods.model.GoodsVo;
import com.dragleo.ms.order.model.MiaoshaOrder;
import com.dragleo.ms.order.model.OrderInfo;
import com.dragleo.ms.user.model.UserVO;

public interface IOrderService {

	MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long id, long goodsId);

	OrderInfo createOrder(UserVO user, GoodsVo goods);

}
