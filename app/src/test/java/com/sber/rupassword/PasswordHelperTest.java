package com.sber.rupassword;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordHelperTest {

    private static final String[] RUS = {"й","ц","у","к","е","н" };
    private static final String[] ENG = {"q","w","e","r","t","y" };
    public static final String[] SOURCES = {
            "",
            "некуцй",
            "НЕКУЦЙ",
            "ъхзщшгн"
    };
    public static final String[] RESULTS = {
            "",
            "ytrewq",
            "YTREWQ",
            "ъхзщшгy"

    };
    private PasswordHelper helper;

    @Before
    public void setUp() throws Exception {
        helper = new PasswordHelper(RUS, ENG);
    }

    @Test
    public void convert() {
        assertTrue("Error in test case", SOURCES.length == RESULTS.length);
        for (int i = 0; i < SOURCES.length; i++) {
            assertEquals(RESULTS[i], helper.convert(SOURCES[i]));

        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void name() {
    }
}