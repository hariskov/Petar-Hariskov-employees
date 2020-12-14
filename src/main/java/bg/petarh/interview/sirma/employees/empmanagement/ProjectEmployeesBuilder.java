package bg.petarh.interview.sirma.employees.empmanagement;

import bg.petarh.interview.sirma.employees.employees.Employee;
import bg.petarh.interview.sirma.employees.employees.Project;
import bg.petarh.interview.sirma.employees.employees.ProjectEmployee;
import bg.petarh.interview.sirma.employees.exceptions.DataInconsistencyException;
import bg.petarh.interview.sirma.employees.exceptions.IdNotProvidedException;
import bg.petarh.interview.sirma.employees.exceptions.LineFormatException;
import bg.petarh.interview.sirma.employees.utils.OverlapCalculator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProjectEmployeesBuilder {

    private EmployeeListWrapper employeeListWrapper;
    private ProjectListWrapper projectListWrapper;

    public ProjectEmployeesBuilder() {
        this(new ListHolder());
    }

    public ProjectEmployeesBuilder(ListHolder listHolder) {
        this.employeeListWrapper = listHolder.getEmployeeListWrapper();
        this.projectListWrapper = listHolder.getProjectListWrapper();
    }

    public List<ProjectEmployee> buildProjectEmployeesFromList(List<String> stringInput) {
        List<ProjectEmployee> projectEmployees = new ArrayList<>();

        for (String line : stringInput) {
            if (line.strip().isBlank()) {
                continue;
            }

            ProjectEmployee projectEmployee = getProjectEmployee(line);

            long discoveredImpossibleOverlaps = projectEmployees.stream().filter(existingPE -> existingOrOverlappingFilter(existingPE, projectEmployee)).count();

            if (discoveredImpossibleOverlaps == 0) {
                projectEmployees.add(projectEmployee);
                projectEmployee.getEmployee().addProjectEmployee(projectEmployee);
                projectEmployee.getProject().addProjectEmployee(projectEmployee);
            } else {
                throw new DataInconsistencyException(projectEmployee.getProject().getId(), projectEmployee.getEmployee().getId());
            }
        }

        return projectEmployees;
    }

    private boolean existingOrOverlappingFilter(ProjectEmployee existingPE, ProjectEmployee projectEmployee) {
        if (existingPE.getEmployee().equals(projectEmployee.getEmployee()) &&
                existingPE.getProject().equals(projectEmployee.getProject())) {
            return 0 < OverlapCalculator.getOverlapForProjectEmployees(existingPE, projectEmployee);
        }
        return false;
    }

    private ProjectEmployee getProjectEmployee(String line) {
        ProjectEmployeeValueWrapper peWrapper = StringToEmployeeConverter.convert(line);
        Employee employee = employeeListWrapper.getOrCreateEmployee(peWrapper.employeeId);
        Project project = projectListWrapper.getOrCreateProject(peWrapper.projectId);

        return new ProjectEmployee.Builder()
                .setEmployee(employee)
                .setProject(project)
                .setStartDate(peWrapper.startDate)
                .setEndDate(peWrapper.endDate)
                .build();
    }
}

class StringToEmployeeConverter {
    private static final DateTimeFormatter[] parsers = new DateTimeFormatter[]{
            new DateTimeFormatterBuilder()
                    .appendOptional(DateTimeFormatter.ISO_DATE)
                    .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                    .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy MM dd"))
                    .appendOptional(DateTimeFormatter.ofPattern("dd MM yyyy"))
                    .appendOptional(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))
                    .appendOptional(DateTimeFormatter.ofPattern("dd/MMM/yyyy"))
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy/MMM/dd"))
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy MMM dd"))
                    .appendOptional(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                    .toFormatter(),
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ISO_OFFSET_DATE_TIME,
            DateTimeFormatter.ISO_ZONED_DATE_TIME,
    };

    private StringToEmployeeConverter() {
    }

    public static ProjectEmployeeValueWrapper convert(String input) {

        String[] line = input.split(",");

        if (line.length != 4) {
            throw new LineFormatException(input);
        }

        return new ProjectEmployeeValueWrapper(parseToInt(line[0]), parseToInt(line[1]), parseToLocalDate(line[2]), parseToLocalDate(line[3]));
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
        for (DateTimeFormatter parser : parsers) {
            try {
                return LocalDate.parse(stringInput.strip(), parser);
            } catch (Exception e) {
                // will break at wrong parses; throw only if all fail
            }
        }
        throw new DateTimeParseException("cant parse : " + stringInput.strip(), "", 1);
    }

}

class ProjectEmployeeValueWrapper {
    int employeeId;
    int projectId;
    LocalDate startDate;
    LocalDate endDate;

    public ProjectEmployeeValueWrapper(int employeeId, int projectId, LocalDate startDate, LocalDate endDate) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
