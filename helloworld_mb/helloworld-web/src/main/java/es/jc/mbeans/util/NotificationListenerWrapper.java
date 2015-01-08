package es.jc.mbeans.util;

import javax.management.NotificationFilter;
import javax.management.NotificationListener;

/**
 * Utility class to hold a triplet listener-filter-handback to be used for notification subscriptions.
 */
public class NotificationListenerWrapper {
	
	private NotificationListener listener;
	private NotificationFilter filter;
	private Object handback;

	/**
	 * Constructor with all attributes.
	 * @param listener the listener class itself
	 * @param filter the filter criteria to be evaluated by notifier before sending the notification
	 * @param handback context data for listener to be handed back in each notification
	 */
	public NotificationListenerWrapper(NotificationListener listener, NotificationFilter filter, Object handback) {
		super();
		this.listener = listener;
		this.filter = filter;
		this.handback = handback;
	}

	public NotificationListener getListener() {
		return listener;
	}

	public NotificationFilter getFilter() {
		return filter;
	}

	public Object getHandback() {
		return handback;
	}
	
}