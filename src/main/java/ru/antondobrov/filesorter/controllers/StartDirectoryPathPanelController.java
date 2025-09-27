package ru.antondobrov.filesorter.controllers;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller for the UI panel that manages the selection of the starting directory for the sorting
 * process.
 * <p>
 * This class connects a {@link TextField} and a corresponding 'Browse' button to a configuration
 * model (implementing {@link IStartDirectoryConfig}). It utilizes bidirectional data binding to
 * ensure the text field always reflects the current path in the model and vice-versa. When the user
 * clicks the button, it delegates the task of displaying a directory selection dialog to an
 * {@link IDirectoryChooserService}.
 */
public class StartDirectoryPathPanelController {
    /** A text field that displays and allows editing of the path to the start directory. */
    @FXML
    private TextField startDirectoryPathTextField;

    private final IStartDirectoryConfig config;
    private final IDirectoryChooserService directoryChooserService;

    /**
     * Constructs a new controller with its required dependencies.
     *
     * @param config The configuration model that holds the start directory path and provides the
     *        property for data binding.
     * @param directoryChooserService The service used to display a native directory selection
     *        dialog to the user.
     */
    public StartDirectoryPathPanelController(IStartDirectoryConfig config,
            IDirectoryChooserService directoryChooserService) {
        this.config = config;
        this.directoryChooserService = directoryChooserService;
    }

    /**
     * Event handler for the 'Choose Directory' button click.
     * <p>
     * This method invokes the {@code directoryChooserService} to display a dialog to the user. If
     * the user selects a directory (and does not cancel), the absolute path of the chosen directory
     * is set in the configuration model.
     *
     * @param event The {@link ActionEvent} triggered by the button click, used to determine the
     *        owner window for the dialog.
     */
    @FXML
    public void onChoiceStartDirectoryButtonClick(ActionEvent event) {
        File startDirectory = directoryChooserService.showDialog(event);

        if (startDirectory == null) {
            return;
        }

        config.getStartDirectoryPathProperty().set(startDirectory.getAbsolutePath());
    }

    @FXML
    void initialize() {
        startDirectoryPathTextField.textProperty()
                .bindBidirectional(config.getStartDirectoryPathProperty());
    }
}
