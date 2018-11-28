package com.dragleo.ms.order.service;

import com.dragleo.ms.goods.model.GoodsVo;
import com.dragleo.ms.order.model.OrderInfo;
import com.dragleo.ms.user.model.UserVO;

public interface IMiaoshaService {

	OrderInfo miaosha(UserVO user, GoodsVo goods);

}
