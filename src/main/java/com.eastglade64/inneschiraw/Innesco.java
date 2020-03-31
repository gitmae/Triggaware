package com.eastglade64.inneschiraw;

import java.time.LocalDateTime;

public class Innesco {
  String event_type;
  
  public static final String process_destination = "SPLITTING";
  
  TriggerId trigger_id;
  
  String last_update_ts;
  
  public static final String process_source = "NCVP";
  
  TriggerInnesco TriggerInnesco;
  
  public static final String status = "NEW";
  
  public Innesco(String event_type, LocalDateTime DataIn, String Pod) {
    this.event_type = event_type;
    LocalDateTime dataIn = DataIn;
  }
  
  public String getEvent_type() {
    return this.event_type;
  }
  
  public void setEvent_type(String event_type) {
    this.event_type = event_type;
  }
  
  public TriggerId getTrigger_id() {
    return this.trigger_id;
  }
  
  public void setTrigger_id(TriggerId trigger_id) {
    this.trigger_id = trigger_id;
  }
  
  public TriggerInnesco getTriggerInnesco() {
    return this.TriggerInnesco;
  }
  
  public void setTriggerInnesco(TriggerInnesco triggerInnesco) {
    this.TriggerInnesco = triggerInnesco;
  }
  
  public Innesco() {}
}
