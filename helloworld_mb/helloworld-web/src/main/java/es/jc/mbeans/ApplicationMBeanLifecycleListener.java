package es.jc.mbeans;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationMBeanLifecycleListener implements ServletContextListener {

	public final static String GREETER_ONAME = "es.indra.test.mbeans:type=Greeter";
	private MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("...contextInitialized: " + sce.getServletContext().getContextPath());
		if (mbs != null) {
			try {
				final ObjectName oname = new ObjectName(GREETER_ONAME);
				mbs.registerMBean(new Greeter(), oname);
			} catch (MalformedObjectNameException | InstanceAlreadyExistsException | MBeanRegistrationException
					| NotCompliantMBeanException e) {
				System.out.println("Problems registering Greeter bean: "
						+ sce.getServletContext().getServletContextName() + "\n" + e.getMessage());
			}
		} else {
			System.out.println("MBeanServer not initialized");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("...contextDestroyed: " + sce.getServletContext().getContextPath());
		if (mbs != null) {
			try {
				final ObjectName oname = new ObjectName(GREETER_ONAME);
				if (mbs.isRegistered(oname)) {
					mbs.unregisterMBean(oname);
				}
			} catch (MalformedObjectNameException | InstanceNotFoundException | MBeanRegistrationException e) {
				System.out.println("Problems unregistering Greeter bean: "
						+ sce.getServletContext().getServletContextName() + "\n" + e.getMessage());
			}
		} else {
			System.out.println("MBeanServer not initialized");
		}
	}
}
