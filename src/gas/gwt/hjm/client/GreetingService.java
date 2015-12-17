package gas.gwt.hjm.client;

import java.util.List;

import gas.gwt.hjm.server.src.Model.Event;
import gas.gwt.hjm.server.src.Model.SchedulerInfo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	List<Event> deserilze(String fileName);
	List<Event> returnList(String fileName);
	Event createEvent(Event event, SchedulerInfo si);
	Boolean deleteEvent(Event event, SchedulerInfo si);
}
