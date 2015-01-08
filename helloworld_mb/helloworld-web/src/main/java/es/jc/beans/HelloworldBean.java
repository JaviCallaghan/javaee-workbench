package es.jc.beans;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.util.Locale;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.management.InstanceNotFoundException;
import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import es.jc.helloworld.Greeter;
import es.jc.helloworld.Helloworld;
import es.jc.mbeans.ApplicationMBeanLifecycleListener;
import es.jc.mbeans.GreeterMBean;
import es.jc.mbeans.GreetingNotification.GREETING_NOTIFTYPE;

/**
 * ManagedBean for helloworld-web basic application.
 * 
 * @author JaviCallaghan
 */
@ManagedBean
@SessionScoped
public class HelloworldBean implements Serializable, NotificationListener {

	private static final long serialVersionUID = -3002431810375279862L;
	private static final Logger log = Logger.getLogger("HelloworldBean");

	private GreeterMBean mBeanProxy;

	private String name;

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
		result = "";
		log.info("HelloworldBean has been instantiated: " + greeter);

		try {
			// Connection to local JVM platform
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			log.info("MBeans server: " + mbs);
			ObjectInstance objectInstance = mbs.getObjectInstance(new ObjectName(ApplicationMBeanLifecycleListener.GREETER_ONAME));
			System.out.println("Greeter MBean: " + objectInstance.getClassName() + " - "
					+ objectInstance.getObjectName());
			mBeanProxy = JMX.newMBeanProxy(mbs, objectInstance.getObjectName(), GreeterMBean.class, true);
			// registering 'this' in MBean notifier and handback with 'this' signature as context info
			mBeanProxy.addNotificationListener(
					this, t -> t.getType().equals(GREETING_NOTIFTYPE.GREETING_THRESHOLD_REACHED.getType()),
					this.hashCode());
		} catch (MalformedObjectNameException | InstanceNotFoundException e) {
			System.out.println("Problems instantiating GreeterMBean");
		}
		log.info("GreeterMBean has been instantiated: " + mBeanProxy);
	}

	/**
	 * Method to perform a greeting to be displayed after navigation (to helloworld.xhtml for default JSF navigation).
	 * 
	 * @return navigation action
	 */
	public String greet() {
		log.info("Greeting name = " + name);
		mBeanProxy.incrementGreetCount();
		result = greeter.greet(name, new Locale(mBeanProxy.getLanguage()));
		return "helloworld";
	}

	/**
	 * Method to reset session status to be displayed after navigation (to index.xhtml for default JSF navigation).
	 * 
	 * @return navigation action
	 */
	public String back() {
		name = "<Input name>";
		result = "";
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

	public void setGreeter(Greeter greeter) {
		this.greeter = greeter;
	}

	@Override
	public void handleNotification(Notification notification, Object handback) {
		// notifications will be filtered by handback checking 'this' signature
		if (handback != null && handback.equals(this.hashCode())) {
			System.out.println(" * Received notification from " + notification.getSource()
					+ " for me (" + handback + "): " + notification.getType() + " - "
					+ notification.getUserData().toString());
		}
	}

}
