package com.eastglade64.transformation.parsing;

import com.eastglade64.model.measure.Curva;
import com.eastglade64.model.exception.ParseException;

import java.util.function.Predicate;
import java.util.regex.Pattern;

class CurvaParser implements Parser<Curva>, PlausibilityChecker {

    private final String separator;
    private final Predicate<String> magnitudeIsPresent;

    CurvaParser(String separator) {
        this.separator = separator;
        this.magnitudeIsPresent = Pattern.compile(separator + "[AR]\\d" + separator).asPredicate();
    }

    @Override
    public Curva parse(String text) throws ParseException {
        try {
            String[] fields = text.split(separator);
            if (fields.length != 7) {
                throw new ParseException("Curva requires 7 fields. Fields provided instead: " + fields.length + ". Separator: '" + separator + "'. Input text: " + text);
            }
            return new Curva(
                    fields[0].trim(),
                    fields[1].trim(),
                    fields[2].trim(),
                    fields[3].trim(),
                    fields[4].trim(),
                    fields[5].trim(),
                    fields[6].trim()
            );
        } catch (Exception e) {
            throw new ParseException("Error while parsing Curva", e);
        }
    }

    @Override
    public boolean isPlausible(String text) {
        return magnitudeIsPresent.test(text);
    }
}
