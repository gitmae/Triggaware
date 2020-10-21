package com.eastglade64.model.measure;

import com.eastglade64.model.time.Time;
import com.eastglade64.model.time.TimeInstant;
import com.eastglade64.util.TimeUtils;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class Registro extends Measure {

  private static final Comparator<Registro> REGISTRO_COMPARATOR = Comparator.comparing(Registro::getTime);
  private static final Comparator<Measure> CURVA_COMPARATOR = Comparator.comparing(Measure::getTime).thenComparingInt(r -> 1);

  private final String measYM;
  private final String pod;
  private final String measDD;
  private final String measHMS;
  private final String meterKey;
  private final String refPeriodId;
  private final String recId;
  private final String collectId;

  private TimeInstant measTs;

  public Registro(String measYM, String pod, String measDD,
                  String measHMS, String meterKey, String refPeriodId,
                  String recId, String collectId) {
    this.measYM = measYM;
    this.pod = pod;
    this.measDD = measDD;
    this.measHMS = measHMS;
    this.meterKey = meterKey;
    this.refPeriodId = refPeriodId;
    this.recId = recId;
    this.collectId = collectId;
  }

  @Override
  public String toString() {
    return measYM + "|" + pod + "|" + measDD + "|" + measHMS +
            "|" + meterKey + "|" + refPeriodId + "|" + recId + "|" + collectId;
  }

  @Override
  public TimeInstant getTime() {
    if (measTs == null) {
      LocalDateTime instant = TimeUtils.parseYearMonthDayHourMinuteSeconds(
              measYM + measDD + measHMS);
      measTs = new TimeInstant(instant);
    }
    return measTs;
  }

  @Override
  public <T> T match(Function<Registro, T> r, Function<Curva, T> c) {
    return r.apply(this);
  }

  @Override
  public void consume(Consumer<Registro> r, Consumer<Curva> c) {
    r.accept(this);
  }

  public String getMeasYM() {
    return this.measYM;
  }
  
  public String getPod() {
    return this.pod;
  }

  public String getMeasDD() {
    return this.measDD;
  }

  public String getMeasHMS() {
    return this.measHMS;
  }

  public String getMeterKey() {
    return this.meterKey;
  }

  public String getRefPeriodId() {
    return this.refPeriodId;
  }
  
  public String getRecId() {
    return this.recId;
  }

  public String getCollectId() {
    return this.collectId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Registro)) return false;
    Registro registro = (Registro) o;
    return measYM.equals(registro.measYM) &&
            pod.equals(registro.pod) &&
            measDD.equals(registro.measDD) &&
            measHMS.equals(registro.measHMS) &&
            meterKey.equals(registro.meterKey) &&
            refPeriodId.equals(registro.refPeriodId) &&
            recId.equals(registro.recId) &&
            collectId.equals(registro.collectId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(measYM, pod, measDD, measHMS, meterKey, refPeriodId, recId, collectId);
  }

  @Override
  public int compareTo(Measure m) {
    return m.match(
            registro -> REGISTRO_COMPARATOR.compare(this, registro),
            curva -> CURVA_COMPARATOR.compare(this, curva)
    );
  }
}
