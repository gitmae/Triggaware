package com.eastglade64.transformation;

import com.eastglade64.model.EventType;
import com.eastglade64.model.exception.TransformationException;
import com.eastglade64.model.trigger.TriggerPLP;
import com.eastglade64.model.util.CollectionPair;
import com.eastglade64.transformation.aggregation.TriggerPLPAggregator;
import com.eastglade64.transformation.parsing.MeasuresParser;
import com.eastglade64.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RTriggerTransformation implements Transformation {


  private static final MeasuresParser MEASURES_PARSER = new MeasuresParser();
  private static final TriggerPLPAggregator TRIGGER_AGGREGATOR = new TriggerPLPAggregator();

  private static final DateTimeFormatter TRIGGER_ID_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddkkmmssSS");
  private static final DateTimeFormatter LAST_UPD_TS_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.000");

  private final LocalDateTime now = LocalDateTime.now();
  private final String lastUpdateTs = now.format(LAST_UPD_TS_FORMAT);
  private final EventType eventType;

  public RTriggerTransformation(EventType eventType) {
    this.eventType = eventType;
  }
  
  public TransformationResult transform(String input) throws TransformationException {
      try {
          List<TriggerPLP> triggers = TRIGGER_AGGREGATOR.aggregate(MEASURES_PARSER.parse(input));

          CollectionPair<TriggerPLP, List<TriggerPLP>> cp = CollectionUtils.span(triggers, TriggerPLP::hasCurva);

          List<TriggerPLP> ok = cp.getTrue();
          List<TriggerPLP> ko = cp.getFalse();

          String okJson = IntStream.range(0, ok.size())
                  .mapToObj(ix -> composizioneInnesco(ok.get(ix), ix))
                  .collect(Collectors.joining());

          String koJson = ko.stream()
                  .map(TriggerPLP::toJson)
                  .collect(Collectors.joining("\n"));

          String info = ok.size() + " inneschi creati.\n" +
                  (ko.size() > 0 ? ko.size() +
                          " inneschi non creati perchè risulterebbero senza curve:\n" + koJson : "");


          return TransformationResult.builder()
                  .withOutput(okJson)
                  .withInfo(info)
                  .build();
      } catch (Exception e) {
          throw new TransformationException("Error in transform", e);
      }
  }

  private String composizioneInnesco(TriggerPLP trigger, int seq) {

    String triggerId = now.format(TRIGGER_ID_FORMAT) +
            String.format("%03d", seq) +
            ":02:04:08:16:F64abcd32mpx0l0EXP";

    return "INSERT INTO settlement.event_change_trigger " +
            "(event_type, process_destination, trigger_id, last_update_ts, process_source, \"trigger\", status)\n" +
            "VALUES("+
            "'" + this.eventType.getLabel() + "', " +
            "'SPLITTING', "+
            "'" + triggerId + "', " +
            "'" + lastUpdateTs + "', " +
            "'NCVP', " +
            "'" + trigger.toJson() + "', " +
            "'NEW');\n";
  }
}
