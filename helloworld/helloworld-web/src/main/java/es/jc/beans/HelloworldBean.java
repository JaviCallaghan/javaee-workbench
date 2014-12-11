package es.jc.beans;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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

	/* JSF navigation method (from index.xhtml) */
	public String greet() {
		log.info("Greeting name = " + name);
		result = "Hello world " + name + "!!";
		return "helloworld";
	}

	/* JSF navigation method (from hello.xhtml) */
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
