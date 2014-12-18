package es.jc.beans;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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

	private String result;

	public HelloworldBean() {
	}

	@PostConstruct
	public void init() {
		log.info("HelloworldBean - Initializing bean...");
		name = "<Input name>";
		result = "";
	}

	/**
	 * Method to perform a greeting to be displayed after navigation (to helloworld.xhtml for default JSF navigation).
	 * 
	 * @return navigation action
	 */
	public String greet() {
		log.info("Greeting name = " + name);
		result = "Hello world " + name + "!!";
		return "helloworld";
	}

	/**
	 * Method to reset session status to be displayed after navigation (to index.xhtml for default JSF navigation).
	 * 
	 * @return navigation action
	 */
	public String back() {
		result = "";
		name = "<Input name>";
		return "index";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResult() {
		return result;
	}

}
