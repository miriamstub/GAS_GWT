package gas.gwt.hjm.server;

import java.util.ArrayList;
import java.util.List;

import gas.gwt.hjm.client.GreetingService;
import gas.gwt.hjm.server.src.API.GeneratorAPI;
import gas.gwt.hjm.server.src.Model.Event;
import gas.gwt.hjm.server.src.Model.SchedulerInfo;
import gas.gwt.hjm.server.src.global.Manager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	@Override
	public List<Event> deserilze(String fileName) {
		GeneratorAPI.deserializer();

		return new ArrayList<Event>(Manager.getInstance().getAllEvents(fileName));
	}

	@Override
	public Event createEvent(Event event, SchedulerInfo si) {
		return GeneratorAPI.createEvent(event, si);
	}

	@Override
	public List<Event> returnList(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteEvent(Event event, SchedulerInfo si) {
		return GeneratorAPI.deleteEvent(si.getSchInfoName(), event.getID());
	}
}
