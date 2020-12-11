package bg.petarh.interview.sirma.employees.exceptions;

public class LineFormatException extends RuntimeException {
    public LineFormatException() {
        super("Formatting of line is wrong , please use int employeeId, int projectId, date startDate, date endDate format ");
    }
}
