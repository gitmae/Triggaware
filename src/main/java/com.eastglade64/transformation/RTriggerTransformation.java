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
    StringBuilder outputBlock = new StringBuilder();

    try {
      String[] blocchi = input.replaceAll("\t", "|").split("\\*");
      for (int i = 0; i < blocchi.length; i++)
        outputBlock.append(composizioneInnesco(blocchi[i], i));
    } catch (Exception e) {
        throw new TransformationException("Input non valido", e);
    }

    return outputBlock.toString();
  }

  private String composizioneInnesco(String blocco, int seq) {
    DateTimeFormatter triggerIdFormat = new DateTimeFormatterBuilder()
            .appendPattern("yyyy")
            .appendPattern("MM")
            .appendPattern("dd")
            .appendPattern("kk")
            .appendPattern("mm")
            .appendPattern("ss")
            .appendPattern("SS")
            .toFormatter();

    String trigger_id = LocalDateTime.now().format(triggerIdFormat) +
            String.format("%03d", seq) +
            ":02:04:08:16:F64abcd32mpx0l0EXP";

    DateTimeFormatter lastUpdTsFormat = new DateTimeFormatterBuilder()
            .appendPattern("yyyy")
            .appendLiteral("-")
            .appendPattern("MM")
            .appendLiteral("-")
            .appendPattern("dd")
            .appendLiteral(" ")
            .appendPattern("kk")
            .appendLiteral(":")
            .appendPattern("mm")
            .appendLiteral(":")
            .appendPattern("ss")
            .appendLiteral(".000")
            .toFormatter();

    String lastUpdateTs = LocalDateTime.now().format(lastUpdTsFormat);
    String[] righeBlocco = blocco.split("\n");

    String rSx = righeBlocco[0].replaceAll("\n", "");
    String rDx = righeBlocco[1].replaceAll("\n", "");
    String cA1 = righeBlocco[2].replaceAll("\n", "");
    String cR1 = righeBlocco[3].replaceAll("\n", "");

    return "INSERT INTO settlement.event_change_trigger " +
            "(event_type, process_destination, trigger_id, last_update_ts, process_source, \"trigger\", status)\n" +
            "VALUES('" +
            this.eventType.getLabel() +
            "', 'SPLITTING', '" +
            trigger_id +
            "', '" +
            lastUpdateTs +
            "', 'NCVP', " +
            "'{\"rdSxK\":\"" + rSx +
            "\", \"rdDxK\":\"" + rDx +
            "\", \"lpK\":[\"" + cA1 +
            "\", \"" + cR1 +
            "\"]}', 'NEW');\n";
  }
}
