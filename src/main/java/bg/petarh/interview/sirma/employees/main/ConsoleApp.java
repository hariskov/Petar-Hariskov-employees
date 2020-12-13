package bg.petarh.interview.sirma.employees.main;

import bg.petarh.interview.sirma.employees.employees.EmployeeProjectResult;
import bg.petarh.interview.sirma.employees.employees.Project;
import bg.petarh.interview.sirma.employees.empmanagement.ListHolder;
import bg.petarh.interview.sirma.employees.empmanagement.ProjectEmployeesBuilder;
import bg.petarh.interview.sirma.employees.fio.FileOpen;
import bg.petarh.interview.sirma.employees.utils.EmployeesOverlapGenerator;
import bg.petarh.interview.sirma.employees.utils.MainArgumentsBuilder;

import java.io.IOException;
import java.util.List;

public class ConsoleApp implements EmployeeRunner {

    @Override
    public void run(MainArgumentsBuilder mab) {

        try {
            ListHolder listHolder = new ListHolder();
            List<String> lines = FileOpen.readFileToListOfLines(mab.getArguments().getFilePath());
            ProjectEmployeesBuilder projectEmployeesBuilder = new ProjectEmployeesBuilder(listHolder);
            projectEmployeesBuilder.buildProjectEmployeesFromList(lines);

            List<Project> projects = listHolder.getProjectListWrapper().getProjectList();

            for (Project project : projects) {
                EmployeeProjectResult result = EmployeesOverlapGenerator.findLongestCoEmployeesByProject(project);
                System.out.println(buildPrintString(result.getEmpId(), result.getProjectId(), result.getEmp2Id(), result.getDays()));
            }

        } catch (IOException ioException) {
            System.out.println("failed reading file");
        }
    }

    private String buildPrintString(int empId, int prId, int emp2Id, long daysWithBuddy) {
        String projectString = "For Project :" + prId + "; The employee :" + empId;

        if (daysWithBuddy == 0) {
            return projectString + " worked alone";
        }

        return projectString + " With Employee :" + emp2Id + "; days together :" + daysWithBuddy;
    }
}
