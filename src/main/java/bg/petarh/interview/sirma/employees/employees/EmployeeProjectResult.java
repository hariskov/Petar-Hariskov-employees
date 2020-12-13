package bg.petarh.interview.sirma.employees.employees;

public class EmployeeProjectResult {

    private int projectId;
    public static final String _PROJECT_ID = "projectId";

    private int empId;
    public static final String _EMPLOYEE_ID = "empId";

    private int emp2Id;
    public static final String _EMPLOYEE_2_ID = "emp2Id";

    private long days;
    public static final String _DAYS = "days";

    public EmployeeProjectResult(int projectId, int empId, int emp2Id, Long days) {
        this.days = days;
        this.emp2Id = emp2Id;
        this.empId = empId;
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getEmpId() {
        return empId;
    }

    public int getEmp2Id() {
        return emp2Id;
    }

    public long getDays() {
        return days;
    }
}
