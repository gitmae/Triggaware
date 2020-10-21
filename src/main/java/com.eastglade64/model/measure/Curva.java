package com.eastglade64.model.measure;

import com.eastglade64.model.time.Time;
import com.eastglade64.model.time.TimeInterval;
import com.eastglade64.util.TimeUtils;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class Curva extends Measure {

  private static final Comparator<Measure> REGISTRO_COMPARATOR = Comparator.comparing(Measure::getTime).thenComparingInt(x -> -1);
  private static final Comparator<Curva> CURVA_COMPARATOR = Comparator.comparing(Curva::getTime).thenComparing(Curva::getMagnitude);

  private final String measYM;
  private final String pod;
  private final String measDD;
  private final String magnitude;
  private final String meterKey;
  private final String recId;
  private final String collectId;

  private TimeInterval measInterval;

  public Curva(String measYM, String pod, String measDD,
               String magnitude, String meterKey, String recId,
               String collectId) {
    this.measYM = measYM;
    this.pod = pod;
    this.measDD = measDD;
    this.magnitude = magnitude;
    this.meterKey = meterKey;
    this.recId = recId;
    this.collectId = collectId;
  }

  @Override
  public String toString() {
    return measYM + "|" + pod + "|" + measDD + "|" + magnitude +
            "|" + meterKey + "|" + recId + "|" + collectId;
  }

  @Override
  public Time getTime() {
    if (measInterval == null) {
      LocalDateTime start = TimeUtils.parseYearMonthDayHourMinuteSeconds(
              measYM + measDD + "000000");
      LocalDateTime end = TimeUtils.parseYearMonthDayHourMinuteSeconds(
              measYM + measDD + "235959");
      measInterval = new TimeInterval(start, end);
    }
    return measInterval;
  }

  @Override
  public <T> T match(Function<Registro, T> r, Function<Curva, T> c) {
    return c.apply(this);
  }

  @Override
  public void consume(Consumer<Registro> r, Consumer<Curva> c) {
    c.accept(this);
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

  public String getMagnitude() {
    return this.magnitude;
  }

  public String getMeterKey() {
    return this.meterKey;
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
    if (!(o instanceof Curva)) return false;
    Curva curva = (Curva) o;
    return measYM.equals(curva.measYM) &&
            pod.equals(curva.pod) &&
            measDD.equals(curva.measDD) &&
            magnitude.equals(curva.magnitude) &&
            meterKey.equals(curva.meterKey) &&
            recId.equals(curva.recId) &&
            collectId.equals(curva.collectId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(measYM, pod, measDD, magnitude, meterKey, recId, collectId);
  }

  @Override
  public int compareTo(Measure m) {
    return m.match(
            registro -> REGISTRO_COMPARATOR.compare(this, registro),
            curva -> CURVA_COMPARATOR.compare(this, curva)
    );
  }
}

