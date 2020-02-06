package com.sber.rupassword;

import com.sber.rupassword.repository.PasswordHelper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PasswordHelperTest {

    private static final String[] RUS = {"й", "ц", "у", "к", "е", "н"};
    private static final String[] ENG = {"q", "w", "e", "r", "t", "y"};
    private static final String[] SOURCES = {
            "",
            "некуцй",
            "НЕКУЦЙ",
    };
    private static final String[] RESULTS = {
            "",
            "ytrewq",
            "YTREWQ",
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

    @Test
    public void entropy() {
        String abc = "abc";
        String ABC = "ABC";
        String a1 = "a1";
        String A1 = "A1";
        String aA1 = "aA1";
        assertEquals(14, helper.calculateStrength(abc));
        assertEquals(14, helper.calculateStrength(ABC));
        assertEquals(10, helper.calculateStrength(a1));
        assertEquals(10, helper.calculateStrength(A1));
        assertEquals(18, helper.calculateStrength(aA1));
    }
}