package com.eastglade64.model.trigger;

import com.eastglade64.model.measure.Curva;
import com.eastglade64.model.measure.Registro;
import com.eastglade64.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class TriggerPLP extends Trigger {

    private final Registro sx;
    private final Registro dx;
    private final List<Curva> curvaList;

    public TriggerPLP(Registro sx, Registro dx, List<Curva> curvaList) {
        this.sx = sx;
        this.dx = dx;
        this.curvaList = curvaList;
    }

    public static TriggerPLPBuilder builder() {
        return new TriggerPLPBuilder();
    }

    @Override
    public String toJson() {
        return "{\"rdSxK\":\"" + sx + "\", " +
                "\"rdDxK\":\"" + dx + "\", " +
                "\"lpK\":[\"" + CollectionUtils.mkString(",", curvaList) + "\"]}";
    }

    /**
     * I realize it's a builder with setters
     * but because of the way it's used
     * it does not need to return itself
     *
     * Should it be called something else?
     */
    public static final class TriggerPLPBuilder {
        private Registro sx;
        private Registro dx;
        private List<Curva> curvaList = new ArrayList<>();

        public void setSx(Registro sx) {
            this.sx = sx;
        }

        public void setDx(Registro dx) {
            this.dx = dx;
        }

        public void addCurva(Curva curva) {
            this.curvaList.add(curva);
        }

        public TriggerPLP build() {
            return new TriggerPLP(sx, dx, curvaList);
        }

        public boolean hasSx() {
            return this.sx != null;
        }

    }
}
