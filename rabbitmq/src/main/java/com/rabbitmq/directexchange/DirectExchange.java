package com.rabbitmq.directexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectExchange {
    public void direct() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String message = "This is tv";

        channel.basicPublish("Direct-Exchange","tv",null, message.getBytes());
        channel.close();
        connection.close();
    }
}
