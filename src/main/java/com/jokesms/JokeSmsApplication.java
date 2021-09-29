package com.jokesms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JokeSmsApplication {

	String mySms;
	private static final Logger log = LoggerFactory.getLogger(JokeSmsApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(JokeSmsApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Joke joke = restTemplate.getForObject(
					"https://v2.jokeapi.dev/joke/Programming?type=twopart", Joke.class);
			//log.info(quote.toString());
			
			String tempSms = joke.getSetup()+" . . . "+joke.getDelivery();
			mySms = tempSms;
			mySms = mySms + "\nSent by JR";
			System.out.println(mySms);
			
			SmsSender smsSender = new SmsSender(mySms);
			smsSender.sendSms();
			
		};
	}
	

}
