package com.smartbear.osgi.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import javax.inject.Inject;
import java.util.Hashtable;

import static com.smartbear.osgi.tests.OsgiTestUtils.assertAllBundlesActive;
import static com.smartbear.osgi.tests.OsgiTestUtils.springDmBundles;
import static org.ops4j.pax.exam.CoreOptions.*;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class EnvironmentTest
{

	@Inject
	BundleContext context;

	@Configuration
	public Option[] config()
	{
		return CoreOptions.options(
				systemPackages(
						"com.sun.crypto.provider",
						"com.sun.net.ssl",
						"com.sun.net.ssl.internal.ssl",
						"org.w3c.dom.traversal",
						"javax.transaction.xa",
						"sun.io",
						"sun.misc" ),
				mavenBundle( "osgi-tests", "test-api" ).versionAsInProject(),
				mavenBundle( "osgi-tests", "producer" ).versionAsInProject(),
				mavenBundle( "osgi-tests", "consumer" ).versionAsInProject(),
				springDmBundles(),
				junitBundles()
		);
	}

	protected <T> void publishService( Class<T> serviceClass, T service, Hashtable<String, String> properties )
	{
		context.registerService( serviceClass, service, properties );
	}

	protected <T> T getService( Class<T> serviceClass )
	{
		ServiceReference ref = context.getServiceReference( serviceClass );
		return ( T )context.getService( ref );
	}

	@Test
	public void allBundlesStart()
	{
		assertAllBundlesActive( context );
	}

}
