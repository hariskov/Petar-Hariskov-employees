package bg.petarh.interview.sirma.employees.empmanagement;

import bg.petarh.interview.sirma.employees.employees.Project;
import bg.petarh.interview.sirma.employees.exceptions.ProjectNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ProjectListWrapper {

    private List<Project> projectList = new ArrayList<>();

    public List<Project> getProjectList() {
        return projectList;
    }

    private void addProject(Project project) {
        if (!projectList.contains(project)) {
            projectList.add(project);
        }
    }

    public Project findProject(int id) {
        return projectList.stream().filter(e -> e.getId() == id).findFirst().orElseThrow(ProjectNotFoundException::new);
    }

    private Project getProject(int id) {
        return projectList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public Project getOrCreateProject(int projectId) {
        Project project = getProject(projectId);
        if (project == null) {
            project = new Project(projectId);
            addProject(project);
        }
        return project;
    }
}
