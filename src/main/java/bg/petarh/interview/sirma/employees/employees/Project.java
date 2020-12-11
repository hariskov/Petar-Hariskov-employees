package bg.petarh.interview.sirma.employees.employees;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private int id;
    private List<ProjectEmployee> projectEmployees = new ArrayList<>();

    public Project(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public List<ProjectEmployee> getProjectEmployees() {
        return projectEmployees;
    }

    public void setProjectEmployees(List<ProjectEmployee> projectEmployees) {
        this.projectEmployees = projectEmployees;
    }

    public void addProjectEmployee(ProjectEmployee projectEmployee) {
        this.projectEmployees.add(projectEmployee);
    }
}
