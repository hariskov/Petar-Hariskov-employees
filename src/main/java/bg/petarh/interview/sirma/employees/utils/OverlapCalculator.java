package bg.petarh.interview.sirma.employees.utils;

import bg.petarh.interview.sirma.employees.employees.Employee;
import bg.petarh.interview.sirma.employees.employees.EmployeeProjectResult;
import bg.petarh.interview.sirma.employees.employees.Project;
import bg.petarh.interview.sirma.employees.employees.ProjectEmployee;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OverlapCalculator {
    private OverlapCalculator() {
    }

    public static Long getOverlapForProjectEmployees(ProjectEmployee first, ProjectEmployee second) {
        LocalDate earliestOverlappingStartDate = first.getStartDate().isBefore(second.getStartDate()) ? second.getStartDate() : first.getStartDate();
        LocalDate latestOverlappingStartDate = first.getEndDate().isBefore(second.getEndDate()) ? first.getEndDate() : second.getEndDate();
        long days = ChronoUnit.DAYS.between(earliestOverlappingStartDate, latestOverlappingStartDate);
        return Math.max(days, 0);
    }

    public static List<EmployeeProjectResult> calculate(Project project) {
        List<Employee> employees = project.getProjectEmployees().stream().map(ProjectEmployee::getEmployee).distinct().collect(Collectors.toList());
        List<EmployeeProjectResult> employeeCombosForProject = new ArrayList<>();

        for (Employee employee : employees) {
            employeeCombosForProject.add(calculate(project, employee));
        }
        return employeeCombosForProject;
    }

    public static EmployeeProjectResult calculate(Project project, Employee emp) {

        List<ProjectEmployee> currentEmployeeWorkings = emp.getProjectEmployeeList().stream().filter(e -> e.getProject().equals(project)).collect(Collectors.toList());
        Map<Employee, List<ProjectEmployee>> a = project.getProjectEmployees().stream().filter(pe -> !pe.getEmployee().equals(emp)).collect(Collectors.groupingBy(ProjectEmployee::getEmployee));

        Pair<Employee, Long> pair = new Pair<>();

        for (Map.Entry<Employee, List<ProjectEmployee>> employeeListEntry : a.entrySet()) {
            Long sumOfTotalOverlappingWorkingDays = 0L;
            for (ProjectEmployee projectEmployee : employeeListEntry.getValue()) {
                sumOfTotalOverlappingWorkingDays += currentEmployeeWorkings.stream().mapToLong(e -> getOverlapForProjectEmployees(e, projectEmployee)).sum();
            }
            if (pair.getValue() < sumOfTotalOverlappingWorkingDays) {
                pair.updatePair(employeeListEntry.getKey(), sumOfTotalOverlappingWorkingDays);
            }
        }

        return new EmployeeProjectResult(project.getId(), emp.getId(), pair.getKey() == null ? 0 : pair.getKey().getId(), pair.getValue() == null ? 0 : pair.getValue());
    }

}
