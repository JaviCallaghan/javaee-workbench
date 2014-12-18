package es.jc.beans.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import es.jc.beans.HelloworldBean;

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
	}

	/**
	 * Unit test for {@link HelloworldBean#greet()} with fixed return assertion.
	 */
	@Test
	public void testGreet() {
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
