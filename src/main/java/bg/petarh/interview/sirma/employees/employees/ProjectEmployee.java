package bg.petarh.interview.sirma.employees.employees;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDate;

public class ProjectEmployee {
    private Employee employee;
    private Project project;
    private LocalDate startDate;
    private LocalDate endDate;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ProjectEmployee that = (ProjectEmployee) o;

        return new EqualsBuilder().append(employee, that.employee).append(project, that.project).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(employee).append(project).toHashCode();
    }
}
