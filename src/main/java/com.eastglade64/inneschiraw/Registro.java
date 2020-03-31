package com.eastglade64.inneschiraw;

public class Registro {
  String meas_ym;
  
  String pod;
  
  String meas_dd;
  
  String meas_hms;
  
  String meter_key;
  
  String ref_period_id;
  
  String rec_id;
  
  String collect_id;
  
  public Registro(String PipeDelimitedReading) {
    String[] readingTokens = PipeDelimitedReading.split("|");
  }
  
  public String getMeas_ym() {
    return this.meas_ym;
  }
  
  public void setMeas_ym(String meas_ym) {
    this.meas_ym = meas_ym;
  }
  
  public String getPod() {
    return this.pod;
  }
  
  public void setPod(String pod) {
    this.pod = pod;
  }
  
  public String getMeas_dd() {
    return this.meas_dd;
  }
  
  public void setMeas_dd(String meas_dd) {
    this.meas_dd = meas_dd;
  }
  
  public String getMeas_hms() {
    return this.meas_hms;
  }
  
  public void setMeas_hms(String meas_hms) {
    this.meas_hms = meas_hms;
  }
  
  public String getMeter_key() {
    return this.meter_key;
  }
  
  public void setMeter_key(String meter_key) {
    this.meter_key = meter_key;
  }
  
  public String getRef_period_id() {
    return this.ref_period_id;
  }
  
  public void setRef_period_id(String ref_period_id) {
    this.ref_period_id = ref_period_id;
  }
  
  public String getRec_id() {
    return this.rec_id;
  }
  
  public void setRec_id(String rec_id) {
    this.rec_id = rec_id;
  }
  
  public String getCollect_id() {
    return this.collect_id;
  }
  
  public void setCollect_id(String collect_id) {
    this.collect_id = collect_id;
  }
}
