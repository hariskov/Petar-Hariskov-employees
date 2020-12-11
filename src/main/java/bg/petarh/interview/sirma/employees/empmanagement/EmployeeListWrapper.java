package bg.petarh.interview.sirma.employees.empmanagement;

import bg.petarh.interview.sirma.employees.employees.Employee;
import bg.petarh.interview.sirma.employees.exceptions.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.List;

class EmployeeListWrapper {

    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    private void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public Employee findEmployee(int id) {
        return employeeList.stream().filter(e -> e.getId() == id).findFirst().orElseThrow(EmployeeNotFoundException::new);
    }

    private Employee getEmployee(int id) {
        return employeeList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public Employee getOrCreateEmployee(int employeeId) {
        Employee employee = getEmployee(employeeId);
        if (employee == null) {
            employee = new Employee();
            employee.setId(employeeId);
            addEmployee(employee);
        }

        return employee;
    }
}
