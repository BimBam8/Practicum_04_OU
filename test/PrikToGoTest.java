package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import domein.PrikToGo;
import domein.Vestiging;
import domein.Klant;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import sun.reflect.ReflectionFactory;

public class PrikToGoTest {

    private PrikToGo ptg;

    @Before
    public void setUp() throws Exception {
        // 1. Maak exact de klanten aan met de juiste constructor
        Klant k1001 = new Klant(1001, "1234AB");
        Klant k1002 = new Klant(1002, "1234AB");
        Klant k2001 = new Klant(2001, "5678CD");
        Klant k2002 = new Klant(2002, "5678CD");

        // 2. Maak de twee vestigingen aan (Amsterdam = index 0, Rotterdam = index 1)
        Vestiging vAmd = new Vestiging("Amsterdam", "1234AB", new Klant[]{k1001, k1002});
        Vestiging vRdm = new Vestiging("Rotterdam", "5678CD", new Klant[]{k2001, k2002});
        Vestiging[] testArray = new Vestiging[]{vAmd, vRdm};

        // 3. Omzeil de database-aanroep uit de constructor volledig
        ReflectionFactory rf = ReflectionFactory.getReflectionFactory();
        Constructor<?> objectConstructor = Object.class.getDeclaredConstructor();
        Constructor<?> prikToGoConstructor = rf.newConstructorForSerialization(PrikToGo.class, objectConstructor);
        ptg = (PrikToGo) prikToGoConstructor.newInstance();

        // 4. Injecteer de testdata rechtstreeks in de private variabele 'vestigingen'
        Field vestigingenField = PrikToGo.class.getDeclaredField("vestigingen");
        vestigingenField.setAccessible(true);
        vestigingenField.set(ptg, testArray);
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
