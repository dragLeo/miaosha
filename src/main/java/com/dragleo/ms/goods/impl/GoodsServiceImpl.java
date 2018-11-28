package com.dragleo.ms.goods.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dragleo.ms.goods.dao.IGoodsDao;
import com.dragleo.ms.goods.model.GoodsVo;
import com.dragleo.ms.goods.model.MiaoshaGoods;
import com.dragleo.ms.goods.service.IGoodsService;

@Service
public class GoodsServiceImpl implements IGoodsService{

	@Autowired
	private IGoodsDao goodsDao;
	@Override
	public List<GoodsVo> listGoodsVo() {
		return goodsDao.queryAllGoods();
	}
	
	@Override
	public GoodsVo getGoodsVoByGoodsId(long goodsId) {
		return goodsDao.getGoodsVoByGoodsId(goodsId);
	}

	public void reduceStock(GoodsVo goods) {
		MiaoshaGoods g = new MiaoshaGoods();
		g.setGoodsId(goods.getId());
		goodsDao.reduceStock(g);
	}

}
