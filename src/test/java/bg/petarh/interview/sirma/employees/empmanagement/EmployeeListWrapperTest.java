package bg.petarh.interview.sirma.employees.empmanagement;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmployeeListWrapperTest {

    @Test
    public void employeesTest() {

        EmployeeListWrapper holder = new EmployeeListWrapper();

        assertTrue("no input - should be empty", holder.getEmployeeList().isEmpty());

        int employeeId1 = 1;

        holder.getOrCreateEmployee(employeeId1);

        assertEquals("size is 1", 1, holder.getEmployeeList().size());
        assertEquals("id of element 1 = $employeeId1 ", employeeId1, holder.getEmployeeList().get(0).getId());

        holder.getOrCreateEmployee(employeeId1);
        assertEquals("size is 1", 1, holder.getEmployeeList().size());

        int employeeId2 = 2;

        holder.getOrCreateEmployee(employeeId2);
        assertEquals("size is 2", 2, holder.getEmployeeList().size());
        assertEquals("id of element 2 = $employeeId2 ", employeeId2, holder.getEmployeeList().get(1).getId());
    }
    
}
