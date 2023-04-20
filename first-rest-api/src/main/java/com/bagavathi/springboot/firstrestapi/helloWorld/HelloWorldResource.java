package com.bagavathi.springboot.firstrestapi.helloWorld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {
	@RequestMapping("/hello-world")
	public String helloWorld()
	{
		return "Hello world!!!";
	}
	@RequestMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("Hello world using bean!!!");
	}
	@RequestMapping("/hello-world-bean-path/{username}")
	public String helloWorldBeanPath(@PathVariable String username)
	{
		return "Hello World "+username+" ,welcome to the world of programming";
	}
}
