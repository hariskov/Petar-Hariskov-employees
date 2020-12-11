package bg.petarh.interview.sirma.employees.employees;

import java.time.LocalDate;

public class ProjectEmployee {
    private Employee employee;
    private Project project;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectEmployeeDayWrapper longestProjectEmployee;

    private ProjectEmployee(ProjectEmployeeBuilder projectEmployeeBuilder) {
        this.employee = projectEmployeeBuilder.employee;
        this.project = projectEmployeeBuilder.project;
        this.startDate = projectEmployeeBuilder.startDate;
        this.endDate = projectEmployeeBuilder.endDate == null ? LocalDate.now() : projectEmployeeBuilder.endDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Project getProject() {
        return project;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setLongestProjectEmployee(ProjectEmployeeDayWrapper longestProjectEmployee){
        this.longestProjectEmployee = longestProjectEmployee;
    }

    public static class ProjectEmployeeBuilder {
        private Employee employee;
        private Project project;
        private LocalDate startDate;
        private LocalDate endDate;

        public ProjectEmployeeBuilder setEmployee(Employee employee) {
            this.employee = employee;
            return this;
        }

        public ProjectEmployeeBuilder setProject(Project project) {
            this.project = project;
            return this;
        }

        public ProjectEmployeeBuilder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public ProjectEmployeeBuilder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }
        public ProjectEmployee build() {
            return new ProjectEmployee(this);
        }
    }
}
