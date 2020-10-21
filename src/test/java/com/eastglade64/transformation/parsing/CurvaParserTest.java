package com.eastglade64.transformation.parsing;

import com.eastglade64.model.measure.Curva;
import com.eastglade64.model.exception.ParseException;
import org.junit.Test;

import static org.junit.Assert.*;

public class CurvaParserTest {

    @Test
    public void curvaPlausibilityTest() {
        CurvaParser p = new CurvaParser("\t");

        String good = "201911" +
                "\tIT001E14923345" +
                "\t22" +
                "\tR1" +
                "\tRL:SN:17E4E5LA102441041" +
                "\t201911230434566910A:4B:13:CF:00:FE5h1trh7h8ydesRIC" +
                "\t0000000000";

        String bad = "201911" +
                "\tIT001E14923345" +
                "\t22" +
                "\tRX" + // not a valid magnitude
                "\tRL:SN:17E4E5LA102441041" +
                "\t201911230434566910A:4B:13:CF:00:FE5h1trh7h8ydesRIC" +
                "\t0000000000";

        assertTrue(p.isPlausible(good));
        assertFalse(p.isPlausible(bad));
    }

    @Test
    public void curvaParserTest() {
        String text = "201911" +
                "\tIT001E14923345" +
                "\t22" +
                "\tR1" +
                "\tRL:SN:17E4E5LA102441041" +
                "\t201911230434566910A:4B:13:CF:00:FE5h1trh7h8ydesRIC" +
                "\t0000000000";

        try {
            Curva c = new CurvaParser("\t").parse(text);
            assertEquals("201911", c.getMeasYM());
            assertEquals("IT001E14923345", c.getPod());
            assertEquals("22", c.getMeasDD());
            assertEquals("R1", c.getMagnitude());
            assertEquals("RL:SN:17E4E5LA102441041", c.getMeterKey());
            assertEquals("201911230434566910A:4B:13:CF:00:FE5h1trh7h8ydesRIC", c.getRecId());
            assertEquals("0000000000", c.getCollectId());
        } catch (ParseException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void curvaParserSpaceTest() {
        String text = "     201911" +
                "\tIT001E14923345" +
                "\t22   " +
                "\tR1" +
                "\t   RL:SN:17E4E5LA102441041" +
                "\t201911230434566910A:4B:13:CF:00:FE5h1trh7h8ydesRIC" +
                "\t0000000000 ";

        try {
            Curva c = new CurvaParser("\t").parse(text);
            assertEquals("201911", c.getMeasYM());
            assertEquals("IT001E14923345", c.getPod());
            assertEquals("22", c.getMeasDD());
            assertEquals("R1", c.getMagnitude());
            assertEquals("RL:SN:17E4E5LA102441041", c.getMeterKey());
            assertEquals("201911230434566910A:4B:13:CF:00:FE5h1trh7h8ydesRIC", c.getRecId());
            assertEquals("0000000000", c.getCollectId());
        } catch (ParseException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
