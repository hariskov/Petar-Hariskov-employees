package bg.petarh.interview.sirma.employees.empmanagement;

import bg.petarh.interview.sirma.employees.employees.Employee;
import bg.petarh.interview.sirma.employees.employees.Project;
import bg.petarh.interview.sirma.employees.employees.ProjectEmployee;
import bg.petarh.interview.sirma.employees.exceptions.IdNotProvidedException;
import bg.petarh.interview.sirma.employees.exceptions.LineFormatException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProjectEmployeesBuilder {

    private EmployeeListWrapper employeeListWrapper;
    private ProjectListWrapper projectListWrapper;

    public ProjectEmployeesBuilder(ListHolder listHolder) {
        this.employeeListWrapper = listHolder.getEmployeeListWrapper();
        this.projectListWrapper = listHolder.getProjectListWrapper();
    }

    public List<ProjectEmployee> buildFromList(List<String> stringInput) {
        List<ProjectEmployee> projectEmployees = new ArrayList<>();

        for (String line : stringInput) {
            projectEmployees.add(getProjectEmployee(line));
        }

        return projectEmployees;
    }

    private ProjectEmployee getProjectEmployee(String line) {
        ProjectEmployeeValueWrapper peWrapper = StringToEmployeeConverter.convert(line);
        Employee employee = employeeListWrapper.getOrCreateEmployee(peWrapper.employeeId);
        Project project = projectListWrapper.getOrCreateProject(peWrapper.projectId);

        ProjectEmployee projectEmployee = new ProjectEmployee.Builder()
                .setEmployee(employee)
                .setProject(project)
                .setStartDate(peWrapper.startDate)
                .setEndDate(peWrapper.endDate)
                .build();

        employee.addProjectEmployee(projectEmployee);
        project.addProjectEmployee(projectEmployee);
        return projectEmployee;
    }
}

class StringToEmployeeConverter {

    private StringToEmployeeConverter() {
    }

    public static ProjectEmployeeValueWrapper convert(String input) {

        String[] line = input.split(",");

        if (line.length != 4) {
            throw new LineFormatException();
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

        return LocalDate.parse(stringInput.strip());
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
