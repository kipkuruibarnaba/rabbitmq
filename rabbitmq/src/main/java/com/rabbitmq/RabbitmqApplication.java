package com.rabbitmq;

import com.rabbitmq.fanoutexchange.FanoutExchange;
import com.rabbitmq.headersexchange.HeadersExchange;
import com.rabbitmq.topicexchange.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class RabbitmqApplication {

	public static void main(String[] args) throws IOException, TimeoutException {

		SpringApplication.run(RabbitmqApplication.class, args);

//		System.out.println("...........Calling consumer and Publisher.............");
//		Publisher publisher = new Publisher();
//		publisher.publishMessage();
//		System.out.println("Message published = "+ publisher);
//		Consumer consumer = new Consumer();
//		consumer.consumeMessage();
//		System.out.println("Message consumed = "+ consumer.toString());
//		RealUserRequest req = new RealUserRequest();
//		req.userRequest();
//		ConsumeRealUserRequest real = new ConsumeRealUserRequest();
//		real.consumeMessage();
//		DirectExchange directExchange = new DirectExchange();
//		directExchange.direct();
//		DirectConsumer directConsumer = new DirectConsumer();
//		directConsumer.consumeMessage();
//		FanoutExchange fanoutExchange = new FanoutExchange();
//		fanoutExchange.publishTofanout();
//		fanoutExchange.consumeFanoutMessage();
//		TopicExchange topicExchange = new TopicExchange();
//		topicExchange.publishMessage();
//		topicExchange.consumeTopicMessage();
//		HeadersExchange headersExchange = new HeadersExchange();
//		headersExchange.publishMessage();
//		headersExchange.consumeHeadersMessage();

	}



}
