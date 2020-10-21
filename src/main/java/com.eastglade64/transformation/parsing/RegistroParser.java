package com.eastglade64.transformation.parsing;

import com.eastglade64.model.measure.Registro;
import com.eastglade64.model.exception.ParseException;

import java.util.function.Predicate;
import java.util.regex.Pattern;

class RegistroParser implements Parser<Registro>, PlausibilityChecker {

    private final String separator;
    private final Predicate<String> measDdSeparatorMeasHmsIsPresent;

    RegistroParser(String separator) {
        this.separator = separator;
        this.measDdSeparatorMeasHmsIsPresent =
                Pattern.compile("\\d{2}" + separator + "\\d{6}").asPredicate();
    }

    @Override
    public Registro parse(String text) throws ParseException {
        try {
            String[] fields = text.split(separator);
            if (fields.length != 8) {
                throw new ParseException("Registro requires 8 fields. Fields provided instead: " + fields.length + ". Separator: '" + separator + "'. Input text: " + text);
            }
            return new Registro(
                    fields[0].trim(),
                    fields[1].trim(),
                    fields[2].trim(),
                    fields[3].trim(),
                    fields[4].trim(),
                    fields[5].trim(),
                    fields[6].trim(),
                    fields[7].trim()
            );
        } catch (Exception e) {
            throw new ParseException("Error while parsing Registro", e);
        }
    }

    @Override
    public boolean isPlausible(String text) {
        return measDdSeparatorMeasHmsIsPresent.test(text);
    }
}
