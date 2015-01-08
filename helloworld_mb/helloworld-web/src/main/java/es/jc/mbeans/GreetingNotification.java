package es.jc.mbeans;

import javax.management.Notification;

public class GreetingNotification extends Notification {

	public enum GREETING_NOTIFTYPE {
		GREETING_THRESHOLD_OK("greeting.threshold.ok"),
		GREETING_THRESHOLD_REACHED("greeting.threshold.reached");
		
		private String type;
		
		private GREETING_NOTIFTYPE(String type) {
			this.type = type;
		}
		
		public String getType() {
			return type;
		}
	}
	
	private static final long serialVersionUID = 1L;

	public GreetingNotification(GREETING_NOTIFTYPE type, Object source, long sequenceNumber) {
		super(type.getType(), source, sequenceNumber);
	}
}
