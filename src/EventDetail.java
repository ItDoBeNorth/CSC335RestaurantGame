
/**
 * This class is a event descriptor.
 * It stores a short description of the event along with an
 * associated value or object that represents what changed.
 *
 */
public class EventDetail {
	private String eventInfo;
	private Object eventChange;
	
	 /**
     * create an EventDetail with a description and a change object.
     *
     * @param info: A text description of the event
     * @param itemChange: An object describing what changed during the event
     */
	public EventDetail(String info, Object itemChange) {
		//use enums if needed later
		this.eventInfo  = info;
		this.eventChange  = itemChange;
	}
	
	/**
     * Returns the descriptive message for the event.
     *
     * @return the event description string
     */
	public String getEventInfo() {
		return eventInfo;
	}
	
	 /**
      * Returns the object describing what changed during the event.
      *
      * @return the object representing the change
      */
	public Object getEventChange() {
		return eventChange;
	}

}
