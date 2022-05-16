import org.junit.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

class StringComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}

public class Drevo23Test {

    private Drevo23<String> d23;

    public Drevo23Test() {}

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        d23 = new Drevo23<>(new StringComparator());
    }

    @After
    public void tearDown() {
    }

    /** Test metod razreda <Drevo23> */

    // testi dodajanja

    @Test
    public void testAddOne() {
        d23.add("1");
    }

    @Test
    public void testAddMultiple() {
        d23.add("7");
        d23.add("1");
        d23.add("4");
        d23.add("0");
        d23.add("3");
        d23.add("2");
        d23.add("5");
        d23.add("9");
        d23.add("6");
        d23.add("8");
        d23.add("10");
        d23.add("11");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDuplicate() {
        d23.add("1");
        d23.add("2");
        d23.add("1");
    }

    @Test
    public void testAddDvovozlisceLevo() {
        d23.add("6");
        d23.add("a12");
        d23.add("8");
        d23.add("4");
        d23.add("a15");
        d23.add("5");
    }

    @Test
    public void testAddDvovozlisceDesno() {
        d23.add("6");
        d23.add("a12");
        d23.add("8");
        d23.add("4");
        d23.add("a15");
        d23.add("13");
    }

    @Test
    public void testAddTrovozlisceLevo() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");
        d23.add("13");
    }

    @Test
    public void testAddTrovozlisceSrednje() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");
        d23.add("25");
    }

    @Test
    public void testAddTrovozlisceDesno() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");
        d23.add("33");
    }

    @Test
    public void testAddNovOceZDesne() {
        d23.add("50");
        d23.add("70");
        d23.add("80");
    }

    @Test
    public void testAddPotisniGorOceDvovozliceOvojVrednostVecja() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("80");
    }

    @Test
    public void testAddPotisniGorOceTrovozliceOvojVrednostManjsaPotisniGorDrugic() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");
        d23.add("33");
    }

    @Test
    public void testAddPotisniGorDrugicNiOcetaVrednostOvojaSrednja() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("33");
    }

    @Test
    public void testAddPotisniGorDrugicNiOcetaVrednostOvojaManjsa() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("22");
    }


    // testi asList


    @Test
    public void testAsListMultiple() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");
        List list = new ArrayList();
        list.add("50");
        list.add("20");
        list.add("28");
        list.add("10");
        list.add("15");
        list.add("24");
        list.add("26");
        list.add("30");
        list.add("35");
        list.add("90");
        list.add("70");
        list.add("80");
        list.add("95");
        list.add("99");
        assertEquals(list, d23.asList());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testAsListNoElements() {
        d23.asList();
    }



    // testi asListWithBrackets


    @Test
    public void testAsListWithBracketsMultiple() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");
        List list = new ArrayList();
        list.add("(50)");
        list.add("(20, 28)");
        list.add("(10, 15)");
        list.add("(24, 26)");
        list.add("(30, 35)");
        list.add("(90)");
        list.add("(70, 80)");
        list.add("(95, 99)");

        assertEquals(list, d23.asListWithBrackets());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testAsListWithBracketsNoElements() {
        d23.asListWithBrackets();
    }



    // testi za isEmpty

    @Test
    public void testIsEmptyTrue() {
        assertTrue(d23.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        d23.add("50");
        assertFalse(d23.isEmpty());
    }


    // testi za size

    @Test
    public void testSizeMultiple() {
        d23.add("50");
        assertEquals(1, d23.size());
        d23.add("70");
        assertEquals(2, d23.size());
        d23.add("30");
        assertEquals(3, d23.size());
        d23.add("20");
        assertEquals(4, d23.size());
        d23.add("90");
        assertEquals(5, d23.size());
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");
        assertEquals(14, d23.size());
    }

    @Test
    public void testSizeEmpty() {
        assertEquals(0, d23.size());
    }


    // testi za depth

    @Test
    public void testDepthMultiple() {
        d23.add("50");
        assertEquals(1, d23.depth());
        d23.add("70");
        assertEquals(1, d23.depth());
        d23.add("30");
        assertEquals(2, d23.depth());
        d23.add("20");
        assertEquals(2, d23.depth());
        d23.add("90");
        assertEquals(2, d23.depth());
        d23.add("28");
        assertEquals(2, d23.depth());
        d23.add("24");
        assertEquals(2, d23.depth());
        d23.add("35");
        assertEquals(2, d23.depth());
        d23.add("95");
        assertEquals(3, d23.depth());
        d23.add("80");
        assertEquals(3, d23.depth());
        d23.add("99");
        assertEquals(3, d23.depth());
        d23.add("10");
        assertEquals(3, d23.depth());
        d23.add("15");
        assertEquals(3, d23.depth());
        d23.add("26");
        assertEquals(3, d23.depth());
    }

    @Test
    public void testDepthEmpty() {
        assertEquals(0, d23.depth());
    }


    //testi za getFirst

    @Test
    public void testGetFirstMultiple() {
        d23.add("50");
        assertEquals("50", d23.getFirst());
        d23.add("70");
        assertEquals("50", d23.getFirst());
        d23.add("30");
        assertEquals("50", d23.getFirst());
        d23.add("20");
        assertEquals("50", d23.getFirst());
        d23.add("90");
        assertEquals("50", d23.getFirst());
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");
        assertEquals("50", d23.getFirst());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testGetFirstEmpty() {
        d23.getFirst();
    }


    //testi za exists

    @Test
    public void testExistsMultiple() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");

        assertTrue(d23.exists("50"));
        assertTrue(d23.exists("28"));
        assertTrue(d23.exists("10"));
        assertTrue(d23.exists("26"));
        assertTrue(d23.exists("35"));
        assertTrue(d23.exists("90"));
        assertTrue(d23.exists("80"));
        assertTrue(d23.exists("99"));

        assertFalse(d23.exists("1"));
        assertFalse(d23.exists("101"));
    }

    @Test
    public void testExistsEmpty() {
        assertFalse(d23.exists("50"));
    }


    //testi za remove

    @Test
    public void testRemoveMultiple() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");

        assertEquals("10", d23.remove("10"));
        assertEquals("15", d23.remove("15"));
        assertEquals("24", d23.remove("24"));
        assertEquals("26", d23.remove("26"));
        assertEquals("30", d23.remove("30"));
        assertEquals("90", d23.remove("90"));
        assertEquals("50", d23.remove("50"));
        assertEquals("70", d23.remove("70"));
        assertEquals("20", d23.remove("20"));
        assertEquals("28", d23.remove("28"));
        assertEquals("35", d23.remove("35"));
        assertEquals("95", d23.remove("95"));
        assertEquals("80", d23.remove("80"));
        assertEquals("99", d23.remove("99"));

    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveEmpty() {
        d23.remove("50");
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveNoElement() {
        d23.add("10");
        d23.add("15");
        d23.add("26");
        d23.remove("50");
    }

    @Test
    public void testRemoveEInB() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");

        assertEquals("15", d23.remove("15"));
    }

    @Test
    public void testRemoveNotranjeVozlisceDesno() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");

        assertEquals("30", d23.remove("30"));
    }

    @Test
    public void testRemoveNotranjeVozlisceSrednje() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");

        assertEquals("24", d23.remove("24"));
    }

    @Test
    public void testRemoveNotranjeVozlisceTrovozlisceBrisemoDrugega() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");

        assertEquals("28", d23.remove("28"));
    }

    @Test
    public void testRemoveListLeviOtrokSrednjiBratNimaDvehVrednostiOceNimaDvehVrednosti() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");

        assertEquals("10", d23.remove("10"));
        assertEquals("15", d23.remove("15"));
        assertEquals("24", d23.remove("24"));
        assertEquals("26", d23.remove("26"));
        assertEquals("30", d23.remove("30"));
        assertEquals("90", d23.remove("90"));
        assertEquals("20", d23.remove("20"));
    }

    @Test
    public void testRemoveListLeviOtrokSrednjiBratNimaDvehVrednostiOceImaDveVrednosti() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");

        assertEquals("10", d23.remove("10"));
        assertEquals("15", d23.remove("15"));
        assertEquals("20", d23.remove("20"));
    }

    @Test
    public void testRemoveListSrednjiOtrokLeviBratImaDveVrednosti() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");

        assertEquals("26", d23.remove("26"));
        assertEquals("24", d23.remove("24"));
    }

    @Test
    public void testRemoveListDesniOtrokSrednjiBratImaDveVrednosti() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");

        assertEquals("35", d23.remove("35"));
        assertEquals("30", d23.remove("30"));
    }

    @Test
    public void testRemoveListDesniOtrokSrednjiBratNimaDvehVrednosti() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");

        assertEquals("35", d23.remove("35"));
        assertEquals("26", d23.remove("26"));
        assertEquals("30", d23.remove("30"));
    }

    @Test
    public void testRemoveResiLuknjoLeviOtrokSrednjiBratImaDveVrednosti() {
        d23.add("10");
        d23.add("03");
        d23.add("15");
        d23.add("02");
        d23.add("04");
        d23.add("14");
        d23.add("16");
        d23.add("18");
        d23.add("20");

        assertEquals("02", d23.remove("02"));
    }

    @Test
    public void testRemoveResiLuknjoLeviOtrokSrednjiBratNimaDveVrednostiOceImaDveVrednosti() {
        d23.add("10");
        d23.add("03");
        d23.add("15");
        d23.add("02");
        d23.add("04");
        d23.add("14");
        d23.add("16");
        d23.add("18");
        d23.add("20");
        d23.add("25");
        d23.add("30");

        assertEquals("02", d23.remove("02"));
    }

    @Test
    public void testRemoveResiLuknjoLeviOtrokSrednjiBratNimaDveVrednostiOceNimaDvehVrednosti() {
        d23.add("10");
        d23.add("03");
        d23.add("15");
        d23.add("02");
        d23.add("04");
        d23.add("14");
        d23.add("16");

        assertEquals("02", d23.remove("02"));
    }

    @Test
    public void testRemoveResiLuknjoSrednjiOtrokLeviBratImaDveVrednosti() {
        d23.add("10");
        d23.add("03");
        d23.add("15");
        d23.add("02");
        d23.add("04");
        d23.add("14");
        d23.add("16");
        d23.add("05");
        d23.add("06");

        assertEquals("14", d23.remove("14"));
    }

    @Test
    public void testRemoveResiLuknjoSrednjiOtrokLeviBratNimaDvehVrednostiDesniBratImaDveVrednosti() {
        d23.add("10");
        d23.add("03");
        d23.add("15");
        d23.add("02");
        d23.add("04");
        d23.add("14");
        d23.add("16");
        d23.add("18");
        d23.add("20");
        d23.add("25");
        d23.add("30");
        d23.add("35");
        d23.add("40");

        assertEquals("14", d23.remove("14"));
    }

    @Test
    public void testRemoveResiLuknjoSrednjiOtrokLeviBratNimaDvehVrednostiOceImaDveVrednosti() {
        d23.add("10");
        d23.add("03");
        d23.add("15");
        d23.add("02");
        d23.add("04");
        d23.add("14");
        d23.add("16");
        d23.add("18");
        d23.add("20");
        d23.add("25");
        d23.add("30");

        assertEquals("14", d23.remove("14"));
    }

    @Test
    public void testRemoveResiLuknjoDesniOtrokSrednjiBratImaDveVrednosti() {
        d23.add("10");
        d23.add("03");
        d23.add("15");
        d23.add("02");
        d23.add("04");
        d23.add("14");
        d23.add("16");
        d23.add("18");
        d23.add("20");
        d23.add("25");
        d23.add("30");
        d23.add("12");
        d23.add("13");

        assertEquals("20", d23.remove("20"));
    }

    @Test
    public void testRemoveResiLuknjoDesniOtrokSrednjiBratNimaDvehVrednostiOceImaDveVrednosti() {
        d23.add("10");
        d23.add("03");
        d23.add("15");
        d23.add("02");
        d23.add("04");
        d23.add("14");
        d23.add("16");
        d23.add("18");
        d23.add("20");
        d23.add("25");
        d23.add("30");

        assertEquals("20", d23.remove("20"));
    }


    //testi za removeFirst

    @Test
    public void testRemoveFirstMultiple() {
        d23.add("10");
        d23.add("03");
        d23.add("15");
        d23.add("02");
        d23.add("04");
        d23.add("14");
        d23.add("16");
        d23.add("18");
        d23.add("20");
        d23.add("25");
        d23.add("30");

        assertEquals("10", d23.removeFirst());
        assertEquals("14", d23.removeFirst());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveFirstEmpty() {
        d23.removeFirst();
    }

    @Test
    public void resetMultipleElements() {
        String a = "1";
        String b = "2";
        String c = "3";
        d23.add(a);
        d23.add(b);
        d23.add(c);
        d23.reset();
        assertEquals(0, d23.size());
    }

    @Test
    public void resetNoElements() {
        d23.reset();
        assertEquals(0, d23.size());
    }


    //testi za search

    @Test
    public void testSearchMultiple() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");

        assertEquals("50", d23.search("50"));
        assertEquals("28", d23.search("28"));
        assertEquals("10", d23.search("10"));
        assertEquals("26", d23.search("26"));
        assertEquals("35", d23.search("35"));
        assertEquals("90", d23.search("90"));
        assertEquals("80", d23.search("80"));

    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testSearchDoesNotExist() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        d23.add("28");
        d23.add("24");
        d23.add("35");
        d23.add("95");
        d23.add("80");
        d23.add("99");
        d23.add("10");
        d23.add("15");
        d23.add("26");

        d23.search("1");
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testSearchEmpty() {
        d23.search("50");
    }


    //testi za print

    @Test
    public void testPrintMultiple() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");

        assertEquals("20\n30\n50\n70\n90", d23.print());
    }

    @Test
    public void testPrintEmpty() {
        assertEquals("", d23.print());
    }


    //testi za save

    @Test
    public void testSaveRestoreMultiple() {
        d23.add("50");
        d23.add("70");
        d23.add("30");
        d23.add("20");
        d23.add("90");
        try {
            d23.save(new FileOutputStream("testSaveRestoreMultiple"));
            d23.reset();
            d23.restore(new FileInputStream("testSaveRestoreMultiple"));

            assertEquals("20\n30\n50\n70\n90", d23.print());
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testSaveRestoreEmpty() {
        try {
            d23.save(new FileOutputStream("testSaveRestoreEmpty"));
            d23.reset();
            d23.restore(new FileInputStream("testSaveRestoreEmpty"));

            d23.print();
            assertEquals("", d23.print());
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
