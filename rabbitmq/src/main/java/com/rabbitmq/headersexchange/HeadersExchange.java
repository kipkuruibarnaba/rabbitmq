package com.rabbitmq.headersexchange;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class HeadersExchange {
    public void publishMessage() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String message = "This is message is for Mobile and TV Queue";
        Map<String,Object> objectMap = new HashMap<>();
         objectMap.put("item1","mobile");
         objectMap.put("item2","television");

        BasicProperties br = new AMQP.BasicProperties();
         br = ((AMQP.BasicProperties) br).builder().headers(objectMap).build();
        channel.basicPublish("Headers-Exchange","", (AMQP.BasicProperties) br, message.getBytes());
//        channel.basicPublish("Topic-Exchange","*.tv.*",null, message.getBytes());
//        channel.basicPublish("Topic-Exchange","#.ac",null, message.getBytes());
        channel.close();
        connection.close();
    }
    public void consumeHeadersMessage() throws IOException, TimeoutException {
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
