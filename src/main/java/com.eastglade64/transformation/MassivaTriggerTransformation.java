package com.eastglade64.transformation;

import com.eastglade64.model.EventType;
import com.eastglade64.model.exception.TransformationException;

public class MassivaTriggerTransformation implements Transformation{
    private final EventType eventType;

    public MassivaTriggerTransformation(EventType eventType) {
        this.eventType = eventType;
    }

    public TransformationResult transform(String input) throws TransformationException {
        return TransformationResult.builder()
                .withOutput("Not implemented")
                .build();
    }
}
