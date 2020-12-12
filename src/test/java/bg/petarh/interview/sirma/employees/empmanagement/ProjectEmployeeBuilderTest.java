package bg.petarh.interview.sirma.employees.empmanagement;

import bg.petarh.interview.sirma.employees.employees.ProjectEmployee;
import bg.petarh.interview.sirma.employees.exceptions.DataInconsistencyException;
import bg.petarh.interview.sirma.employees.exceptions.IdNotProvidedException;
import bg.petarh.interview.sirma.employees.exceptions.LineFormatException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class ProjectEmployeeBuilderTest {

    private List<String> employees = new ArrayList<>();
    private ProjectEmployeesBuilder employeesBuilder;

    @Before
    public void employeesSetup() {
        employees.add("143, 12, 2013-11-01, 2014-01-05");
        employees.add("218, 10, 2012-05-16, NULL");
        employees.add("143, 10, 2009-01-01, 2011-04-27");
        employeesBuilder = new ProjectEmployeesBuilder(new ListHolder());
    }

    @Test
    public void employeesBuilderTest() {
        List<ProjectEmployee> projectEmployeeList = employeesBuilder.buildProjectEmployeesFromList(employees);
        assertEquals("should be 3", 3, projectEmployeeList.size());
    }

    @Test(expected = IdNotProvidedException.class)
    public void employeesIdFailTest(){
        employees.add("143, , 2013-11-01, 2014-01-05");
        employeesBuilder.buildProjectEmployeesFromList(employees);
    }

    @Test(expected = LineFormatException.class)
    public void employeesLineFailTest(){
        employees.add("143, 2013-11-01, 2014-01-05");
        employeesBuilder.buildProjectEmployeesFromList(employees);
    }

    @Test(expected = DataInconsistencyException.class)
    public void overlappingEmployeeProjects(){
        employees.add("143 , 12 , 2013-11-01, 2013-11-02");
        employeesBuilder.buildProjectEmployeesFromList(employees);
    }

    @Test
    public void multipleDatesTest(){
        employees.add("111, 15, 2009-02-01, 2009-02-02");
        employees.add("111, 15, 03-02-2009, 04-02-2009");
        employees.add("111, 15, 05/02/2009, 06/02/2009");
        employees.add("111, 15, 2011-12-03T10:15:30, 2011-12-04T10:15:30");
        employees.add("111, 15, 2011-11-03T10:15:30+01:00, 2011-11-04T10:15:30+01:00");
        employees.add("111, 15, 2011-12-05T10:15:30+01:00[Europe/Paris], 2011-12-06T10:15:30+01:00[Europe/Paris]");
        employees.add("111 , 15 , 02 Jan 2018 , 03 Jan 2018");
        List<ProjectEmployee> projectEmployeeList =  employeesBuilder.buildProjectEmployeesFromList(employees);
        assertEquals("should be 10", 10, projectEmployeeList.size());
    }

}
