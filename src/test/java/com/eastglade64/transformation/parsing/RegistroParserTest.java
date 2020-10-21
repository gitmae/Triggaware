package com.eastglade64.transformation.parsing;

import com.eastglade64.model.measure.Registro;
import com.eastglade64.model.exception.ParseException;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistroParserTest {

    @Test
    public void registroPlausibilityTest() {
        RegistroParser p = new RegistroParser("\t");

        String good = "201911" +
                "\tIT001E14915555" +
                "\t08" +
                "\t000000" +
                "\tRL:SN:16E4E5LA100214539" +
                "\t0" +
                "\t2019110805562509206:F1:C9:5F:DE:D082htveuxx5wd8RIC" +
                "\t0000000000";

        String bad = "201911" +
                "\tIT001E14915555" +
                "\t08" +
                "\t00000" + // a digit is missing
                "\tRL:SN:16E4E5LA100214539" +
                "\t0" +
                "\t2019110805562509206:F1:C9:5F:DE:D082htveuxx5wd8RIC" +
                "\t0000000000";

        assertTrue(p.isPlausible(good));
        assertFalse(p.isPlausible(bad));
    }
    @Test
    public void registroParserTest() {
        String text = "201911" +
                "\tIT001E14915555" +
                "\t08" +
                "\t000000" +
                "\tRL:SN:16E4E5LA100214539" +
                "\t0" +
                "\t2019110805562509206:F1:C9:5F:DE:D082htveuxx5wd8RIC" +
                "\t0000000000";

        try {
            Registro r = new RegistroParser("\t").parse(text);
            assertEquals("201911", r.getMeasYM());
            assertEquals("IT001E14915555", r.getPod());
            assertEquals("08", r.getMeasDD());
            assertEquals("000000", r.getMeasHMS());
            assertEquals("RL:SN:16E4E5LA100214539", r.getMeterKey());
            assertEquals("0", r.getRefPeriodId());
            assertEquals("2019110805562509206:F1:C9:5F:DE:D082htveuxx5wd8RIC", r.getRecId());
            assertEquals("0000000000", r.getCollectId());
        } catch (ParseException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    @Test
    public void registroParserSpaceTest() {
        String text = "    201911" +
                "\tIT001E14915555" +
                "\t08" +
                "\t000000    " +
                "\tRL:SN:16E4E5LA100214539" +
                "\t0" +
                "\t    2019110805562509206:F1:C9:5F:DE:D082htveuxx5wd8RIC" +
                "\t0000000000 ";

        try {
            Registro r = new RegistroParser("\t").parse(text);
            assertEquals("201911", r.getMeasYM());
            assertEquals("IT001E14915555", r.getPod());
            assertEquals("08", r.getMeasDD());
            assertEquals("000000", r.getMeasHMS());
            assertEquals("RL:SN:16E4E5LA100214539", r.getMeterKey());
            assertEquals("0", r.getRefPeriodId());
            assertEquals("2019110805562509206:F1:C9:5F:DE:D082htveuxx5wd8RIC", r.getRecId());
            assertEquals("0000000000", r.getCollectId());
        } catch (ParseException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }
}
