package bg.petarh.interview.sirma.employees.employees;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EmployeesBuilder {
    public List<Employee> buildFromList(List<String> stringInput) {
        List<Employee> employees = new ArrayList<>();

        for (String string : stringInput) {
            employees.add(StringToEmployeeConverter.convert(string));
        }

        return employees;
    }
}

class StringToEmployeeConverter {

    private StringToEmployeeConverter(){}

    public static Employee convert(String input) {

        String[] line = input.split(",");

        if (line.length != 4) {
            throw new LineFormatException();
        }

        Employee employee = new Employee();
        employee.setId(parseToInt(line[0]));
        employee.setProjectId(parseToInt(line[1]));
        employee.setStartDate(parseToLocalDate(line[2]));
        employee.setEndDate(parseToLocalDate(line[3]));

        return employee;
    }

    private static Integer parseToInt(String stringInput) {

        if (stringInput == null || stringInput.strip().isEmpty()) {
            throw new IdNotProvidedException();
        }

        return Integer.parseInt(stringInput.strip());
    }

    private static LocalDate parseToLocalDate(String stringInput) {

        if (stringInput == null || stringInput.strip().isEmpty() || stringInput.strip().toLowerCase(Locale.ROOT).equals("null")) {
            return null;
        }

        return LocalDate.parse(stringInput.strip());
    }

    static class IdNotProvidedException extends RuntimeException {
        IdNotProvidedException() {
            super("id is not provided for employee");
        }
    }

    static class LineFormatException extends RuntimeException {
        LineFormatException() {
            super("Formatting of line is wrong , please use int employeeId, int projectId, date startDate, date endDate format ");
        }
    }
}
