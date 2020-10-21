package com.eastglade64.transformation.aggregation;

import com.eastglade64.model.measure.Measure;
import com.eastglade64.model.trigger.Trigger;

import java.util.List;

interface TriggerAggregator<T extends Trigger> {

    List<T> aggregate(List<Measure> measures);
}
