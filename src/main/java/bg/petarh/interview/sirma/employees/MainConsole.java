package bg.petarh.interview.sirma.employees;

import bg.petarh.interview.sirma.employees.employees.Employee;
import bg.petarh.interview.sirma.employees.employees.Project;
import bg.petarh.interview.sirma.employees.empmanagement.ListHolder;
import bg.petarh.interview.sirma.employees.empmanagement.ProjectEmployeesBuilder;
import bg.petarh.interview.sirma.employees.fio.FileOpen;
import bg.petarh.interview.sirma.employees.utils.EmployeesOverlapCalculator;
import bg.petarh.interview.sirma.employees.utils.Pair;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainConsole {

    public static void main(String[] args) {
        MainArgumentsBuilder mab = new MainArgumentsBuilder(args);
        try {
            ListHolder listHolder = new ListHolder();
            List<String> lines = FileOpen.readFileToListOfLines(mab.getArguments().getFilePath());
            ProjectEmployeesBuilder projectEmployeesBuilder = new ProjectEmployeesBuilder(listHolder);
            projectEmployeesBuilder.buildProjectEmployeesFromList(lines);

            List<Employee> employees = listHolder.getEmployeeList();

            // to prettify a bit
            for (Employee employee : employees) {
                System.out.println("Employee : " + employee.getId());
                Map<Project, Pair<Employee, Long>> employeeBuddiesPerProject = EmployeesOverlapCalculator.generateOverlapForEmployee(employee);
                employeeBuddiesPerProject.forEach((key, value) -> System.out.println(buildPrintString(employee, key, value)));
            }
        } catch (IOException ioException) {
            System.out.println("failed reading file");
        }
    }

    private static String buildPrintString(Employee emp, Project pr, Pair<Employee, Long> daysWithBuddy) {
        String projectString = "For Project : " + pr.getId() + "; The employee" + emp.getId();

        if(daysWithBuddy.getKey() == null){
            return projectString + " the employee worked alone";
        }

        return projectString + "With Employee :" + daysWithBuddy.getKey().getId() + "; days together :" + daysWithBuddy.getValue();
    }

}

class MainArgumentsBuilder {
    private static final int ARGS_COUNT = 2;

    private ArgumentsHolder holder;

    public MainArgumentsBuilder(String[] args) {

        if (args.length == 0) {
            System.out.println("please provide -filePath argument followed by the path to the file ");
            System.exit(1);
        }

        if (args.length % ARGS_COUNT != 0 || args.length > ARGS_COUNT) {
            System.out.println("wrong argument count");
            System.exit(1);
        }

        buildArguments(args);
    }

    // potentially more arguments ?!
    // overengineering doesnt hurt i guess
    private void buildArguments(String[] args) {
        String filePath = args[1];
        this.holder = new ArgumentsHolder.Builder().setFilePath(filePath).build();
    }

    public ArgumentsHolder getArguments() {
        return holder;
    }
}

class ArgumentsHolder {
    private String filePath;

    private ArgumentsHolder(Builder builder) {
        this.filePath = builder.filePath;
    }

    static class Builder {
        private String filePath;

        public Builder setFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public ArgumentsHolder build() {
            return new ArgumentsHolder(this);
        }
    }

    public String getFilePath() {
        return filePath;
    }

}
