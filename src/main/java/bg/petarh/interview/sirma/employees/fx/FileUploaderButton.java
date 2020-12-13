package bg.petarh.interview.sirma.employees.fx;

import bg.petarh.interview.sirma.employees.main.FxApp;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileUploaderButton extends Button {
    final FileChooser fileChooser = new FileChooser();

    public FileUploaderButton(Stage stage, FxApp fxApp) {
        super("File Uploader");

        this.setOnAction(
                e -> {
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        fxApp.setUpDataTable(fxApp.processFile(file.getAbsolutePath()));
                    }
                });
    }
}
