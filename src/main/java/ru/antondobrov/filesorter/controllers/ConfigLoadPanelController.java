package ru.antondobrov.filesorter.controllers;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        File configFile = fileChooserService.showSaveDialog(event);

        if (configFile == null) {
            return;
        }

        configLoadService.saveConfig(configFile);
    }

    @FXML
    public void onOpenConfigButtonClick(ActionEvent event) {
        File configFile = fileChooserService.showOpenDialog(event);

        if (configFile == null) {
            return;
        }

        configLoadService.openConfig(configFile);
    }

    @FXML
    void initialize() { /* Need for javafx */ }
}
