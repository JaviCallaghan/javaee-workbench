package es.indra.test.greet.test;

import java.util.Locale;

import org.junit.Test;

import es.jc.helloworld.Greeter;
import es.jc.helloworld.GreeterImpl;

public class TestGreeter {

	@Test
	public void testGreet() {
		Greeter greeter = new GreeterImpl();
		System.out.println(greeter.greet("Javi", new Locale("es")));
		System.out.println(greeter.greet("Javi", new Locale("en")));
		System.out.println(greeter.greet("Javi", new Locale("fr")));
		System.out.println(greeter.greet("Javi", new Locale("xx")));
	}

}
