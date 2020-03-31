package com.eastglade64.model;

public enum EventType {
  PDO_RFO_CE2G_R("PDO_RFO_CE2G_R"),
  PNO_RNO_CE2G_R("PNO_RNO_CE2G_R"),
  SNM_RSN_CE2G_R("SNM_RSN_CE2G_R");
  
  private String label;
  
  EventType(String label) {
    this.label = label;
  }
  
  public String getLabel() {
    return this.label;
  }
  
  public String toString() {
    return this.label;
  }
}
