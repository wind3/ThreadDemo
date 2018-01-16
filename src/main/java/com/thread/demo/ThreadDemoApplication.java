package com.thread.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thread.demo.domain.CommonResource;
import com.thread.demo.factory.ProductFactory;
import com.thread.demo.runnable.ConsumeRunnable;
import com.thread.demo.runnable.ProduceRunnable;

@SpringBootApplication
public class ThreadDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreadDemoApplication.class, args);
		ProductFactory factory = new ProductFactory();
		CommonResource resource = new CommonResource(factory);
		
		Thread a1 = new Thread(new ProduceRunnable(resource));
		Thread a2 = new Thread(new ProduceRunnable(resource));
		Thread a3 = new Thread(new ProduceRunnable(resource));
		
		Thread b1 = new Thread(new ConsumeRunnable(resource));
		Thread b2 = new Thread(new ConsumeRunnable(resource));
		Thread b3 = new Thread(new ConsumeRunnable(resource));
		
		a1.start();
		a2.start();
		a3.start();
		
		b1.start();
		b2.start();
		b3.start();
	}
}
