package bg.petarh.interview.sirma.employees.exceptions;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException() {
        super("Project Not Found");
    }
}
