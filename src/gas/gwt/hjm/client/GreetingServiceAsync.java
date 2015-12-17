package gas.gwt.hjm.client;

import java.util.List;

import gas.gwt.hjm.server.src.Model.Event;
import gas.gwt.hjm.server.src.Model.SchedulerInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void deserilze(	String fileName, AsyncCallback<List<Event>> asyncCallback);
	void returnList(String fileName, AsyncCallback<List<Event>> asyncCallback);
	void createEvent(Event event, SchedulerInfo si, AsyncCallback<Event> asyncCallback);
	void deleteEvent(Event event, SchedulerInfo si, AsyncCallback<Boolean> asyncCallback);
}
