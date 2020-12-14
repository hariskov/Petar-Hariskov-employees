package bg.petarh.interview.sirma.employees.exceptions;

public class DataInconsistencyException extends RuntimeException {
    public DataInconsistencyException(int projectId, int employeeId) {
        super("A Combination of ProjectId:" + projectId + " and EmployeeId:" + employeeId + " already Exists");
    }
}
