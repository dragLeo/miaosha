package com.dragleo.ms.goods.service;

import java.util.List;

import com.dragleo.ms.goods.model.GoodsVo;

public interface IGoodsService {

	public List<GoodsVo> listGoodsVo() ;

	public GoodsVo getGoodsVoByGoodsId(long goodsId);

	public void reduceStock(GoodsVo goods);

}
