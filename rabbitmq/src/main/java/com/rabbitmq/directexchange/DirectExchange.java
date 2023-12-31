package com.rabbitmq.directexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectExchange {
    public void directMessage() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String message = "This is tv";

        channel.basicPublish("Direct-Exchange","tv",null, message.getBytes());
        channel.close();
        connection.close();
    }

    public void consumeMessage() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery)->{
            String message = new String(delivery.getBody());
            System.out.println("Message Received = "+message);
        };
//        channel.basicConsume("Mobile",true,deliverCallback,consumerTag ->{});
        channel.basicConsume("TV",true,deliverCallback,consumerTag ->{});
//        channel.basicConsume("AC",true,deliverCallback,consumerTag ->{});
    }
}
