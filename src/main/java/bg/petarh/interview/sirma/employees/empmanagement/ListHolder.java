package bg.petarh.interview.sirma.employees.empmanagement;

import bg.petarh.interview.sirma.employees.employees.Employee;

import java.util.List;

public class ListHolder {

    private EmployeeListWrapper employeeListWrapper;
    private ProjectListWrapper projectListWrapper;

    public ListHolder(){
        this.employeeListWrapper = new EmployeeListWrapper();
        this.projectListWrapper = new ProjectListWrapper();
    }

    public EmployeeListWrapper getEmployeeListWrapper() {
        return employeeListWrapper;
    }

    public ProjectListWrapper getProjectListWrapper() {
        return projectListWrapper;
    }

    public List<Employee> getEmployeeList(){
        return this.employeeListWrapper.getEmployeeList();
    }
}
