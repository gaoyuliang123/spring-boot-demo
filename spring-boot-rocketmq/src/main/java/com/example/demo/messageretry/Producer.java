package com.example.demo.messageretry;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * Producer 发布消息
 */
public class Producer {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("producerGroup");
        producer.setNamesrvAddr("127.0.0.1:9876");
        /**Producer端重试*/
        //设置重试的次数
        producer.setRetryTimesWhenSendFailed(3);
        producer.start();
        for (int i = 0; i < 6; i++) {
            try {
                Message message = new Message("TopicTest",
                        "TagA",
                        "key" + i,
                        ("Hello RocketMQ" + i).getBytes());
                //SendResult sendResult = producer.send(message, 1000);
                //System.out.println(sendResult);
                producer.send(message, new SendCallback() {
                    //成功的回调函数
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println("成功了" + sendResult.getSendStatus());
                    }
                    //出现异常的回调函数
                    @Override
                    public void onException(Throwable e) {
                        System.out.println("失败了" + e.getMessage());
                    }
                }, 1000);
            } catch (Exception e) {
               e.printStackTrace();
               Thread.sleep(1000);
            }
        }
        producer.shutdown();
    }
}
