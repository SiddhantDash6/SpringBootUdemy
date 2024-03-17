package com.chospital.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class HelloWorld {
	
	@GetMapping("/helloworld")
	public String hello() {
		return "Hello World...";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Sidhanta", "Dash", "Cuttack");
	}

}
