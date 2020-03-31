package com.eastglade64.transformation;

import com.eastglade64.model.EventType;
import com.eastglade64.model.exception.TransformationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class RTriggerTransformation implements Transformation {
  private final EventType eventType;
  
  public RTriggerTransformation(EventType eventType) {
    this.eventType = eventType;
  }
  
  public String transform(String input) throws TransformationException {
    String outputBlock = "";

    try {
      input = input.replaceAll("\t", "|");
      String[] ArrayBlocchi = input.split("\\*");
      for (int i = 0; i < ArrayBlocchi.length; i++)
        outputBlock = outputBlock + composizioneInnesco(ArrayBlocchi[i], i);
    } catch (Exception e) {
        TransformationException excp = new TransformationException("Input non valido");
        excp.initCause(e);
        throw excp;
    }

    return outputBlock;
  }

  private String composizioneInnesco(String Blocco, int Seq) {
    String outputBlock = "";
    String trigger_id = "";
    DateTimeFormatter formatter = (new DateTimeFormatterBuilder()).appendPattern("yyyy").appendPattern("MM").appendPattern("dd").appendPattern("kk").appendPattern("mm").appendPattern("ss").appendPattern("SS").toFormatter();

    trigger_id = LocalDateTime.now().format(formatter);
    trigger_id = trigger_id + String.format("%03d", new Object[] { Integer.valueOf(Seq) });
    trigger_id = trigger_id + ":02:04:08:16:F64abcd32mpx0l0EXP";

    formatter = (new DateTimeFormatterBuilder()).appendPattern("yyyy").appendLiteral("-").appendPattern("MM").appendLiteral("-").appendPattern("dd").appendLiteral(" ").appendPattern("kk").appendLiteral(":").appendPattern("mm").appendLiteral(":").appendPattern("ss").appendLiteral(".000").toFormatter();

    String last_update_ts = LocalDateTime.now().format(formatter);
    String[] RigheBlocco = Blocco.split("\n");

    String RSx = RigheBlocco[0].replaceAll("\n", "");
    String RDx = RigheBlocco[1].replaceAll("\n", "");
    String CA1 = RigheBlocco[2].replaceAll("\n", "");
    String CR1 = RigheBlocco[3].replaceAll("\n", "");

    outputBlock = "INSERT INTO settlement.event_change_trigger (event_type, process_destination, trigger_id, last_update_ts, process_source, \"trigger\", status)\nVALUES('";
    outputBlock = outputBlock + this.eventType.getLabel();
    outputBlock = outputBlock + "', 'SPLITTING', '";
    outputBlock = outputBlock + trigger_id + "', '" + last_update_ts + "', 'NCVP', '{\"rdSxK\":\"";
    outputBlock = outputBlock + RSx;
    outputBlock = outputBlock + "\", \"rdDxK\":\"";
    outputBlock = outputBlock + RDx;
    outputBlock = outputBlock + "\", \"lpK\":[\"";
    outputBlock = outputBlock + CA1;
    outputBlock = outputBlock + "\", \"";
    outputBlock = outputBlock + CR1;
    outputBlock = outputBlock + "\"]}', 'NEW');" + '\n';

    return outputBlock;
  }
}
