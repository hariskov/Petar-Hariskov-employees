package bg.petarh.interview.sirma.employees.employees;

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

    public void addProjectEmployee(ProjectEmployee projectEmployee){
        this.projectEmployeeList.add(projectEmployee);
    }
}
