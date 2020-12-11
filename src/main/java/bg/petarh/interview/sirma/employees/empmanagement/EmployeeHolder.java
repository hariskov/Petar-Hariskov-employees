package bg.petarh.interview.sirma.employees.empmanagement;

import bg.petarh.interview.sirma.employees.employees.Employee;
import bg.petarh.interview.sirma.employees.exceptions.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.List;

class EmployeeHolder {

    private static List<Employee> employeeList = new ArrayList<>();

    private EmployeeHolder(){} // static only

    public static List<Employee> getEmployeeList() {
        return employeeList;
    }

    private static void addEmployee(Employee employee){
        employeeList.add(employee);
    }

    public static Employee findEmployee(int id){
        return employeeList.stream().filter(e->e.getId() == id).findFirst().orElseThrow(EmployeeNotFoundException::new);
    }

    private static Employee getEmployee(int id){
        return employeeList.stream().filter(e->e.getId() == id).findFirst().orElse(null);
    }

    public static Employee getOrCreateEmployee(int employeeId) {
        Employee employee = getEmployee(employeeId);
        if(employee == null){
            employee = new Employee();
            employee.setId(employeeId);
            addEmployee(employee);
        }

        return employee;
    }
}
