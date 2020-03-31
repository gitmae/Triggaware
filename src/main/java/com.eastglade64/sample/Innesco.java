package com.eastglade64.sample;

public class Innesco {
  String pod;
  
  String YM;
  
  String DD;
  
  public Innesco() {}
  
  public Innesco(String pod, String YM, String DD) {
    this.pod = pod;
    this.YM = YM;
    this.DD = DD;
  }
  
  public String getPod() {
    return this.pod;
  }
  
  public void setPod(String pod) {
    this.pod = pod;
  }
  
  public String getYM() {
    return this.YM;
  }
  
  public void setYM(String YM) {
    this.YM = YM;
  }
  
  public String getDD() {
    return this.DD;
  }
  
  public void setDD(String DD) {
    this.DD = DD;
  }
  
  public String toString() {
    return "('" + this.YM + "','" + this.pod + "', '" + this.DD + "'" + ')';
  }
}
