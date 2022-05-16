import org.easymock.EasyMock;
import org.junit.*;

import java.io.*;

import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

public class SeznamiUVTest {

    SeznamiUV uv;

    public SeznamiUVTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        uv = new SeznamiUV();
    }

    @After
    public void tearDown() {
    }


    //Testi za add

    @Test
    public void testAdd() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processEmso("1234567891234"));
        assertEquals("add> SURNAME: ", uv.processIme("Janez"));
        assertEquals("add> AGE: ", uv.processPriimek("Janez", "Novak"));
        assertEquals("add> VACCINE: ", uv.processStarost("30"));
        assertEquals("", uv.processCepivo("Moderna"));
        assertEquals(">>OK", uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna"));
    }

    @Test
    public void testAddEmsoInvalidTooShort() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals(">>Invalid input data", uv.processEmso("12"));
    }

    @Test
    public void testAddEmsoInvalidNotANumber() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals(">>Invalid input data", uv.processEmso("xxxxxxxxxxxxx"));
    }

    @Test
    public void testAddEmsoAlreadyExists() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals(">>Person already exists", uv.processEmso("1234567891234"));
    }

    @Test
    public void testAddImePriimekAlreadyExists() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processEmso("1234567891235"));
        assertEquals("add> SURNAME: ", uv.processIme("Janez"));
        assertEquals(">>Person already exists", uv.processPriimek("Janez", "Novak"));
    }

    @Test
    public void testAddInvalidAgeTooBig() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processEmso("1234567891235"));
        assertEquals("add> SURNAME: ", uv.processIme("Janez"));
        assertEquals("add> AGE: ", uv.processPriimek("Janez", "Potočnik"));
        assertEquals(">>Invalid input data", uv.processStarost("999"));
    }

    @Test
    public void testAddInvalidAgeNotANumber() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processEmso("1234567891235"));
        assertEquals("add> SURNAME: ", uv.processIme("Janez"));
        assertEquals("add> AGE: ", uv.processPriimek("Janez", "Potočnik"));
        assertEquals(">>Invalid input data", uv.processStarost("x"));
    }

    @Test
    public void testAddInvalidCepivo() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processEmso("1234567891235"));
        assertEquals("add> SURNAME: ", uv.processIme("Janez"));
        assertEquals("add> AGE: ", uv.processPriimek("Janez", "Potočnik"));
        assertEquals("add> VACCINE: ", uv.processStarost("30"));
        assertEquals(">>Invalid input data", uv.processCepivo("Kr neki"));
    }

    @Test
    public void testAddInvalidArgument() {
        assertEquals(">>Invalid argument", uv.processInput("add x"));
    }


    //Testi za remove

    @Test
    public void testRemoveByEmsoMultiple() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        uv.processAddDrzavljan("1234567891235", "Tone", "Potočnik", "40", "Pfizer");
        uv.processAddDrzavljan("1234567891236", "Lojze", "Horvat", "50", "AstraZeneca");

        assertEquals(">>No. of Persons: 3", uv.processInput("size"));

        assertEquals(">>OK", uv.processInput("remove 1234567891235"));
        assertEquals(">>No. of Persons: 2", uv.processInput("size"));

        assertEquals(">>OK", uv.processInput("remove 1234567891234"));
        assertEquals(">>No. of Persons: 1", uv.processInput("size"));

        assertEquals(">>OK", uv.processInput("remove 1234567891236"));
        assertEquals(">>No. of Persons: 0", uv.processInput("size"));
    }

    @Test
    public void testRemoveByEmsoDoesNotExist() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        uv.processAddDrzavljan("1234567891235", "Tone", "Potočnik", "40", "Pfizer");
        uv.processAddDrzavljan("1234567891236", "Lojze", "Horvat", "50", "AstraZeneca");

        assertEquals(">>No. of Persons: 3", uv.processInput("size"));

        assertEquals(">>Person does not exist", uv.processInput("remove 1234567891239"));

        assertEquals(">>No. of Persons: 3", uv.processInput("size"));
    }

    @Test
    public void testRemoveByEmsoEmsoInvalid() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        uv.processAddDrzavljan("1234567891235", "Tone", "Potočnik", "40", "Pfizer");
        uv.processAddDrzavljan("1234567891236", "Lojze", "Horvat", "50", "AstraZeneca");

        assertEquals(">>No. of Persons: 3", uv.processInput("size"));

        assertEquals(">>Invalid input data", uv.processInput("remove 12"));

        assertEquals(">>No. of Persons: 3", uv.processInput("size"));
    }

    @Test
    public void testRemoveByEmsoEmpty() {
        assertEquals(">>Person does not exist", uv.processInput("remove 1234567891239"));
    }

    @Test
    public void testRemoveByName() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        uv.processAddDrzavljan("1234567891235", "Tone", "Potočnik", "40", "Pfizer");
        uv.processAddDrzavljan("1234567891236", "Lojze", "Horvat", "50", "AstraZeneca");

        assertEquals(">>No. of Persons: 3", uv.processInput("size"));

        assertEquals("remove> NAME: ", uv.processInput("remove"));
        assertEquals("remove> SURNAME: ", uv.processRemoveIme("Tone"));
        assertEquals(">>OK", uv.processRemovePriimek("Tone", "Potočnik"));

        assertEquals(">>No. of Persons: 2", uv.processInput("size"));
    }

    @Test
    public void testRemoveByNamePersonDoesNotExist() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        uv.processAddDrzavljan("1234567891235", "Tone", "Potočnik", "40", "Pfizer");
        uv.processAddDrzavljan("1234567891236", "Lojze", "Horvat", "50", "AstraZeneca");

        assertEquals(">>No. of Persons: 3", uv.processInput("size"));

        assertEquals("remove> NAME: ", uv.processInput("remove"));
        assertEquals("remove> SURNAME: ", uv.processRemoveIme("Tone"));
        assertEquals(">>Person does not exist", uv.processRemovePriimek("Tone", "Novak"));

        assertEquals(">>No. of Persons: 3", uv.processInput("size"));
    }

    @Test
    public void testRemoveInvalidArgument() {
        assertEquals(">>Invalid argument", uv.processInput("remove 1234567891234 x"));
    }


    //Testi za search

    @Test
    public void testSearchByEmsoMultiple() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        uv.processAddDrzavljan("1234567891235", "Tone", "Potočnik", "40", "Pfizer");
        uv.processAddDrzavljan("1234567891236", "Lojze", "Horvat", "50", "AstraZeneca");

        assertEquals(">>1234567891235\tPotočnik, Tone\t40\tPfizer", uv.processInput("search 1234567891235"));
        assertEquals(">>1234567891234\tNovak, Janez\t30\tModerna", uv.processInput("search 1234567891234"));
        assertEquals(">>1234567891236\tHorvat, Lojze\t50\tAstraZeneca", uv.processInput("search 1234567891236"));
    }

    @Test
    public void testSearchByEmsoDoesNotExist() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        uv.processAddDrzavljan("1234567891235", "Tone", "Potočnik", "40", "Pfizer");
        uv.processAddDrzavljan("1234567891236", "Lojze", "Horvat", "50", "AstraZeneca");

        assertEquals(">>Person does not exist", uv.processInput("search 1234567891239"));
    }

    @Test
    public void testSearchByEmsoEmsoInvalid() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        uv.processAddDrzavljan("1234567891235", "Tone", "Potočnik", "40", "Pfizer");
        uv.processAddDrzavljan("1234567891236", "Lojze", "Horvat", "50", "AstraZeneca");

        assertEquals(">>Invalid input data", uv.processInput("search 12"));
    }

    @Test
    public void testSearchByEmsoEmpty() {
        assertEquals(">>Person does not exist", uv.processInput("search 1234567891239"));
    }

    @Test
    public void testSearchByName() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        uv.processAddDrzavljan("1234567891235", "Tone", "Potočnik", "40", "Pfizer");
        uv.processAddDrzavljan("1234567891236", "Lojze", "Horvat", "50", "AstraZeneca");

        assertEquals("search> NAME: ", uv.processInput("search"));
        assertEquals("search> SURNAME: ", uv.processSearchIme("Tone"));
        assertEquals(">>1234567891235\tPotočnik, Tone\t40\tPfizer", uv.processSearchPriimek("Tone", "Potočnik"));
    }

    @Test
    public void testSearchByNamePersonDoesNotExist() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        uv.processAddDrzavljan("1234567891235", "Tone", "Potočnik", "40", "Pfizer");
        uv.processAddDrzavljan("1234567891236", "Lojze", "Horvat", "50", "AstraZeneca");

        assertEquals("search> NAME: ", uv.processInput("search"));
        assertEquals("search> SURNAME: ", uv.processSearchIme("Tone"));
        assertEquals(">>Person does not exist", uv.processSearchPriimek("Tone", "Novak"));
    }

    @Test
    public void testSearchInvalidArgument() {
        assertEquals(">>Invalid argument", uv.processInput("search 1234567891234 x"));
    }


    //testi za reset

    @Test
    public void testResetOnEmpty() {
        assertEquals("reset> Are you sure (y|n): ", uv.processInput("reset"));
    }

    @Test
    public void testResetOnFullResponseYes() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        uv.processAddDrzavljan("1234567891235", "Tone", "Potočnik", "40", "Pfizer");
        uv.processAddDrzavljan("1234567891236", "Lojze", "Horvat", "50", "AstraZeneca");

        assertEquals(">>No. of Persons: 3", uv.processInput("size"));

        assertEquals("reset> Are you sure (y|n): ", uv.processInput("reset"));
        assertEquals(">>OK", uv.processReset("y"));

        assertEquals(">>No. of Persons: 0", uv.processInput("size"));
    }

    @Test
    public void testResetOnFullResponseNo() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        uv.processAddDrzavljan("1234567891235", "Tone", "Potočnik", "40", "Pfizer");
        uv.processAddDrzavljan("1234567891236", "Lojze", "Horvat", "50", "AstraZeneca");

        assertEquals(">>No. of Persons: 3", uv.processInput("size"));

        assertEquals("reset> Are you sure (y|n): ", uv.processInput("reset"));
        assertEquals(">>OK", uv.processReset("n"));

        assertEquals(">>No. of Persons: 3", uv.processInput("size"));
    }

    @Test
    public void testResetOnFullResponseInvalid() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        uv.processAddDrzavljan("1234567891235", "Tone", "Potočnik", "40", "Pfizer");
        uv.processAddDrzavljan("1234567891236", "Lojze", "Horvat", "50", "AstraZeneca");

        assertEquals(">>No. of Persons: 3", uv.processInput("size"));

        assertEquals("reset> Are you sure (y|n): ", uv.processInput("reset"));
        assertEquals(">>Invalid argument", uv.processReset("Kr neki"));

        assertEquals(">>No. of Persons: 3", uv.processInput("size"));
    }

    @Test
    public void testResetInvalidArgument() {
        assertEquals(">>Invalid argument", uv.processInput("reset x"));
    }


    //testi za size

    @Test
    public void testSizeOnEmpty() {
        assertEquals(">>No. of Persons: 0", uv.processInput("size"));
    }

    @Test
    public void testSizeMultiple() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        uv.processAddDrzavljan("1234567891235", "Tone", "Potočnik", "40", "Pfizer");
        uv.processAddDrzavljan("1234567891236", "Lojze", "Horvat", "50", "AstraZeneca");

        assertEquals(">>No. of Persons: 3", uv.processInput("size"));
    }

    @Test
    public void testSizeInvalidArgument() {
        assertEquals(">>Invalid argument", uv.processInput("size x"));
    }


    //testi za print

    @Test
    public void testPrintOnEmpty() {
        assertEquals(">>No. of Persons: 0", uv.processInput("print"));
    }

    @Test
    public void testPrintMultiple() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        uv.processAddDrzavljan("1234567891235", "Tone", "Potočnik", "40", "Pfizer");
        uv.processAddDrzavljan("1234567891236", "Lojze", "Horvat", "50", "AstraZeneca");

        assertEquals(">>No. of Persons: 3\n1234567891236\tHorvat, Lojze\t50\tAstraZeneca\n1234567891234\tNovak, Janez\t30\tModerna\n1234567891235\tPotočnik, Tone\t40\tPfizer", uv.processInput("print"));
    }

    @Test
    public void testPrintInvalidArgument() {
        assertEquals(">>Invalid argument", uv.processInput("print x"));
    }


    //testi za save in restore

    @Test
    public void testSaveAndRestoreMultiple() {
        uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna");
        uv.processAddDrzavljan("1234567891235", "Tone", "Potočnik", "40", "Pfizer");
        uv.processAddDrzavljan("1234567891236", "Lojze", "Horvat", "50", "AstraZeneca");

        assertEquals(">>No. of Persons: 3\n1234567891236\tHorvat, Lojze\t50\tAstraZeneca\n1234567891234\tNovak, Janez\t30\tModerna\n1234567891235\tPotočnik, Tone\t40\tPfizer", uv.processInput("print"));

        assertEquals(">>OK", uv.processInput("save testSaveAndRestore"));

        uv.processReset("y");

        assertEquals(">>No. of Persons: 0", uv.processInput("print"));

        assertEquals(">>OK", uv.processInput("restore testSaveAndRestore"));

        assertEquals(">>No. of Persons: 3\n1234567891236\tHorvat, Lojze\t50\tAstraZeneca\n1234567891234\tNovak, Janez\t30\tModerna\n1234567891235\tPotočnik, Tone\t40\tPfizer", uv.processInput("print"));

        File file1 = new File("testSaveAndRestore_p");
        File file2 = new File("testSaveAndRestore_e");
        file1.delete();
        file2.delete();
    }

    @Test
    public void testSaveAndRestoreEmpty() {
        assertEquals(">>No. of Persons: 0", uv.processInput("print"));

        assertEquals(">>OK", uv.processInput("save testSaveAndRestore"));

        uv.processReset("y");

        assertEquals(">>No. of Persons: 0", uv.processInput("print"));

        assertEquals(">>OK", uv.processInput("restore testSaveAndRestore"));

        assertEquals(">>No. of Persons: 0", uv.processInput("print"));

        File file1 = new File("testSaveAndRestore_p");
        File file2 = new File("testSaveAndRestore_e");
        file1.delete();
        file2.delete();
    }

    @Test
    public void testSaveAndRestoreInvalidArgument() {
        assertEquals(">>Invalid argument", uv.processInput("save testFile x"));
        assertEquals(">>Invalid argument", uv.processInput("restore testFile x"));
    }


    //drugi testi

    @Test
    public void testEmptyCommand() {
        assertEquals("", uv.processInput(""));
    }

    @Test
    public void testExit() {
        assertEquals(">>Bye", uv.processInput("exit"));
    }

    @Test
    public void testInvalidCommand() {
        assertEquals(">>Invalid command", uv.processInput("x"));
    }

    @Test
    public void testDrevo23MockUnsupportedOperationsForFullCoverage() {
        Drevo23Mock<Drzavljan> d23Mock = new Drevo23Mock<>();
        try { d23Mock.removeFirst(); } catch (UnsupportedOperationException e) { }
        try { d23Mock.getFirst(); } catch (UnsupportedOperationException e) { }
        try { d23Mock.exists(new Drzavljan()); } catch (UnsupportedOperationException e) { }
        try { d23Mock.size(); } catch (UnsupportedOperationException e) { }
        try { d23Mock.depth(); } catch (UnsupportedOperationException e) { }
        try { d23Mock.isEmpty(); } catch (UnsupportedOperationException e) { }
        try { d23Mock.print(); } catch (UnsupportedOperationException e) { }
        try { d23Mock.asList(); } catch (UnsupportedOperationException e) { }
        try { d23Mock.reset(); } catch (UnsupportedOperationException e) { }
        try { d23Mock.search(new Drzavljan()); } catch (UnsupportedOperationException e) { }
    }


    //testi za izjemne scenarije


    @Test
    public void testAddMemoryFullMock() {
        Drevo23Mock<Drzavljan> d23Mock1 = new Drevo23Mock<>();
        Drevo23Mock<Drzavljan> d23Mock2 = new Drevo23Mock<>();
        uv.seznamPoImenu = d23Mock1;
        uv.seznamPoEmso = d23Mock2;
        assertEquals(">>Error: not enough memory, operation failed", uv.processAddDrzavljan("1234567891234", "Janez", "Novak", "30", "Moderna"));
    }

    @Test
    public void testSaveIOErrorMock() {
        Drevo23Mock<Drzavljan> d23Mock1 = new Drevo23Mock<>();
        Drevo23Mock<Drzavljan> d23Mock2 = new Drevo23Mock<>();
        uv.seznamPoImenu = d23Mock1;
        uv.seznamPoEmso = d23Mock2;
        assertEquals(">>I/O Error Writing the file failed.", uv.processInput("save testSaveMock"));

        File file1 = new File("testSaveMock_p");
        File file2 = new File("testSaveMock_e");
        file1.delete();
        file2.delete();
    }

    @Test
    public void testRestoreClassNotFoundMock() {
        Drevo23Mock<Drzavljan> d23Mock1 = new Drevo23Mock<>();
        Drevo23Mock<Drzavljan> d23Mock2 = new Drevo23Mock<>();
        uv.seznamPoImenu = d23Mock1;
        uv.seznamPoEmso = d23Mock2;
        assertEquals(">>Unknown format", uv.processInput("restore testSaveMock"));
    }

    @Test
    public void testUnsupportedOperationMock() {
        Drevo23Mock<Drzavljan> d23Mock1 = new Drevo23Mock<>();
        Drevo23Mock<Drzavljan> d23Mock2 = new Drevo23Mock<>();
        uv.seznamPoImenu = d23Mock1;
        uv.seznamPoEmso = d23Mock2;
        assertEquals(">>Invalid command", uv.processInput("remove 1234567891234"));
    }

    @Test(expected = IOException.class)
    public void testSaveIOErrorEasyMock() throws IOException {
        Seznam d23Mock = EasyMock.createMock(Seznam.class);
        FileOutputStream fileOutputStream = new FileOutputStream("datoteka");
        d23Mock.save(fileOutputStream);
        expectLastCall().andThrow(new IOException("Writing the file failed."));
        replay(d23Mock);
        d23Mock.save(fileOutputStream);
    }
}
