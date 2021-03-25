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
        System.out.println("merge");
        int res = service.saveStudent("id", "nume", 188);
        assertEquals(1, res);
    }

    @Test
    public void test2()
    {
        assertFalse( false );
    }
}
