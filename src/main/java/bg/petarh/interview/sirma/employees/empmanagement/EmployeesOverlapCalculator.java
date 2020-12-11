package bg.petarh.interview.sirma.employees.empmanagement;

import bg.petarh.interview.sirma.employees.employees.ProjectEmployee;
import bg.petarh.interview.sirma.employees.employees.ProjectEmployeeDayWrapper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

public class EmployeesOverlapCalculator {
    public void generateAndApplyOverlap(List<ProjectEmployee> projectEmployeeList) {
        for (ProjectEmployee projectEmployee : projectEmployeeList) {
            ProjectEmployee longestOverlapEmployee = projectEmployee.getProject().getProjectEmployees().stream()
                    .filter(e -> !e.getEmployee().equals(projectEmployee.getEmployee()))
                    .max(Comparator.comparing(e -> getOverlapForEmployees(e, projectEmployee))).orElse(null);
            if (longestOverlapEmployee != null) {
                long days = getOverlapForEmployees(projectEmployee, longestOverlapEmployee);

                projectEmployee.setLongestProjectEmployee(new ProjectEmployeeDayWrapper(longestOverlapEmployee, days));
            }
        }
    }

    private Long getOverlapForEmployees(ProjectEmployee first, ProjectEmployee second) {
        LocalDate earliestOverlappingStartDate = first.getStartDate().isBefore(second.getStartDate()) ? second.getStartDate() : first.getStartDate();
        LocalDate latestOverlappingStartDate = first.getEndDate().isBefore(second.getEndDate()) ? first.getEndDate() : second.getEndDate();
        return ChronoUnit.DAYS.between(earliestOverlappingStartDate, latestOverlappingStartDate);
    }
}
