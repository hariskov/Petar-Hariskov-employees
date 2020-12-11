package bg.petarh.interview.sirma.employees.empmanagement;

import bg.petarh.interview.sirma.employees.employees.ProjectEmployee;
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

    List<String> employees = new ArrayList<>();

    @Before
    public void employeesSetup() {
        employees.add("143, 12, 2013-11-01, 2014-01-05");
        employees.add("218, 10, 2012-05-16, NULL");
        employees.add("143, 10, 2009-01-01, 2011-04-27");
    }

    @Test
    public void employeesBuilderTest() {
        ProjectEmployeesBuilder eb = new ProjectEmployeesBuilder();
        List<ProjectEmployee> projectEmployeeList = eb.buildFromList(employees);

        assertEquals("should be 3", 3, projectEmployeeList.size());
    }

    @Test(expected = IdNotProvidedException.class)
    public void employeesIdFailTest(){
        employees.add("143, , 2013-11-01, 2014-01-05");
        ProjectEmployeesBuilder eb = new ProjectEmployeesBuilder();
        eb.buildFromList(employees);
    }

    @Test(expected = LineFormatException.class)
    public void employeesLineFailTest(){
        employees.add("143, 2013-11-01, 2014-01-05");
        ProjectEmployeesBuilder eb = new ProjectEmployeesBuilder();
        eb.buildFromList(employees);
    }


}
