package ru.antondobrov.filesorter.controllers;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
    public void onChoiceStartDirectoryButtonClick(ActionEvent event) {
        File startDirectory = directoryChooserService.showDialog(event);

        if (startDirectory == null) {
            return;
        }

        config.getStartDirectoryPathProperty().set(startDirectory.getAbsolutePath());
    }

    void initialize() {
        startDirectoryPathTextField.textProperty()
                .bindBidirectional(config.getStartDirectoryPathProperty());
    }
}
