package com.astraltear.cmdlinerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class App implements CommandLineRunner {

	@Autowired
	CustomerRepository customerRepository;
//	CustomerService customerService;
	
	
	@Override
	public void run(String... strings) throws Exception {
		
/*		customerService.save(new Customer(1,"nobita","nobi"));
		customerService.save(new Customer(2,"nobita2","nobi2"));
		customerService.save(new Customer(3,"nobita3","nobi3"));
		customerService.save(new Customer(4,"nobita4","nobi4"));
		
		
		customerService.findAll().forEach(System.out::println);*/
		
		Customer created = customerRepository.save(new Customer(null,"ARA","KO"));
		System.out.println(created +"is created!!!");
		
		customerRepository.findAll().forEach(System.out::println);
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
