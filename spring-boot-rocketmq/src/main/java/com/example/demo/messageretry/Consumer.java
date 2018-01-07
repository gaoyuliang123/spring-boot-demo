package com.example.demo.messageretry;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * Consumer 订阅消息
 */
public class Consumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumerGroup");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setConsumeMessageBatchMaxSize(10);
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
         * 如果非第一次启动，那么按照上次消费的位置继续消费 ,（消费顺序消息的时候设置）
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("TopicTest", "*");
        /**消费模式*/
        //rocketMQ默认是集群消费,我们可以通过在Consumer来支持广播消费
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.registerMessageListener((List<MessageExt> msgs, ConsumeConcurrentlyContext context) -> {
            try {
                //System.out.println("msgs的长度：" + msgs.size());
                System.out.println(Thread.currentThread().getName() + "接收到的消息：" + msgs);
                for (MessageExt msg : msgs) {
                    String message =  new String(msg.getBody(), "utf-8");
                    if ("Hello RocketMQ4".equals(message)) {
                        /**exception的情况，一般重复16次*/
                        System.out.println("======错误=======");
                        int a = 1/0;
                    }
                }

                /**超时的情况，这种情况MQ会无限制的发送给消费端。*/
                // 表示业务处理时间
                //System.out.println("=========开始暂停===============");
                //Thread.sleep(60000);

            } catch (Exception e) {
                e.printStackTrace();
                if (msgs.get(0).getReconsumeTimes() == 3) {
                    //记录日志
                    //TODO
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                //exception的情况，一般重复16次 10s、30s、1分钟、2分钟、3分钟等等
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;

            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        System.out.println("consumer start!");

    }
}
