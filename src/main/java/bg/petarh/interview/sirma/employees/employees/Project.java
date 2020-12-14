package bg.petarh.interview.sirma.employees.employees;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private int id;
    private List<ProjectEmployee> projectEmployees = new ArrayList<>();

    public Project(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return new EqualsBuilder().append(id, project.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
