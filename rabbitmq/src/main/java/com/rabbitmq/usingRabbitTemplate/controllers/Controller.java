package com.rabbitmq.usingRabbitTemplate.controllers;

import com.rabbitmq.usingRabbitTemplate.payloads.Person;
import org.apache.logging.log4j.message.Message;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/api/v1")
public class Controller {
    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    @Autowired
    RabbitTemplate rabbitTemplate;
    @PostMapping()
    public Person createUser(@RequestBody Person person){
        JSONObject json = new JSONObject();
        json.put("id", person.getId());
        json.put("name", person.getName());
//        rabbitTemplate.convertAndSend("Mobile",personDto);
        rabbitTemplate.convertAndSend("Direct-Exchange","mobile",json.toString());
//        rabbitTemplate.convertAndSend("Fanout-Exchange","", person.toString());
//        rabbitTemplate.convertAndSend("Topic-Exchange","tv.mobile.ac", person.toString());
        return person;
    }

    @PostMapping("/test/{name}")
    public String createUser1(@PathVariable("name") String name){
        Person person = new Person(1L,"Barnaba");
        rabbitTemplate.convertAndSend("Mobile", person.toString());
//        rabbitTemplate.convertAndSend("Direct-Exchange","mobile",personDto);
        return "Success";
    }

//    @PostMapping("/test/headers")
//    public String createUserHeader(@RequestBody PersonDto personDto) throws IOException {
//        JSONObject json = new JSONObject();
//        json.put("id",personDto.getId());
//        json.put("name",personDto.getName());
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutput objectOutput = new ObjectOutputStream(byteArrayOutputStream);
//        objectOutput.write(personDto.toString());
//        objectOutput.flush();
//        objectOutput.close();
//
//        byte[] byteMessage = byteArrayOutputStream.toByteArray();
//        byteArrayOutputStream.close();
//
//        Message message = MessageBuilder.withBody(byteMessage)
//                .setHeader("item1","mobile")
//                .setHeader("item2","television")
//                .build();
//
//        rabbitTemplate.convertAndSend("Headers-Exchange","",message);
//        return "Success";
//    }
}
