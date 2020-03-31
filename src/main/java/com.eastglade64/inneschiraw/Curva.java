package com.eastglade64.inneschiraw;

public class Curva {
  private String measYM;
  
  private String pod;
  
  private String measDD;
  
  private String magnitude;
  
  private String meterKey;
  
  private String recId;
  
  private String collectId;
  
  public Curva(String measYM, String pod, String measDD, String magnitude, String meterKey, String recId, String collectId) {
    this.measYM = measYM;
    this.pod = pod;
    this.measDD = measDD;
    this.magnitude = magnitude;
    this.meterKey = meterKey;
    this.recId = recId;
    this.collectId = collectId;
  }
  
  public String toString() {
    return this.measYM + "|" + this.pod + "|" + this.measDD + "|" + this.magnitude + "|" + this.meterKey + "|" + this.recId + "|" + this.collectId;
  }
  
  public String getMeasYM() {
    return this.measYM;
  }
  
  public void setMeasYM(String measYM) {
    this.measYM = measYM;
  }
  
  public String getPod() {
    return this.pod;
  }
  
  public void setPod(String pod) {
    this.pod = pod;
  }
  
  public String getMeasDD() {
    return this.measDD;
  }
  
  public void setMeasDD(String measDD) {
    this.measDD = measDD;
  }
  
  public String getMagnitude() {
    return this.magnitude;
  }
  
  public void setMagnitude(String magnitude) {
    this.magnitude = magnitude;
  }
  
  public String getMeterKey() {
    return this.meterKey;
  }
  
  public void setMeterKey(String meterKey) {
    this.meterKey = meterKey;
  }
  
  public String getRecId() {
    return this.recId;
  }
  
  public void setRecId(String recId) {
    this.recId = recId;
  }
  
  public String getCollectId() {
    return this.collectId;
  }
  
  public void setCollectId(String collectId) {
    this.collectId = collectId;
  }
}
