package gas.gwt.hjm.server.src.global;

import gas.gwt.hjm.server.src.Model.Event;
import gas.gwt.hjm.server.src.Model.SchedulerInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * This class hold the tree of all the objects. singleton class.
 * Manager -> filesList -> availsList + eventsList.
 * @author rtolidan
 *
 */
public class Manager {

	static Manager instance = null;

	private Map<String, SchedulerInfo> filesList = new HashMap<>();
	
	private Manager() {
	}

	public Map<String, SchedulerInfo> getFilesList() {
		return filesList;
	}

	public void setFilesList(Map<String, SchedulerInfo> tree) {
		this.filesList = tree;
	}

	public static Manager getInstance() {
		if(instance == null) {
			instance = new Manager();
		}
		return instance;
	}
	
	public void addSchedulerInfo(SchedulerInfo schInfo) {
		filesList.put(schInfo.getSchInfoName(), schInfo);
	}
	
	public void deleteSchedulerInfo(SchedulerInfo schInfo) {
		schInfo.getAvailMap().clear();
		schInfo.getEventMap().clear();
		filesList.remove(schInfo);
	}
	
	public ArrayList<Event> getAllEvents(String schInfoName) {
		
		return new ArrayList<Event>(filesList.get(schInfoName).getEventMap().values());
	}
	
	public boolean deleteEvent(String schInfoName, String eventId ){
		return filesList.get(schInfoName).getEventMap().remove(eventId)!=null
		&& UUIDPool.remove(eventId);
	}

	private static Set<UUID> UUIDPool = new HashSet<UUID>();
	
	public String getUUID(){
		UUID uuid = UUID.randomUUID();
		while(!UUIDPool.add(uuid)){
			uuid = UUID.randomUUID();
			System.out.println("Duplicate UUID!!!");
		}
		return uuid.toString();
	}

}