package ru.antondobrov.filesorter.services;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.antondobrov.filesorter.controllers.IFileChooserService;
import ru.antondobrov.filesorter.utils.FileChooserFactory;
import ru.antondobrov.filesorter.utils.ILocalizer;

/**
 * A concrete implementation of the {@link IFileChooserService} interface that displays native
 * 'Open' and 'Save' file dialogs.
 * <p>
 * This class handles the detailed configuration of the JavaFX {@link FileChooser}, such as setting
 * localized titles, default filenames, and extension filters. It uses an {@link ILocalizer} for all
 * user-facing text and a {@link FileChooserFactory} to create chooser instances, promoting loose
 * coupling and easier testing. This factory-based approach improves modularity and simplifies
 * testing by allowing a mock factory to be injected.
 */
public class FileChooserService implements IFileChooserService {

    private final ILocalizer localizer;
    private final FileChooserFactory factory;

    /**
     * Constructs a new FileChooserService with its required dependencies.
     *
     * @param localizer The localization service used for all user-facing text in the dialogs.
     * @param factory The factory responsible for creating new {@link FileChooser} instances.
     */
    public FileChooserService(ILocalizer localizer, FileChooserFactory factory) {
        this.localizer = localizer;
        this.factory = factory;
    }

    /**
     * Creates and displays a native 'Open File' dialog configured for loading a configuration file.
     * <p>
     * This implementation creates a standard file chooser, sets a localized title, and presents it
     * to the user.
     *
     * @param event The {@link ActionEvent} that triggered the dialog, used to determine the parent
     *        window.
     * @return The {@link File} selected by the user, or {@code null} if the dialog was cancelled.
     */
    @Override
    public File showOpenDialog(ActionEvent event) {
        String openConfigFileDialogTitle = localizer.get("open.config.file.dialog.title");

        FileChooser fileChooser = createFileChooser(openConfigFileDialogTitle);

        return fileChooser.showOpenDialog(getStageFromEvent(event));
    }

    /**
     * Creates and displays a native 'Save File' dialog configured for saving a configuration file.
     * <p>
     * This implementation performs more detailed configuration than the open dialog. It sets a
     * localized title, a default initial filename (e.g., "config.json"), and an extension filter to
     * guide the user towards saving the file with a '.json' extension.
     *
     * @param event The {@link ActionEvent} that triggered the dialog, used to determine the parent
     *        window.
     * @return The {@link File} representing the path and filename chosen by the user, or
     *         {@code null} if the dialog was cancelled.
     */
    @Override
    public File showSaveDialog(ActionEvent event) {
        String saveConfigFileDialogTitle = localizer.get("save.config.file.dialog.title");

        FileChooser fileChooser = createFileChooser(saveConfigFileDialogTitle);

        String configInitialName = localizer.get("config.initial.name");
        fileChooser.setInitialFileName(configInitialName);

        String jsonFilesExtensionDescription = localizer.get("json.files.extension.description");
        FileChooser.ExtensionFilter extensionFilter =
                new FileChooser.ExtensionFilter(jsonFilesExtensionDescription, "*.json");
        fileChooser.getExtensionFilters().add(extensionFilter);

        return fileChooser.showSaveDialog(getStageFromEvent(event));
    }

    private FileChooser createFileChooser(String title) {
        FileChooser fileChooser = factory.create();
        fileChooser.setTitle(title);
        String userHomeDirectory = System.getProperty("user.home");
        File initialDirectory = new File(userHomeDirectory);
        fileChooser.setInitialDirectory(initialDirectory);
        return fileChooser;
    }

    private Stage getStageFromEvent(ActionEvent event) {
        Node source = (Node) event.getSource();
        return (Stage) source.getScene().getWindow();
    }
}
