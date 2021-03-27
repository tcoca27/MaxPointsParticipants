package ssvv.example;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ssvv.example.domain.Nota;
import ssvv.example.domain.Student;
import ssvv.example.domain.Tema;
import ssvv.example.repository.NotaXMLRepository;
import ssvv.example.repository.StudentXMLRepository;
import ssvv.example.repository.TemaXMLRepository;
import ssvv.example.service.Service;
import ssvv.example.validation.NotaValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.TemaValidator;
import ssvv.example.validation.Validator;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private Service service;
    private StudentXMLRepository studentXMLRepository;
    private TemaXMLRepository temaXMLRepository;
    private NotaXMLRepository notaXMLRepository;
    public AppTest() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator();

        studentXMLRepository = new StudentXMLRepository(studentValidator, "studenti-test.xml");
        notaXMLRepository = new NotaXMLRepository(notaValidator, "note-text.xml");
        temaXMLRepository = new TemaXMLRepository(temaValidator, "teme-test.xml");
        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void test1()
    {
        int res = service.saveStudent("id", "nume", 111);
        assertEquals(0, res);
    }

    @Test
    public void test2()
    {
        int res1 = service.saveStudent("", "nume", 188);
        int res2 = service.saveStudent(null, "nume", 188);
        assertEquals(1, res1);
        assertEquals(1, res2);
    }

    @Test
    public void test3()
    {
        int res1 = service.saveStudent("1", "", 188);
        int res2 = service.saveStudent("1", null, 188);
        assertEquals(1, res1);
        assertEquals(1, res2);
    }

    @Test
    public void test4()
    {
        int res1 = service.saveStudent("8", "n@me", 188);
        assertEquals(1, res1);
    }

    @Test
    public void test6()
    {
        int res1 = service.saveStudent("8", "nme", 110);
        assertEquals(1, res1);
    }

    @Test
    public void test7()
    {
        int res1 = service.saveStudent("8", "nme", 938);
        assertEquals(1, res1);
    }

    @Test
    public void test8()
    {
        int res1 = service.saveStudent("id", "nme", 200);
        assertEquals(1, res1);
    }

    @Test
    public void test_bva1() {
        int res1 = service.saveStudent("id", "nme", -200);
        assertEquals(1, res1);
    }

    @Test
    public void test_bva4() {
        int res1 = service.saveStudent("id8", "nme", 888);
        assertEquals(0, res1);
    }

    @Test
    public void test_bva5() {
        int res1 = service.saveStudent("id", "nme", 938);
        assertEquals(1, res1);
    }

    @Test
    public void test_bva7() {
        int res1 = service.saveStudent("7", "2d0R#T3$t@r", 200);
        assertEquals(1, res1);
    }
}
