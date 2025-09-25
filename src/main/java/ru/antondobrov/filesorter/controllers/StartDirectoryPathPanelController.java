package ru.antondobrov.filesorter.controllers;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.antondobrov.filesorter.services.IDirectoryChooserService;
import ru.antondobrov.filesorter.services.IStartDirectoryConfig;

public class StartDirectoryPathPanelController {
    @FXML
    private TextField startDirectoryPathTextField;
    private final IStartDirectoryConfig config;
    private final IDirectoryChooserService directoryChooserService;

    public StartDirectoryPathPanelController(IStartDirectoryConfig config,
            IDirectoryChooserService directoryChooserService) {
        this.config = config;
        this.directoryChooserService = directoryChooserService;
    }

    @FXML
    void onChoiceStartDirectoryButtonClick(ActionEvent event) {
        Stage window = getStageFromEvent(event);
        File startDirectory = directoryChooserService.showDialog(window);

        if (startDirectory == null) {
            return;
        }

        config.getStartDirectoryPathProperty().set(startDirectory.getAbsolutePath());
    }

    private Stage getStageFromEvent(ActionEvent event) {
        Node source = (Node) event.getSource();
        return (Stage) source.getScene().getWindow();
    }

    void initialize() {
        startDirectoryPathTextField.textProperty()
                .bindBidirectional(config.getStartDirectoryPathProperty());
    }
}
