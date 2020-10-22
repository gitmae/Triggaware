package com.eastglade64.transformation.aggregation;

import com.eastglade64.model.measure.Measure;
import com.eastglade64.model.trigger.TriggerPLP;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TriggerPLPAggregator implements TriggerAggregator<TriggerPLP> {

    @Override
    public List<TriggerPLP> aggregate(List<Measure> measures) {

        return measures.stream()
                .collect(Collectors.groupingBy(Measure::getPod))
                .values()
                .stream()
                .flatMap(this::aggregatePodMeasures)
                .collect(Collectors.toList());
    }

    private Stream<TriggerPLP> aggregatePodMeasures(List<Measure> measures) {
        TriggerPLPAccumulator accumulator = new TriggerPLPAccumulator();
        new TreeSet<>(measures).forEach(accumulator::add);
        return accumulator.getTriggers().stream();
    }

    private final class TriggerPLPAccumulator {

        private final List<TriggerPLP> triggers = new ArrayList<>();
        private TriggerPLP.TriggerPLPBuilder currentTrigger = TriggerPLP.builder();

        private TriggerPLPAccumulator(){}

        private void add(Measure m) {
          m.consume(
                  registro -> {
                      if (currentTrigger.hasSx()) {
                          currentTrigger.setDx(registro);
                          triggers.add(currentTrigger.build());
                          currentTrigger = TriggerPLP.builder();
                      }
                      // we do this in either case
                      currentTrigger.setSx(registro);
                  },
                  curva -> currentTrigger.addCurva(curva)
          );
        }

        private List<TriggerPLP> getTriggers() {
            return this.triggers;
        }
    }
}
