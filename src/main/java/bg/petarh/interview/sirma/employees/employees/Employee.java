package bg.petarh.interview.sirma.employees.employees;

import java.time.LocalDate;

public class Employee {
    private int id;
    private int projectId;
    private LocalDate startDate;
    private LocalDate endDate;

    private Employee(EmployeeBuilder employeeBuilder) {
        this.id = employeeBuilder.id;
        this.projectId = employeeBuilder.projectId;
        this.startDate = employeeBuilder.startDate;
        this.endDate = employeeBuilder.endDate == null ? LocalDate.now() : employeeBuilder.endDate;
    }

    public int getId() {
        return id;
    }

    public int getProjectId() {
        return projectId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    static class EmployeeBuilder {
        private int id;
        private int projectId;
        private LocalDate startDate;
        private LocalDate endDate;

        public EmployeeBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder setProjectId(int projectId) {
            this.projectId = projectId;
            return this;
        }

        public EmployeeBuilder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public EmployeeBuilder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }


        public Employee build() {
            return new Employee(this);
        }

    }

}
