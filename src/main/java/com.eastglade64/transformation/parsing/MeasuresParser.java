package com.eastglade64.transformation.parsing;

import com.eastglade64.model.exception.ParseException;
import com.eastglade64.model.measure.Measure;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MeasuresParser implements Parser<List<Measure>> {

    private static final Pattern SEPARATOR_PATTERN = Pattern.compile("\\w([^:\\w\\n\\r]+)\\w") ;

    @Override
    public List<Measure> parse(String text) throws ParseException {
        try {
            String separator = identifySeparator(text);
            if (separator == null) {
                throw new ParseException("Unable to identify the separator");
            }
            CurvaParser curvaParser = new CurvaParser(separator);
            RegistroParser registroParser = new RegistroParser(separator);

            List<Measure> measures = new ArrayList<>();

            for (String line : text.split("\\R")) {
                if (!line.trim().isEmpty()) {
                    if (curvaParser.isPlausible(line)) {
                        measures.add(curvaParser.parse(line));
                    } else {
                        measures.add(registroParser.parse(line));
                    }
                }
            }

            return measures;
        } catch (Exception e) {
            throw new ParseException("Error while parsing Measures", e);
        }
    }

    // TODO improve separator identifier algorithm
    private String identifySeparator(String text) {
        Matcher matcher = SEPARATOR_PATTERN.matcher(text.trim());
        while (matcher.find()) {
            String separator = matcher.group(1);
            if (separator.length() == 1) {
                return separator;
            }
        }
        return null;
    }

}
