package bg.petarh.interview.sirma.employees.empmanagement;

import bg.petarh.interview.sirma.employees.employees.Employee;
import bg.petarh.interview.sirma.employees.employees.Project;
import bg.petarh.interview.sirma.employees.employees.ProjectEmployee;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EmployeesOverlapCalculatorTest {
    private List<ProjectEmployee> projectEmployeeList = new ArrayList<>();

    @Before
    public void setup() {
        LocalDate endDate = LocalDate.now().plusDays(15);

        Project project = new Project(1);
        for (int i = 1; i <= 15; i++) {
            Employee employee = new Employee();
            employee.setId(i);
            projectEmployeeList.add(prepareEmployee(employee, project, LocalDate.now().minusDays(i), endDate.plusDays(i)));
        }
    }

    @Test
    public void calculateOverlapTest() {
        EmployeesOverlapCalculator employeesOverlapCalculator = new EmployeesOverlapCalculator();
        employeesOverlapCalculator.generateAndApplyOverlap(projectEmployeeList);

        ProjectEmployee first = projectEmployeeList.get(0);
        ProjectEmployee second = projectEmployeeList.get(1);
        ProjectEmployee secondLast = projectEmployeeList.get(projectEmployeeList.size() - 2);
        ProjectEmployee last = projectEmployeeList.get(projectEmployeeList.size() - 1);

        assertEquals("the closest to first should be second", second, first.getLongestProjectEmployee().getProjectEmployee());
        assertNotEquals("the closest to second should not be first", first, second.getLongestProjectEmployee().getProjectEmployee());

        assertEquals("the last ones should be each others closest", secondLast, last.getLongestProjectEmployee().getProjectEmployee());
        assertEquals("the last ones should be each others closest", last, secondLast.getLongestProjectEmployee().getProjectEmployee());

    }

    private ProjectEmployee prepareEmployee(Employee employee, Project project, LocalDate startDate, LocalDate endDate) {
        ProjectEmployee projectEmployee = new ProjectEmployee.Builder()
                .setEmployee(employee)
                .setProject(project)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .build();

        employee.addProjectEmployee(projectEmployee);
        project.addProjectEmployee(projectEmployee);
        return projectEmployee;
    }

}
