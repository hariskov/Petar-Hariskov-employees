package bg.petarh.interview.sirma.employees.empmanagement;

import bg.petarh.interview.sirma.employees.employees.Employee;
import bg.petarh.interview.sirma.employees.employees.Project;
import bg.petarh.interview.sirma.employees.employees.ProjectEmployee;
import bg.petarh.interview.sirma.employees.utils.EmployeesOverlapCalculator;
import bg.petarh.interview.sirma.employees.utils.Pair;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class EmployeesOverlapCalculatorControlledTest {
    private static final Long DAYS_DIFFERENCE = 15L;
    private Project project = new Project(1);
    private List<Employee> employees = new ArrayList<>();

    @Before
    public void setup() {
        for (int i = 0; i < 2; i++) {
            Employee employee = new Employee();
            employee.setId(i);
            ProjectEmployee projectEmployee = prepareEmployee(employee, project, LocalDate.now().minusDays(DAYS_DIFFERENCE), LocalDate.now());
            employee.addProjectEmployee(projectEmployee);
            project.addProjectEmployee(projectEmployee);
            employees.add(employee);
        }
    }

    @Test
    public void multipleOccurancesForEmployeeTest() {

        Employee emp = employees.get(0);
        // emp1 = (-60 -> -30 ); (-15 -> now)
        ProjectEmployee projectEmployee = prepareEmployee(emp, project, LocalDate.now().minusDays(60), LocalDate.now().minusDays(30));
        emp.addProjectEmployee(projectEmployee);
        project.addProjectEmployee(projectEmployee);

        Employee otherEmployee = employees.get(1);
        Map<Project, Pair<Employee, Long>> projectOverlapPair = EmployeesOverlapCalculator.generateOverlapForEmployee(emp);
        assertTrue(projectOverlapPair.containsKey(project));

        assertEquals("otherEmployee for project should be longest", otherEmployee, projectOverlapPair.get(project).getKey());
        assertEquals("length should be 15", DAYS_DIFFERENCE, projectOverlapPair.get(project).getValue());

        // maxDaysEmployee = (-70 -> now)
        Employee maxDaysEmployee = new Employee();
        maxDaysEmployee.setId(3);
        ProjectEmployee maxDaysPE = prepareEmployee(maxDaysEmployee, project, LocalDate.now().minusDays(70), LocalDate.now());
        maxDaysEmployee.addProjectEmployee(maxDaysPE);
        project.addProjectEmployee(maxDaysPE);

        projectOverlapPair = EmployeesOverlapCalculator.generateOverlapForEmployee(emp);
        Pair<Employee, Long> foundPair = projectOverlapPair.get(project);

        assertEquals("emp1 should now have longestEmployee with maxDaysEmployee", maxDaysEmployee, foundPair.getKey());
        // overlap -60-30; -15-now = 45 days // from emp1
        assertEquals("should be 45", Long.valueOf(45), foundPair.getValue());
    }

    @Test
    public void multipleProjectsTest() {
        Project project2 = new Project(2);
        Employee emp = employees.get(0);
        Employee emp2 = employees.get(1);

        ProjectEmployee empPE = prepareEmployee(emp, project2, LocalDate.now().minusDays(1), null);
        ProjectEmployee emp2PE = prepareEmployee(emp2, project2, LocalDate.now().minusDays(2), null);

        emp.addProjectEmployee(empPE);
        project2.addProjectEmployee(empPE);

        emp2.addProjectEmployee(emp2PE);
        project2.addProjectEmployee(emp2PE);

        Map<Project, Pair<Employee, Long>> projectOverlapPair = EmployeesOverlapCalculator.generateOverlapForEmployee(emp);
        assertEquals("we should expect two projects now ", 2, projectOverlapPair.size());

        Pair<Employee, Long> discoveredPairForP2 = projectOverlapPair.get(project2);
        assertEquals("expecting to see the other employee", emp2, discoveredPairForP2.getKey());
        assertEquals("difference between thet wo is 1 day", Long.valueOf(1), discoveredPairForP2.getValue());

        Pair<Employee, Long> discoveredPairForP1 = projectOverlapPair.get(project);

        assertNotEquals("the longest employee Overlap for the two projects are different", discoveredPairForP1.getValue(), discoveredPairForP2.getValue());


    }


    private ProjectEmployee prepareEmployee(Employee employee, Project project, LocalDate startDate, LocalDate endDate) {
        return new ProjectEmployee.Builder()
                .setEmployee(employee)
                .setProject(project)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .build();
    }
}
