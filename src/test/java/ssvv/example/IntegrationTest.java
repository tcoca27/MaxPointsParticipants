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

public class IntegrationTest {
    private Service service;
    private StudentXMLRepository studentXMLRepository;
    private TemaXMLRepository temaXMLRepository;
    private NotaXMLRepository notaXMLRepository;
    private Random rnd;

    public IntegrationTest() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator();

        studentXMLRepository = new StudentXMLRepository(studentValidator, "studenti-test.xml");
        notaXMLRepository = new NotaXMLRepository(notaValidator, "note-text.xml");
        temaXMLRepository = new TemaXMLRepository(temaValidator, "teme-test.xml");
        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
        rnd = new Random();
    }

    private String getRandomNumberString() {
        int number = rnd.nextInt(999999);

        return String.format("%06d", number);
    }

    @Test
    public void testAddStudent() {
        int res = service.saveStudent(getRandomNumberString(), "Opt", 111);
        assertEquals(0, res);
    }

    @Test
    public void testAddAssignement() {
        int result = service.saveTema(getRandomNumberString(), "descriere", 8, 6);
        assertEquals(0, result);
    }

    @Test
    public void testAddGrade() {
        int result = service.saveNota("1", "1", 8, 7, "bv");
        assertEquals(0, result);
    }

    @Test
    public void testAddGradeIntegration() {
        String idStud = getRandomNumberString();
        String idAss = getRandomNumberString();
        service.saveStudent(idStud, "Opt", 111);
        service.saveTema(idAss, "descriere", 8, 6);
        int result = service.saveNota(idStud, idAss, 8, 7, "bv");
        assertEquals(0, result);
    }

}
