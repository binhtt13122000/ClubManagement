package binhtt.dtos;

import binhtt.entities.TblEvent;
import binhtt.entities.TblUser;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class CartEventObj implements Serializable {
    private String studentID;
    private HashMap<String, TblEvent> events;

    public CartEventObj(String studentID) {
        this.studentID = studentID;
        events = new HashMap<>();
    }

    public CartEventObj(){
        this.studentID = "GUEST";
        events = new HashMap<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public HashMap<String, TblEvent> getEvents() {
        return events;
    }

    public String addToCart(TblEvent dto) throws Exception{
        if(this.events.containsKey(dto.getEventID())){
            return "This event is in the cart";
        }
        Date timeStart = dto.getTimeStartEvent();
        for (String eventID: this.getEvents().keySet()) {
            TblEvent event = this.events.get(eventID);
            if(timeStart.equals(event.getTimeStartEvent())){
                return "You have chosen a vent have same time with this event";
            }
        }
        this.events.put(dto.getEventID(), dto);
        return null;
    }

    public boolean isContains(String id) throws Exception{
        return this.events.containsKey(id);
    }

    public void remove(String id) throws Exception {
        if(this.events.containsKey(id)){
            this.events.remove(id);
        }
    }
}
