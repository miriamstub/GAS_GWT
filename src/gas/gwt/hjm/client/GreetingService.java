package gas.gwt.hjm.client;

import gas.gwt.hjm.server.src.Model.Event;
import gas.gwt.hjm.server.src.Model.SchedulerInfo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;

	Event greetServer2(Event event, SchedulerInfo schedulerInfo);
}
