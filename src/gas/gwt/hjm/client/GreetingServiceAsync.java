package gas.gwt.hjm.client;

import gas.gwt.hjm.server.src.Model.Event;
import gas.gwt.hjm.server.src.Model.SchedulerInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void greetServer2(Event event, SchedulerInfo schedulerInfo,
			AsyncCallback<Event> callback);
}
