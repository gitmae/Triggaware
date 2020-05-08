package com.eastglade64.transformation;

import com.eastglade64.model.EventType;
import com.eastglade64.model.exception.TransformationException;

public class MassivaTriggerTransformation implements Transformation{
    private final EventType eventType;

    public MassivaTriggerTransformation(EventType eventType) {
        this.eventType = eventType;
    }

    public String transform(String input) throws TransformationException {
        String outputBlock = "";

   /*     try {
            input = input.replaceAll("\t", "|");
            String[] ArrayBlocchi = input.split("\\*");
            for (int i = 0; i < ArrayBlocchi.length; i++)
                outputBlock = outputBlock + composizioneInnesco(ArrayBlocchi[i], i);
        } catch (Exception e) {
            TransformationException excp = new TransformationException("Input non valido");
            excp.initCause(e);
            throw excp;
        }*/

        return outputBlock;
    }
}
