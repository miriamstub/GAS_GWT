package gas.gwt.hjm.server.src.Serializer;

import gas.gwt.hjm.server.src.Model.SchedulerInfo;

/**
 * ISerializer.java Interface
 * Declare basic serializing functions.
 * Each schedulerInfoType implements the interface according to its certain serializing process.
 * 
 **/
public interface ISerializer {
	
	void run();
	void createSchedulerInfo(SchedulerInfo value1);

}
