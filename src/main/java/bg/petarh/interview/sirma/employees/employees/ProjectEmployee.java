package bg.petarh.interview.sirma.employees.employees;

import java.time.LocalDate;

public class ProjectEmployee {
    private Employee employee;
    private Project project;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectEmployeeDayWrapper longestProjectEmployee;

    private ProjectEmployee(Builder builder) {
        this.employee = builder.employee;
        this.project = builder.project;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate == null ? LocalDate.now() : builder.endDate;
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

    public static class Builder {
        private Employee employee;
        private Project project;
        private LocalDate startDate;
        private LocalDate endDate;

        public Builder setEmployee(Employee employee) {
            this.employee = employee;
            return this;
        }

        public Builder setProject(Project project) {
            this.project = project;
            return this;
        }

        public Builder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }
        public ProjectEmployee build() {
            return new ProjectEmployee(this);
        }
    }
}
