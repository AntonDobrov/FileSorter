package ru.antondobrov.filesorter.controllers;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller for the UI panel that handles saving and loading of the application configuration.
 * <p>
 * This class orchestrates the process of configuration persistence. It responds to user actions
 * (button clicks) by invoking a file chooser service to get a file path from the user, and then
 * delegates the actual serialization or deserialization of the configuration to a dedicated loading
 * service.
 */
public class ConfigLoadPanelController {
    private final IConfigLoadService configLoadService;
    private final IFileChooserService fileChooserService;

    /**
     * Constructs a new controller with its required service dependencies.
     *
     * @param configLoadService The service responsible for serializing the configuration to a file
     *        and deserializing it back.
     * @param fileChooserService The service responsible for displaying native 'Save' and 'Open'
     *        file dialogs to the user.
     */
    public ConfigLoadPanelController(IConfigLoadService configLoadService,
            IFileChooserService fileChooserService) {
        this.configLoadService = configLoadService;
        this.fileChooserService = fileChooserService;
    }

    /**
     * Event handler for the 'Save Configuration' button click.
     * <p>
     * It opens a 'Save' file dialog, prompting the user to choose a location and filename. If the
     * user selects a file, it passes the selected {@code File} object to the
     * {@code configLoadService} to perform the save operation. If the user cancels the dialog, no
     * action is taken.
     *
     * @param event The {@link ActionEvent} triggered by the button click. It is used to determine
     *        the owner window for the file dialog.
     */
    @FXML
    public void onSaveConfigButtonClick(ActionEvent event) {
        File configFile = fileChooserService.showSaveDialog(event);

        if (configFile == null) {
            return;
        }

        configLoadService.saveConfig(configFile);
    }

    /**
     * Event handler for the 'Open Configuration' button click.
     * <p>
     * It opens an 'Open' file dialog, prompting the user to select an existing configuration file.
     * If the user selects a file, it passes the selected {@code File} object to the
     * {@code configLoadService} to perform the load operation. If the user cancels the dialog, no
     * action is taken.
     *
     * @param event The {@link ActionEvent} triggered by the button click. It is used to determine
     *        the owner window for the file dialog.
     */
    @FXML
    public void onOpenConfigButtonClick(ActionEvent event) {
        File configFile = fileChooserService.showOpenDialog(event);

        if (configFile == null) {
            return;
        }

        configLoadService.openConfig(configFile);
    }


    @FXML
    void initialize() { /* Needed for JavaFX FXML lifecycle */ }
}
