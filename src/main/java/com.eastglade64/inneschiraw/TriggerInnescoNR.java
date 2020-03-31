package com.eastglade64.inneschiraw;

public class TriggerInnescoNR extends TriggerInnesco {
  public Registro registro;
  
  public TriggerInnescoNR(Registro registro) {
    this.registro = registro;
  }
  
  String ReturnJSONTrigger() {
    String OutJSON = "'{\"pod\":\"";
    OutJSON = OutJSON + this.Pod;
    OutJSON = OutJSON + "\",\"measYearMonth\":\"";
    OutJSON = OutJSON + this.registro.getMeas_ym();
    OutJSON = OutJSON + "\",\"measDay\":\"";
    OutJSON = OutJSON + this.registro.getMeas_dd();
    OutJSON = OutJSON + "\", \"rdDxK\":\"";
    return OutJSON;
  }
}
