package es.jc.beans;

import java.io.Serializable;
import java.util.Locale;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import es.jc.helloworld.Greeter;
import es.jc.helloworld.Helloworld;

/**
 * ManagedBean for helloworld-web basic application.
 * 
 * @author JaviCallaghan
 */
@ManagedBean
@SessionScoped
public class HelloworldBean implements Serializable {

	private static final long serialVersionUID = -3002431810375279862L;
	private static final Logger log = Logger.getLogger("HelloworldBean");

	private String name;
	private String lang;

	private String result;

	@Inject
	@Helloworld
	private Greeter greeter;

	/**
	 * Default constructor.
	 */
	public HelloworldBean() {
	}

	@PostConstruct
	public void init() {
		log.info("HelloworldBean - Initializing bean...");
		name = "<Input name>";
		lang = "en";
		result = "";
	}

	/**
	 * Method to perform a greeting to be displayed after navigation (to helloworld.xhtml for default JSF navigation).
	 * 
	 * @return navigation action
	 */
	public String greet() {
		log.info("Greeting name = " + name);
		result = greeter.greet(name, new Locale(lang));
		return "helloworld";
	}

	/**
	 * Method to reset session status to be displayed after navigation (to index.xhtml for default JSF navigation).
	 * 
	 * @return navigation action
	 */
	public String back() {
		result = "";
		init();
		return "index";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getResult() {
		return result;
	}

	public void setGreeter(Greeter greeter) {
		this.greeter = greeter;
	}

}
