package com.example.demo.transactionmessage;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.message.Message;
/**
 * Producer 发布消息
 */
public class Producer {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionMQProducer producer = new TransactionMQProducer("producerGroup");
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 事务回查最小并发数
        producer.setCheckThreadPoolMinSize(2);
        // 事务回查最大并发数
        producer.setCheckThreadPoolMaxSize(2);
        // 队列数
        producer.setCheckRequestHoldMax(2000);
        TransactionCheckListener transactionCheckListener = new TransactionCheckListenerImpl();
        producer.setTransactionCheckListener(transactionCheckListener);
        producer.start();
        TransactionExecuterImpl transactionExecuter = new TransactionExecuterImpl();

        String arg = "外部参数";
        for (int i = 1; i <= 2; i++) {
            try {
                Message message = new Message("TopicTransactionTest",
                        "Tag" + i,
                        "key" + i,
                        ("Hello RocketMQ" + i).getBytes());
                SendResult sendResult = producer.sendMessageInTransaction(message, transactionExecuter, arg);
                System.out.println(sendResult);
                Thread.sleep(10);
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        for (int i = 0; i < 100000; i++) {
            Thread.sleep(1000);
        }
        producer.shutdown();
    }
}
