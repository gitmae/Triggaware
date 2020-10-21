package com.eastglade64.transformation.parsing;

import com.eastglade64.model.exception.ParseException;
import com.eastglade64.model.measure.Measure;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MeasuresParserTest {

    @Test
    public void measuresParserTest() {
        String text = "201911" +
                "\tIT001E14923345" +
                "\t22" +
                "\tR1" +
                "\tRL:SN:17E4E5LA102441041" +
                "\t201911230434566910A:4B:13:CF:00:FE5h1trh7h8ydesRIC" +
                "\t0000000000" +
                "\n" +
                "201911" +
                "\tIT001E14915555" +
                "\t08" +
                "\t000000" +
                "\tRL:SN:16E4E5LA100214539" +
                "\t0" +
                "\t2019110805562509206:F1:C9:5F:DE:D082htveuxx5wd8RIC" +
                "\t0000000000";

        try {
            List<Measure> measures = new MeasuresParser().parse(text);

            assertEquals(2, measures.size());

            measures.forEach(m -> m.consume(
                    r -> {
                        assertEquals("201911", r.getMeasYM());
                        assertEquals("IT001E14915555", r.getPod());
                        assertEquals("08", r.getMeasDD());
                        assertEquals("000000", r.getMeasHMS());
                        assertEquals("RL:SN:16E4E5LA100214539", r.getMeterKey());
                        assertEquals("0", r.getRefPeriodId());
                        assertEquals("2019110805562509206:F1:C9:5F:DE:D082htveuxx5wd8RIC", r.getRecId());
                        assertEquals("0000000000", r.getCollectId());
                    },
                    c -> {
                        assertEquals("201911", c.getMeasYM());
                        assertEquals("IT001E14923345", c.getPod());
                        assertEquals("22", c.getMeasDD());
                        assertEquals("R1", c.getMagnitude());
                        assertEquals("RL:SN:17E4E5LA102441041", c.getMeterKey());
                        assertEquals("201911230434566910A:4B:13:CF:00:FE5h1trh7h8ydesRIC", c.getRecId());
                        assertEquals("0000000000", c.getCollectId());
                    }
            ));

        } catch (ParseException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    @Test
    public void measuresParserSpaceTest() {
        String text = "     201911" +
                "\t   IT001E14923345" +
                "\t22" +
                "\tR1" +
                "\tRL:SN:17E4E5LA102441041  " +
                "\t201911230434566910A:4B:13:CF:00:FE5h1trh7h8ydesRIC" +
                "\t0000000000" +
                "\n" +
                "201911" +
                "\t     IT001E14915555" +
                "\t08" +
                "\t000000       " +
                "\tRL:SN:16E4E5LA100214539" +
                "\t   0" +
                "\t2019110805562509206:F1:C9:5F:DE:D082htveuxx5wd8RIC" +
                "\t0000000000   ";

        try {
            List<Measure> measures = new MeasuresParser().parse(text);

            assertEquals(2, measures.size());

            measures.forEach(m -> m.consume(
                    r -> {
                        assertEquals("201911", r.getMeasYM());
                        assertEquals("IT001E14915555", r.getPod());
                        assertEquals("08", r.getMeasDD());
                        assertEquals("000000", r.getMeasHMS());
                        assertEquals("RL:SN:16E4E5LA100214539", r.getMeterKey());
                        assertEquals("0", r.getRefPeriodId());
                        assertEquals("2019110805562509206:F1:C9:5F:DE:D082htveuxx5wd8RIC", r.getRecId());
                        assertEquals("0000000000", r.getCollectId());
                    },
                    c -> {
                        assertEquals("201911", c.getMeasYM());
                        assertEquals("IT001E14923345", c.getPod());
                        assertEquals("22", c.getMeasDD());
                        assertEquals("R1", c.getMagnitude());
                        assertEquals("RL:SN:17E4E5LA102441041", c.getMeterKey());
                        assertEquals("201911230434566910A:4B:13:CF:00:FE5h1trh7h8ydesRIC", c.getRecId());
                        assertEquals("0000000000", c.getCollectId());
                    }
            ));
        } catch (ParseException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }
}
