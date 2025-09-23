package ru.antondobrov.filesorter.controllers;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import ru.antondobrov.filesorter.services.IConfigLoadService;
import ru.antondobrov.filesorter.services.IFileChooserService;

public class ConfigLoadPanelController {
    private final IConfigLoadService configLoadService;
    private final IFileChooserService fileChooserService;

    public ConfigLoadPanelController(IConfigLoadService configLoadService,
            IFileChooserService fileChooserService) {
        this.configLoadService = configLoadService;
        this.fileChooserService = fileChooserService;
    }

    @FXML
    public void onSaveConfigButtonClick(ActionEvent event) {
        Stage window = getStageFromEvent(event);
        File configFile = fileChooserService.showSaveDialog(window);

        if (configFile == null) {
            return;
        }

        configLoadService.saveConfig(configFile);
    }

    private Stage getStageFromEvent(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        return window;
    }

    @FXML
    public void onOpenConfigButtonClick(ActionEvent event) {
        Stage window = getStageFromEvent(event);
        File configFile = fileChooserService.showOpenDialog(window);

        if (configFile == null) {
            return;
        }

        configLoadService.openConfig(configFile);
    }

    @FXML
    void initialize() {}
}
