package ru.antondobrov.filesorter.controllers;

import java.io.File;
import javafx.event.ActionEvent;

/**
 * Defines a contract for a service that displays native file selection dialogs to the user.
 * <p>
 * This interface abstracts the creation and display of standard 'Open' and 'Save' dialogs,
 * decoupling the UI controllers from the specific implementation details of JavaFX's
 * {@code FileChooser}. This abstraction is particularly useful for testing, as it allows for a mock
 * implementation to be injected, which can return a predefined file path without requiring any
 * actual user interaction or a running UI.
 */
public interface IFileChooserService {

    /**
     * Displays a native 'Open File' dialog to the user.
     * <p>
     * The dialog is typically configured to allow the user to browse their file system and select
     * an existing file.
     *
     * @param event The {@link ActionEvent} that triggered the dialog. It is used to derive the
     *        owner window for the dialog, ensuring it is properly parented to the main application
     *        window.
     * @return The selected {@link File} object if the user confirms their choice, or {@code null}
     *         if the user cancels the dialog.
     */
    File showOpenDialog(ActionEvent event);

    /**
     * Displays a native 'Save File' dialog to the user.
     * <p>
     * This dialog allows the user to browse their file system, specify a new filename, and choose a
     * location to save a file.
     *
     * @param event The {@link ActionEvent} that triggered the dialog. It is used to derive the
     *        owner window for the dialog, ensuring it is properly parented to the main application
     *        window.
     * @return The selected {@link File} object representing the chosen path and filename if the
     *         user confirms, or {@code null} if the user cancels the dialog.
     */
    File showSaveDialog(ActionEvent event);
}
