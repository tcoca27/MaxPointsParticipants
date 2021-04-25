package ssvv.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ssvv.example.domain.Nota;
import ssvv.example.domain.Pair;
import ssvv.example.domain.Student;
import ssvv.example.domain.Tema;
import ssvv.example.repository.NotaXMLRepository;
import ssvv.example.repository.StudentXMLRepository;
import ssvv.example.repository.TemaXMLRepository;
import ssvv.example.service.Service;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IntegrationTest {

    @InjectMocks
    private Service service;

    @Mock
    private StudentXMLRepository studentXMLRepository;

    @Mock
    private TemaXMLRepository temaXMLRepository;

    @Mock
    private NotaXMLRepository notaXMLRepository;

    private final Random rnd = new Random();

    private String getRandomNumberString() {
        int number = rnd.nextInt(999999);

        return String.format("%06d", number);
    }

    @Test
    public void testAddStudent() {
        String id = getRandomNumberString();
        Student s = new Student(id, "Opt", 111);
        when(studentXMLRepository.save(s)).thenReturn(s);
        int res = service.saveStudent(id, "Opt", 111);
        assertEquals(0, res);
    }

    @Test
    public void testAddAssignement() {
        String id = getRandomNumberString();
        Tema t = new Tema(id, "descriere", 8, 6);
        when(temaXMLRepository.save(t)).thenReturn(t);
        int result = service.saveTema(id, "descriere", 8, 6);
        assertEquals(0, result);
    }

    @Test
    public void testAddGrade() {
        String idS = getRandomNumberString();
        String idT = getRandomNumberString();
        Student s = new Student(idS, "Opt", 111);
        Tema t = new Tema(idT, "descriere", 8, 6);
        Nota n = new Nota(new Pair<>(idS, idT), 8.0, 7, "bv");
        when(studentXMLRepository.findOne(idS)).thenReturn(s);
        when(temaXMLRepository.findOne(idT)).thenReturn(t);
        when(notaXMLRepository.save(any(Nota.class))).thenReturn(n);
        int result = service.saveNota(idS, idT, 8.0, 7, "bv");
        assertEquals(0, result);
    }

    @Test
    public void testAddS() {
        testAddStudent();
    }

    @Test
    public void testAddSandA() {
        testAddStudent();
        testAddAssignement();
    }

    @Test
    public void testAddSandAandG() {
        testAddStudent();
        testAddAssignement();
        testAddGrade();
    }

}
