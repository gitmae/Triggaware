package com.eastglade64.transformation;

public class TransformationResult {

    private final String output;
    private final String info;

    private TransformationResult(String output, String info) {
        this.output = output;
        this.info = info;
    }

    public static TransformationResultBuilder builder() {
        return new TransformationResultBuilder();
    }

    public String getOutput() {
        return output;
    }

    public String getInfo() {
        return info;
    }

    public static final class TransformationResultBuilder {
        private String output;
        private String info;

        private TransformationResultBuilder() {}

        public TransformationResultBuilder withOutput(String output) {
            this.output = output;
            return this;
        }

        public TransformationResultBuilder withInfo(String info) {
            this.info = info;
            return this;
        }

        public TransformationResult build() {
            return new TransformationResult(output, info);
        }
    }
}
