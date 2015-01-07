package es.jc.helloworld;

import java.util.Locale;

/**
 * Interface for greeting business logic.
 * 
 * @author JaviCallaghan
 */
public interface Greeter {

	/**
	 * Returns a personalized greeting for given name on the language specified by locale.
	 * 
	 * @param name the name to greet
	 * @param locale the locale to greet into
	 * @return a personalized greeting
	 */
	String greet(final String name, final Locale locale);

}
