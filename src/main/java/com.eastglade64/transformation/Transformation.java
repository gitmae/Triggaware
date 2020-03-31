package com.eastglade64.transformation;

import com.eastglade64.model.exception.TransformationException;

public interface Transformation {
  String transform(String paramString) throws TransformationException;
}
