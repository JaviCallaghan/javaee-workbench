package es.jc.helloworld;

import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;

/**
 * Basic implementation of {@link Greeter} for Helloworld.
 * 
 * @author JaviCallaghan
 */
@ApplicationScoped
@Helloworld
public class GreeterImpl implements Greeter {

	/**
	 * Default constructor.
	 */
	public GreeterImpl() {
	}

	/**
	 * {@inheritDoc}<br>
	 * Only locale language will be taken into account for a set of supported values. Otherwise, Esperanto will be
	 * considered as default language.
	 */
	public String greet(final String name, final Locale locale) {
		final String greeting;
		switch (locale.getLanguage()) {
		case "en":
			greeting = "Hello " + name + "!";
			break;
		case "es":
			greeting = "¡Hola " + name + "!";
			break;
		case "fr":
			greeting = "Bonjour " + name + "!";
			break;
		default:
			greeting = "Saluton " + name + "!";
		}
		return greeting;
	}

}
