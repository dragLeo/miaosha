package com.dragleo.ms.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dragleo.ms.goods.model.GoodsVo;
import com.dragleo.ms.goods.model.MiaoshaGoods;

@Mapper
public interface IGoodsDao {

	@Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from ms_goods mg left join goods g on mg.goods_id = g.id")
	public List<GoodsVo> queryAllGoods();

	
	@Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from ms_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
	public GoodsVo getGoodsVoByGoodsId(@Param("goodsId")long goodsId);


	@Update("update ms_goods set stock_count = stock_count - 1 where goods_id = #{goodsId}")
	public void reduceStock(MiaoshaGoods g);

}
