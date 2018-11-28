package com.dragleo.ms.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dragleo.ms.goods.model.GoodsVo;
import com.dragleo.ms.goods.service.IGoodsService;
import com.dragleo.ms.order.model.MiaoshaOrder;
import com.dragleo.ms.order.model.MiaoshaUser;
import com.dragleo.ms.order.service.IMiaoshaService;
import com.dragleo.ms.order.service.IOrderService;
import com.dragleo.ms.redis.RedisHelper;
import com.dragleo.ms.user.model.UserVO;

@Service
public class MQReceiver {

		private static Logger log = LoggerFactory.getLogger(MQReceiver.class);
		
		@Autowired
		IMiaoshaService miaoshaService;
		@Autowired
		IGoodsService goodsService;
		@Autowired
		IOrderService orderService;
		@Autowired
		RedisHelper helper;
	
//		@RabbitListener(queues=MQConfig.QUEUE)
//		public void receive(String message) { 
//			log.info("receive message:"+message);
//		}
		
		@RabbitListener(queues=MQConfig.TOPIC_QUEUE1)
		public void receiveTopic1(String message) {
			log.info(" topic  queue1 message:"+message);
		}
		
		@RabbitListener(queues=MQConfig.TOPIC_QUEUE2)
		public void receiveTopic2(String message) {
			log.info(" topic  queue2 message:"+message);
		}
		
		@RabbitListener(queues=MQConfig.HEADER_QUEUE) 
		public void receiveHeader(byte[] message){
			log.info(" header  queue message:"+new String(message));
		}
		
		
		
		@RabbitListener(queues=MQConfig.MIAOSHA_QUEUE)
		public void receive(String message) {
			log.info("receive message:"+message);
			MiaoshaMessage mm  = helper.string2Bean(message, MiaoshaMessage.class);
			UserVO user = mm.getUser();
			long goodsId = mm.getGoodsId();
			
			GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
	    	int stock = goods.getStockCount();
	    	if(stock <= 0) {
	    		return;
	    	}
	    	//判断是否已经秒杀到了
	    	MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
	    	if(order != null) {
	    		return;
	    	}
	    	//减库存 下订单 写入秒杀订单
	    	miaoshaService.miaosha(user, goods);
		}
		
}
