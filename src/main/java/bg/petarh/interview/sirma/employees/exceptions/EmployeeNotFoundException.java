package bg.petarh.interview.sirma.employees.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("Employee Not Found");
    }
}
