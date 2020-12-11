package bg.petarh.interview.sirma.employees.exceptions;

public class IdNotProvidedException extends RuntimeException {
    public IdNotProvidedException() {
        super("id is not provided for employee");
    }
}
