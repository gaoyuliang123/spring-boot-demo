package com.example.demo.batchconsume;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * Producer 发布消息
 */
public class Producer {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("producerGroup");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        for (int i = 0; i < 50; i++) {
            try {
                Message message = new Message("TopicTest",
                        "TagA",
                        "key" + i,
                        ("Hello RocketMQ" + i).getBytes());
                SendResult sendResult = producer.send(message);
                System.out.println(sendResult);
            } catch (Exception e) {
               e.printStackTrace();
               Thread.sleep(1000);
            }
        }
        producer.shutdown();
    }
}
