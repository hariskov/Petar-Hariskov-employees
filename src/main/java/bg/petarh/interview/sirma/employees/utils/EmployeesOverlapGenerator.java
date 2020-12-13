package bg.petarh.interview.sirma.employees.utils;

import bg.petarh.interview.sirma.employees.employees.Employee;
import bg.petarh.interview.sirma.employees.employees.EmployeeProjectResult;
import bg.petarh.interview.sirma.employees.employees.Project;
import bg.petarh.interview.sirma.employees.employees.ProjectEmployee;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeesOverlapGenerator {

    private EmployeesOverlapGenerator() {
    }

    public static List<EmployeeProjectResult> generateOverlapForEmployee(Employee employee) {
        return employee.getProjectEmployeeList().stream()
                .map(ProjectEmployee::getProject)
                .distinct()
                .map(project -> OverlapCalculator.calculate(project, employee))
                .collect(Collectors.toList());
    }

    public static EmployeeProjectResult findLongestCoEmployeesByProject(Project project) {
        return OverlapCalculator.calculate(project).stream().max(Comparator.comparing(EmployeeProjectResult::getDays)).orElse(null);
    }
}
