package com.rabbitmq.fanoutexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutExchange {
    public void publishTofanout() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String message = "This is message is for mobile and ac";

        channel.basicPublish("Fanout-Exchange","",null, message.getBytes());
        channel.close();
        connection.close();
    }
    public void consumeFanoutMessage() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery)->{
            String message = new String(delivery.getBody());
            System.out.println("Message Received = "+message);
        };
        channel.basicConsume("Mobile",true,deliverCallback,consumerTag ->{});
//        channel.basicConsume("TV",true,deliverCallback,consumerTag ->{});
        channel.basicConsume("AC",true,deliverCallback,consumerTag ->{});
    }
}
