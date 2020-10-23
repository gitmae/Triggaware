package com.eastglade64.transformation;

import com.eastglade64.model.exception.TransformationException;

public interface Transformation {
  TransformationResult transform(String paramString) throws TransformationException;
}
