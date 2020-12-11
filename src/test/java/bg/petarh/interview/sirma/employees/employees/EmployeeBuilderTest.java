package bg.petarh.interview.sirma.employees.employees;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class EmployeeBuilderTest {

    List<String> employees = new ArrayList<>();

    @Before
    public void employeesSetup() {
        employees.add("143, 12, 2013-11-01, 2014-01-05");
        employees.add("218, 10, 2012-05-16, NULL");
        employees.add("143, 10, 2009-01-01, 2011-04-27");
    }

    @Test
    public void employeesBuilderTest() {
        EmployeesBuilder eb = new EmployeesBuilder();
        List<Employee> employeeList = eb.buildFromList(employees);

        assertEquals("should be 3", 3, employeeList.size());
    }

    @Test(expected = StringToEmployeeConverter.IdNotProvidedException.class)
    public void employeesIdFailTest(){
        employees.add("143, , 2013-11-01, 2014-01-05");
        EmployeesBuilder eb = new EmployeesBuilder();
        eb.buildFromList(employees);
    }

    @Test(expected = StringToEmployeeConverter.LineFormatException.class)
    public void employeesLineFailTest(){
        employees.add("143, 2013-11-01, 2014-01-05");
        EmployeesBuilder eb = new EmployeesBuilder();
        eb.buildFromList(employees);
    }


}
