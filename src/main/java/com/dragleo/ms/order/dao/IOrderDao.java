package com.dragleo.ms.order.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.dragleo.ms.order.model.MiaoshaOrder;
import com.dragleo.ms.order.model.OrderInfo;

@Mapper
public interface IOrderDao {

	@Select("select * from ms_order where user_id=#{userId} and goods_id=#{goodsId}")
	MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId")long userId, @Param("goodsId")long goodsId);

	@Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, status, create_date)values("
			+ "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice},#{status},#{createDate} )")
	@SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
	long insert(OrderInfo orderInfo);

	
	@Insert("insert into ms_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
	void insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);

}
