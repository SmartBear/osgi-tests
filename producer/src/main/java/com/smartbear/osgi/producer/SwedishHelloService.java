package com.smartbear.osgi.producer;

import com.smartbear.osgi.HelloService;

public class SwedishHelloService implements HelloService
{
	@Override
	public String sayHi()
	{
		return "Hej världen!";
	}
}
