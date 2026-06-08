package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import domein.PrikToGo;

public class PrikToGoTest {

    private PrikToGo ptg;

    @Before
    public void setUp() {
        ptg = new PrikToGo();
    }

    @Test
    public void testGetOverzichtVestigingen() {
        String[] vestigingen = ptg.getOverzichtVestigingen();
        assertEquals(2, vestigingen.length);
        assertEquals("Amsterdam", vestigingen[0]);
        assertEquals("Rotterdam", vestigingen[1]);
    }

    @Test
    public void testSelecteerVestiging() {
        String[] klanten = ptg.selecteerVestiging(0);
        assertEquals(2, klanten.length);
        assertEquals("1001", klanten[0]);
        assertEquals("1002", klanten[1]);
    }

    @Test
    public void testSelecteerVestigingRdam() {
        String[] klanten = ptg.selecteerVestiging(1);
        assertEquals(2, klanten.length);
        assertEquals("2001", klanten[0]);
        assertEquals("2002", klanten[1]);
    }
}