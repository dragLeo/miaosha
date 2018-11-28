package com.dragleo.ms.rabbitmq;

import com.dragleo.ms.user.model.UserVO;

public class MiaoshaMessage {
	private UserVO user;
	private long goodsId;
	
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
	public long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
}
