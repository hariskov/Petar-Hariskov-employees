package bg.petarh.interview.sirma.employees.main;

import bg.petarh.interview.sirma.employees.employees.ProjectEmployee;
import bg.petarh.interview.sirma.employees.empmanagement.ListHolder;
import bg.petarh.interview.sirma.employees.empmanagement.ProjectEmployeesBuilder;
import bg.petarh.interview.sirma.employees.fio.FileOpen;
import bg.petarh.interview.sirma.employees.fx.FileUploaderButton;
import bg.petarh.interview.sirma.employees.fx.ProjectTable;
import bg.petarh.interview.sirma.employees.utils.MainArgumentsBuilder;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FxApp extends Application implements EmployeeRunner {

    private VBox vbox;

    @Override
    public void run(MainArgumentsBuilder mab) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        StackPane stackPanel = new StackPane();
        FileUploaderButton fileUploaderButton = new FileUploaderButton(stage, this);

        vbox = new VBox();
        vbox.setSpacing(15);
        vbox.setPadding(new Insets(15, 12, 15, 12));

        stage.setTitle("Employee Overlap");

        GridPane.setConstraints(fileUploaderButton, 0, 0);

        vbox.getChildren().addAll(fileUploaderButton);

        stackPanel.getChildren().addAll(vbox);

        Scene scene = new Scene(stackPanel, 1024, 768);
        stage.setScene(scene);
        stage.show();
    }

    public List<ProjectEmployee> processFile(String absolutePath) {
        try {
            ListHolder listHolder = new ListHolder();
            List<String> lines = FileOpen.readFileToListOfLines(absolutePath);
            ProjectEmployeesBuilder projectEmployeesBuilder = new ProjectEmployeesBuilder(listHolder);
            return projectEmployeesBuilder.buildProjectEmployeesFromList(lines);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void setUpDataTable(List<ProjectEmployee> projectEmployees) {
        ProjectTable table = new ProjectTable();

        table.setProjectEmployees(projectEmployees.stream().map(ProjectEmployee::getProject).distinct().collect(Collectors.toList()));
        vbox.getChildren().removeIf(e -> e instanceof ProjectTable); // redraw maybe ?
        vbox.getChildren().add(table);
    }
}
