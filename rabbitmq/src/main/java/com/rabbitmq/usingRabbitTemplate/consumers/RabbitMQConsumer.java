package com.rabbitmq.usingRabbitTemplate.consumers;

import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    @RabbitListener(queues = "Mobile")
    public void getMessage(String p){
        JSONObject jObject  = new JSONObject(p);
        System.out.println("Type = : "+ jObject.getClass().getName());
        String name = (String) jObject.get("name");
        System.out.println("Name = "+name);
    }

    
}
