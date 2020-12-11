package bg.petarh.interview.sirma.employees.employees;

public class ProjectEmployeeDayWrapper {

    private ProjectEmployee projectEmployee;
    private long days;

    public ProjectEmployeeDayWrapper(ProjectEmployee projectEmployee, long days) {
        this.projectEmployee = projectEmployee;
        this.days = days;
    }

    public long getDays() {
        return days;
    }

    public ProjectEmployee getProjectEmployee() {
        return projectEmployee;
    }
}
