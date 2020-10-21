package com.eastglade64.model.trigger;

public enum TriggerFamily {
  R("R"),
  NR("NR");
  
  private String label;
  
  TriggerFamily(String label) {
    this.label = label;
  }
  
  public String getLabel() {
    return this.label;
  }
  
  public String toString() {
    return this.label;
  }
}
