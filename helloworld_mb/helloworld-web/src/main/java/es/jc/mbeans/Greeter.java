package es.jc.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.management.ListenerNotFoundException;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;

import es.jc.mbeans.GreetingNotification.GREETING_NOTIFTYPE;
import es.jc.mbeans.util.NotificationListenerWrapper;

// TODO implement MBeanRegistration to manage MBean registration lifecycle properly
public class Greeter implements GreeterMBean {

	private String language;
	private int greetCount;
	private long notifSeq;

	/**
	 * Listener subscribers for greeting threshold notifying.
	 */
	private List<NotificationListenerWrapper> greetingThresholdNotifListeners;

	private static final int GREETING_THRESHOLD = 10;

	public Greeter() {
		language = "en";
		greetCount = 0;
		notifSeq = 0L;
		greetingThresholdNotifListeners = new ArrayList<>();
	}

	public Greeter(String language) {
		this();
		this.language = language;
	}

	@Override
	public void managedGreet(String name) {
		// implemented as a greeting attempt (not increasing greetCount)
		System.out.println("Greet #" + greetCount + " to " + name + " in " + language);
	}

	@Override
	public String getLanguage() {
		return language;
	}

	@Override
	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public int getGreetCount() {
		return greetCount;
	}

	@Override
	public void incrementGreetCount() {
		greetCount++;
		System.out.println("Incrementing greet count up to: " + greetCount);
		final Notification notif;
		if (greetCount >= GREETING_THRESHOLD) {
			notif = new Notification(GREETING_NOTIFTYPE.GREETING_THRESHOLD_REACHED.getType(), this, ++notifSeq);
		} else {
			notif = new Notification(GREETING_NOTIFTYPE.GREETING_THRESHOLD_OK.getType(), this, ++notifSeq);
		}
		notif.setUserData(greetCount + "/" + GREETING_THRESHOLD);
		greetingThresholdNotifListeners.stream()
				.filter(t -> (t != null) && (t.getFilter() != null) && (t.getFilter().isNotificationEnabled(notif)))
				.forEach(t -> t.getListener().handleNotification(notif, t.getHandback()));
	}

	@Override
	public void removeNotificationListener(NotificationListener listener, NotificationFilter filter, Object handback)
			throws ListenerNotFoundException {
		// TODO Not implemented
		throw new UnsupportedOperationException("Operation unsupported");
	}

	@Override
	public void addNotificationListener(NotificationListener listener, NotificationFilter filter, Object handback)
			throws IllegalArgumentException {
		// listener subscriptions include also the notification filter criteria and a handback with context info to be
		// sent along with each notification to the corresponding listener opaquely
		greetingThresholdNotifListeners.add(new NotificationListenerWrapper(listener, filter, handback));
	}

	@Override
	public void removeNotificationListener(NotificationListener listener) throws ListenerNotFoundException {
		// TODO Not implemented
		throw new UnsupportedOperationException("Operation unsupported");
	}

	@Override
	public MBeanNotificationInfo[] getNotificationInfo() {
		return new MBeanNotificationInfo[] {
				new MBeanNotificationInfo(
						new String[] { GREETING_NOTIFTYPE.GREETING_THRESHOLD_OK.getType() },
						GreetingNotification.class.getName(), "Greeting notification still under threshold"),
				new MBeanNotificationInfo(
						new String[] { GREETING_NOTIFTYPE.GREETING_THRESHOLD_REACHED.getType() },
						GreetingNotification.class.getName(), "Greeting notification threshold reached")
		};
	}

}
