package com.eastglade64.transformation;

import com.eastglade64.model.EventType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TransformationFactory {

    private TransformationFactory() {}

    public static Transformation forEventType(EventType eventType) {
        switch (eventType) {
            case PDO_RFO_CE2G_R:
            case PNO_RNO_CE2G_R:
            case SNM_RSN_CE2G_R:
                return new RTriggerTransformation(eventType);
            default:
                throw new NotImplementedException();
        }
    }
}
