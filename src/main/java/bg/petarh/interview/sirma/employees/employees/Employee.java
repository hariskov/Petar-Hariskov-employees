package bg.petarh.interview.sirma.employees.employees;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private int id;
    private List<ProjectEmployee> projectEmployeeList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ProjectEmployee> getProjectEmployeeList() {
        return projectEmployeeList;
    }

    public void setProjectEmployeeList(List<ProjectEmployee> projectEmployeeList) {
        this.projectEmployeeList = projectEmployeeList;
    }

    public void addProjectEmployee(ProjectEmployee projectEmployee) {
        this.projectEmployeeList.add(projectEmployee);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return new EqualsBuilder().append(id, employee.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
