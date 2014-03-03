package com.smartbear.osgi.tests.hello;

import com.smartbear.osgi.HelloService;
import com.smartbear.osgi.tests.EnvironmentTest;
import org.junit.Test;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HelloServiceTest extends EnvironmentTest
{
	@Inject
	HelloService helloService;

	@Test
	public void helloServiceSpeaksSwedish()
	{
		assertThat( helloService.sayHi(), is( "Hej världen!" ) );
	}


}
