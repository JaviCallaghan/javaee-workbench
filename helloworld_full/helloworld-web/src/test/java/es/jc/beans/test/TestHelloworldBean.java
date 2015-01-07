package es.jc.beans.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import es.jc.beans.HelloworldBean;
import es.jc.helloworld.GreeterImpl;

/**
 * Test for HelloworldBean.
 * 
 * @author JaviCallaghan
 */
public class TestHelloworldBean {

	/** Class to test. */
	HelloworldBean bean;

	/**
	 * Default constructor initializing test class.
	 */
	public TestHelloworldBean() {
		bean = new HelloworldBean();
		bean.setGreeter(new GreeterImpl());
	}

	/**
	 * Unit test for {@link HelloworldBean#greet()} with fixed return assertion.
	 */
	@Test
	public void testGreet() {
		bean.init();
		assertEquals("helloworld", bean.greet());
	}

	/**
	 * Unit test for {@link HelloworldBean#back()} with fixed return assertion.
	 */
	@Test
	public void testBack() {
		assertEquals("index", bean.back());
	}

}
