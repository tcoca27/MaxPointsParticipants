package ssvv.example;

import org.junit.Test;
import ssvv.example.repository.NotaXMLRepository;
import ssvv.example.repository.StudentXMLRepository;
import ssvv.example.repository.TemaXMLRepository;
import ssvv.example.service.Service;
import ssvv.example.validation.NotaValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.TemaValidator;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private Service service;
    private StudentXMLRepository studentXMLRepository;
    private TemaXMLRepository temaXMLRepository;
    private NotaXMLRepository notaXMLRepository;
    private Random rnd;

    public AppTest() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator();

        studentXMLRepository = new StudentXMLRepository(studentValidator, "studenti-test.xml");
        notaXMLRepository = new NotaXMLRepository(notaValidator, "note-text.xml");
        temaXMLRepository = new TemaXMLRepository(temaValidator, "teme-test.xml");
        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
        rnd = new Random();
    }

    @Test
    public void test1() {
        int res = service.saveStudent(getRandomNumberString(), getAlphaString(), 111);
        assertEquals(0, res);
    }

    @Test
    public void test2() {
        int res1 = service.saveStudent("", getAlphaString(), 188);
        int res2 = service.saveStudent(null, getAlphaString(), 188);
        assertEquals(1, res1);
        assertEquals(1, res2);
    }

    @Test
    public void test3() {
        int res1 = service.saveStudent(getRandomNumberString(), "", 188);
        int res2 = service.saveStudent(getRandomNumberString(), null, 188);
        assertEquals(1, res1);
        assertEquals(1, res2);
    }

    @Test
    public void test4() {
        int res1 = service.saveStudent(getRandomNumberString(), "n@me", 188);
        assertEquals(1, res1);
    }

    @Test
    public void test6() {
        int res1 = service.saveStudent(getRandomNumberString(), getAlphaString(), 110);
        assertEquals(1, res1);
    }

    @Test
    public void test7() {
        int res1 = service.saveStudent(getRandomNumberString(), getAlphaString(), 938);
        assertEquals(1, res1);
    }

    @Test
    public void test8() {
        int res1 = service.saveStudent("id", getAlphaString(), 200);
        assertEquals(1, res1);
    }

    @Test
    public void test_bva1() {
        int res1 = service.saveStudent(getRandomNumberString(), getAlphaString(), -200);
        assertEquals(1, res1);
    }

    @Test
    public void test_bva4() {
        int res1 = service.saveStudent(getRandomNumberString(), getAlphaString(), 888);
        assertEquals(0, res1);
    }

    @Test
    public void test_bva5() {
        int res1 = service.saveStudent(getRandomNumberString(), getAlphaString(), 938);
        assertEquals(1, res1);
    }

    @Test
    public void test_bva7() {
        int res1 = service.saveStudent(getRandomNumberString(), "2d0R#T3$t@r", 200);
        assertEquals(1, res1);
    }

    private String getRandomNumberString() {
        int number = rnd.nextInt(999999);

        return String.format("%06d", number);
    }

    private String getAlphaString() {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {

            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    @Test
    public void test_wbt1() {
        int result = service.saveTema(getRandomNumberString(), getAlphaString(), 8, 6);
        assertEquals(0, result);
    }

    @Test
    public void test_wbt2() {
        int resultInvalidLines = service.saveTema(getRandomNumberString(), getAlphaString(), 8, 9);
        int resultBelow1 = service.saveTema(getRandomNumberString(), getAlphaString(), 0, 9);
        int resultOver14 = service.saveTema(getRandomNumberString(), getAlphaString(), 15, 9);
        assertEquals(1, resultInvalidLines);
        assertEquals(1, resultBelow1);
        assertEquals(1, resultOver14);
    }

    @Test
    public void test_wbt3() {
        int resultNull = service.saveTema(null, getAlphaString(), 8, 6);
        int resultEmpty = service.saveTema("", getAlphaString(), 8, 6);
        assertEquals(1, resultNull);
        assertEquals(1, resultEmpty);
    }

    @Test
    public void test_wbt4() {
        int resultNull = service.saveTema(getRandomNumberString(), "", 8, 6);
        int resultEmpty = service.saveTema(getRandomNumberString(), null, 8, 6);
        assertEquals(1, resultNull);
        assertEquals(1, resultEmpty);
    }

    @Test
    public void test_wbt5() {
        int resultInvalidLines = service.saveTema(getRandomNumberString(), getAlphaString(), 8, 9);
        int resultBelow1 = service.saveTema(getRandomNumberString(), getAlphaString(), 8, 0);
        int resultOver14 = service.saveTema(getRandomNumberString(), getAlphaString(), 8, 15);
        assertEquals(1, resultInvalidLines);
        assertEquals(1, resultBelow1);
        assertEquals(1, resultOver14);
    }
}
