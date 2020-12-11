package bg.petarh.interview.sirma.employees.empmanagement;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmployeeHolderTest {

    @Test
    public void employeesTest() {

        assertTrue("no input - should be empty", EmployeeHolder.getEmployeeList().isEmpty());

        int employeeId1 = 1;

        EmployeeHolder.getOrCreateEmployee(employeeId1);

        assertEquals("size is 1", 1, EmployeeHolder.getEmployeeList().size());
        assertEquals("id of element 1 = $employeeId1 ", employeeId1, EmployeeHolder.getEmployeeList().get(0).getId());

        EmployeeHolder.getOrCreateEmployee(employeeId1);
        assertEquals("size is 1", 1, EmployeeHolder.getEmployeeList().size());

        int employeeId2 = 2;

        EmployeeHolder.getOrCreateEmployee(employeeId2);
        assertEquals("size is 2", 2, EmployeeHolder.getEmployeeList().size());
        assertEquals("id of element 2 = $employeeId2 ", employeeId2, EmployeeHolder.getEmployeeList().get(1).getId());
    }
    
}
