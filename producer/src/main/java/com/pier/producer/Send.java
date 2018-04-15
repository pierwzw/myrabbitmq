package com.pier.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @auther: zhongweiwu
 * @Description:
 * @Date: 2018/4/12
 */
public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        //factory.setUsername("admin");
        //factory.setPassword("admin");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //channel.queueDeclare(queueName, durable, exclusive, autoDelete, argss)
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        int count = 1;
        while(true){
            String message = "Hello World--------------" + count++;
            //basicPublish(exchange, routingKey, mandatory, immediate, props, body)
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            Thread.sleep(1000);
        }

        /*channel.close();
        connection.close();*/
    }
}
