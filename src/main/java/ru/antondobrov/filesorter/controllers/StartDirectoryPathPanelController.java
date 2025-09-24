package ru.antondobrov.filesorter.controllers;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.antondobrov.filesorter.services.IDirectoryChooserService;
import ru.antondobrov.filesorter.services.IStartDirectoryHandler;

public class StartDirectoryPathPanelController {
    @FXML
    private TextField startDirectoryPathTextField;
    private final IStartDirectoryHandler startDirectoryHandler;
    private final IDirectoryChooserService directoryChooserService;

    public StartDirectoryPathPanelController(IStartDirectoryHandler startDirectoryHandler,
            IDirectoryChooserService directoryChooserService) {
        this.startDirectoryHandler = startDirectoryHandler;
        this.directoryChooserService = directoryChooserService;
    }

    @FXML
    void onChoiceStartDirectoryButtonClick(ActionEvent event) {
        Stage window = getStageFromEvent(event);
        File startDirectory = directoryChooserService.showDialog(window);

        if (startDirectory == null) {
            return;
        }
        // calls startDirectoryHandler and change visible path
        startDirectoryPathTextField.setText(startDirectory.getAbsolutePath());
    }

    private Stage getStageFromEvent(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        return window;
    }

    void initialize() {
        startDirectoryPathTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.trim().isEmpty()) {
                File newStartDirectory = new File(newValue.trim());
                startDirectoryHandler.handleStartDirectory(newStartDirectory);
            } else {
                startDirectoryHandler.handleStartDirectory(null);
            }
        });
    }
}
