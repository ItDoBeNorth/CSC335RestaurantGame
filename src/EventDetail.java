
public class EventDetail {
	private String eventInfo;
	private Object eventChange;
	
	
	public EventDetail(String info, Object itemChange) {
		//use enums if needed later
		this.eventInfo  = info;
		this.eventChange  = itemChange;
	}
	
	public String getEventInfo() {
		return eventInfo;
	}
	
	public Object getEventChange() {
		return eventChange;
	}

}
