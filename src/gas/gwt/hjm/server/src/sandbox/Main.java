package gas.gwt.hjm.server.src.sandbox;



import java.util.Date;

import gas.gwt.hjm.server.src.API.GeneratorAPI;
import gas.gwt.hjm.server.src.Model.Event;
import gas.gwt.hjm.server.src.Model.EventType;
import gas.gwt.hjm.server.src.Model.SchedulerInfo;
import gas.gwt.hjm.server.src.Model.SchedulerInfoType;
import gas.gwt.hjm.server.src.global.Manager;


public class Main {

	public static void main(String [ ] args) {

		System.out.println("Welcome to GAS!");
		GeneratorAPI.deserializer(SchedulerInfoType.CCMS);

		SchedulerInfo si = Manager.getInstance().getFilesList().get("B1801001");
		Event e;
		
		e = new Event(new Date(), new Date(), new Date(), new Date(), 1, 1, new Date(), "eventqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq", EventType.SCHEDULED);
		GeneratorAPI.createEvent(e, si);
		
		e = new Event(new Date(), new Date(), new Date(), new Date(), 1000, 1, new Date(), "a", EventType.SCHEDULED);
		GeneratorAPI.createEvent(e, si);

		e = new Event(new Date(), new Date(), new Date(), new Date(), 0, 1, new Date(), "a", EventType.FILL);
		GeneratorAPI.createEvent(e, si);
		
		e = new Event(null, null, null, null, 0, 0, null, null, null);
		GeneratorAPI.createEvent(e, si);

		GeneratorAPI.serializer(SchedulerInfoType.CCMS);
		System.out.println("@M.E.E.R.B");
	}
}