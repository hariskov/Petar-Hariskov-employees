package bg.petarh.interview.sirma.employees.empmanagement;

import bg.petarh.interview.sirma.employees.employees.Project;
import bg.petarh.interview.sirma.employees.exceptions.ProjectNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ProjectHolder {
    private static List<Project> projectList = new ArrayList<>();

    private ProjectHolder() {
        // static only
    }

    public static List<Project> getProjectList() {
        return projectList;
    }

    private static void addProject(Project project) {
        if(!projectList.contains(project)) {
            projectList.add(project);
        }
    }

    public static Project findProject(int id) {
        return projectList.stream().filter(e -> e.getId() == id).findFirst().orElseThrow(ProjectNotFoundException::new);
    }

    private static Project getProject(int id) {
        return projectList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public static Project getOrCreateProject(int projectId) {
        Project project = getProject(projectId);
        if(project == null){
            project = new Project(projectId);
            ProjectHolder.addProject(project);
        }
        return project;
    }
}
