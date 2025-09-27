package ru.antondobrov.filesorter.controllers;

import java.io.File;
import javafx.event.ActionEvent;

/**
 * Defines a contract for a service that displays a native directory selection dialog.
 * <p>
 * This interface abstracts the process of prompting the user to select a directory, decoupling UI
 * controllers from the concrete implementation of JavaFX's {@code DirectoryChooser}. This
 * abstraction is particularly useful for testing, as it allows for a mock implementation to be
 * injected, which can return a predefined file path without requiring any actual user interaction
 * or a running UI.
 */
public interface IDirectoryChooserService {

    /**
     * Displays a native directory selection dialog to the user.
     * <p>
     * The dialog allows the user to browse their file system and select a folder.
     *
     * @param event The {@link ActionEvent} that triggered the dialog. It is used to derive the
     *        owner window for the dialog, ensuring it is properly parented to the main application
     *        window.
     * @return The selected {@link File} object representing the chosen directory if the user
     *         confirms their choice, or {@code null} if the user cancels the dialog.
     */
    File showDialog(ActionEvent event);
}
