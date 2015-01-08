package es.jc.mbeans;

import javax.management.NotificationEmitter;

public interface GreeterMBean extends NotificationEmitter {
	
	public String getLanguage();
	public void setLanguage(String language);
	
	public void managedGreet(String name);
	
	public int getGreetCount();
	public void incrementGreetCount();

}
