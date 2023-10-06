package com.rabbitmq.topicexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicExchange {
    public void publishMessage() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String message = "This is message is for mobile and AC Queue";

        channel.basicPublish("Topic-Exchange","tv.mobile.ac",null, message.getBytes());
//        channel.basicPublish("Topic-Exchange","*.tv.*",null, message.getBytes());
//        channel.basicPublish("Topic-Exchange","#.ac",null, message.getBytes());
        channel.close();
        connection.close();
    }
    public void consumeTopicMessage() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery)->{
            String message = new String(delivery.getBody());
            System.out.println("Message Received = "+message);
        };
        channel.basicConsume("Mobile",true,deliverCallback,consumerTag ->{});
        channel.basicConsume("TV",true,deliverCallback,consumerTag ->{});
        channel.basicConsume("AC",true,deliverCallback,consumerTag ->{});
    }
}
