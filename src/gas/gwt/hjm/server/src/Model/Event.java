package gas.gwt.hjm.server.src.Model;

import gas.gwt.hjm.server.src.global.Manager;

import java.io.Serializable;
import java.util.*;

/**
 * Event.java class
 * 
 * @property {Date} date - T&B's approximation of the date when the event will occur.
 * @property {Date} time - T&B's approximation of the time of day when the event will occur.
 * @property {Window} window - Qv Window.java.
 * @property {Actual} actual - Qv Actual.java.
 * @property {String} adName - a name assigned to the ad during the encoding process.
 * @property {String} stat - aired status.
 * @property {EventType} eventType - Qv EventType.java.
 * @property {UUID} ID - unique identifier.
 * 
 * 
 * @author bfeldman 
 * Nov 10, 2015
 */

public class Event implements Comparable<Event>, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	private Date time;
	private Window window;
	private Actual actual;
	private String adName;
	private String stat;
	private EventType eventType;
	private String ID;
	
	public Event() {
		// default c-tor
	}

	public Event(Date date, Date time, Date start,Date duration, int brk, int pos, Date length,
			/*String actualTime, String actualLength,String actualPos, String stat,*/
			String adName, EventType eventType) {

		setDate(date);
		setTime(time);
		this.window = new Window(start, duration, brk, pos, length);
		setAdName(adName);
		setEventType(eventType);
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date =date;
	}


	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public EventType getEventType() {
		return eventType;
	}


	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getID() {
		return ID;
	}

	@Override
	public String toString() {
		return "Event [date=" + date + ", time=" + time + ", window=" + window
				+ ", actual=" + actual + ", adName=" + adName + ", stat="
				+ stat + ", eventType=" + eventType + ", ID=" + ID + "]";
	}

	/**
	 * check only the uniqe params
	 */
	public int compareTo(Event event) {
		if (this.eventType != event.getEventType()) {
			return -1;
		}
		if (event.getEventType() == EventType.SCHEDULED) {
			if (this.window.getBrk() != event.getWindow().getBrk() || this.window.getDuration() != event.getWindow().getDuration() || this.window.getStart() != event.getWindow().getStart() || this.window.getPos() != event.getWindow().getPos())
				return -1;
		} else {
			if(this.time != event.getTime())
				return -1;
		}
		return 0;
	}
	
	public String generateUUID() {
		this.ID = Manager.getUUID();
		return this.ID;
	}
}