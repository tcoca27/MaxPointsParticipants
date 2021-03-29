package ssvv.example.validation;
import ssvv.example.domain.Tema;

public class TemaValidator implements Validator<Tema> {
    public void validate(Tema tema) throws ValidationException {
        if (tema.getID() == null || tema.getID().equals("")) {
            System.out.println("id");
            throw new ValidationException("ID invalid! \n");
        }
        if (tema.getDescriere() == null || tema.getDescriere().equals("")) {
            System.out.println("descriere");
            throw new ValidationException("Descriere invalida! \n");
        }
        if (tema.getDeadline() < 1 || tema.getDeadline() > 14 || tema.getDeadline() < tema.getStartline()) {
            System.out.println("deadline");
            throw new ValidationException("Deadline invalid! \n");
        }
        if (tema.getStartline() < 1 || tema.getStartline() > 14 || tema.getStartline() > tema.getDeadline()) {
            System.out.println("startline");
            throw new ValidationException("Data de primire invalida! \n");
        }
    }
}

