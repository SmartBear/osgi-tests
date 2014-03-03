package com.smartbear.osgi.tests.hello;

import com.smartbear.osgi.HelloService;
import com.smartbear.osgi.tests.EnvironmentTest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReplaceServiceTest extends EnvironmentTest
{

	@Before
	public void replaceHelloService()
	{
		HelloService olaService = new HelloService()
		{
			@Override
			public String sayHi()
			{
				return "Ola mundo!";
			}
		};

		publishService( HelloService.class, olaService, null );

	}

	@Test
	public void replacementServiceIsUsed()
	{
		HelloService olaService = getService( HelloService.class );
		assertThat( olaService.sayHi(), is( "Ola mundo!" ) );
	}

}
