package bg.petarh.interview.sirma.employees.fx;

import bg.petarh.interview.sirma.employees.employees.EmployeeProjectResult;
import bg.petarh.interview.sirma.employees.employees.Project;
import bg.petarh.interview.sirma.employees.utils.EmployeesOverlapGenerator;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectTable extends TableView<EmployeeProjectResult> {

    public ProjectTable(){

        // copy from Oracle docs //
        TableColumn<EmployeeProjectResult, Integer> projectIdCol = new TableColumn<>("Project ID");
        TableColumn<EmployeeProjectResult, Integer> firstEmpId = new TableColumn<>("First Employee ID");
        TableColumn<EmployeeProjectResult, Integer> secondEmpId = new TableColumn<>("Second Employee ID");
        TableColumn<EmployeeProjectResult, Integer> overlap = new TableColumn<>("Workdays Together");

        projectIdCol.setCellValueFactory(new PropertyValueFactory(EmployeeProjectResult._PROJECT_ID));
        firstEmpId.setCellValueFactory(new PropertyValueFactory(EmployeeProjectResult._EMPLOYEE_ID));
        secondEmpId.setCellValueFactory(new PropertyValueFactory(EmployeeProjectResult._EMPLOYEE_2_ID));
        overlap.setCellValueFactory(new PropertyValueFactory(EmployeeProjectResult._DAYS));

        this.getColumns().setAll(projectIdCol, firstEmpId, secondEmpId, overlap);
        this.setHeight(500);
        this.setWidth(500);
    }

    public void setProjectEmployees(List<Project> projects){
        List<EmployeeProjectResult> result = projects.stream().map(EmployeesOverlapGenerator::findLongestCoEmployeesByProject).collect(Collectors.toList());
        this.setItems(FXCollections.observableArrayList(result));
    }
}
