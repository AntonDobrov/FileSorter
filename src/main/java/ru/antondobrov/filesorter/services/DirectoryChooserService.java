package ru.antondobrov.filesorter.services;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ru.antondobrov.filesorter.controllers.IDirectoryChooserService;
import ru.antondobrov.filesorter.utils.DirectoryChooserFactory;
import ru.antondobrov.filesorter.utils.ILocalizer;

/**
 * A concrete implementation of the {@link IDirectoryChooserService} interface that displays a
 * native directory selection dialog.
 * <p>
 * This class orchestrates the creation, configuration, and display of a JavaFX
 * {@link DirectoryChooser}. It leverages an {@link ILocalizer} to set a localized title for the
 * dialog window and a {@link DirectoryChooserFactory} to decouple the service from the direct
 * instantiation of the chooser. This factory-based approach improves modularity and simplifies
 * testing by allowing a mock factory to be injected.
 */
public class DirectoryChooserService implements IDirectoryChooserService {
    private final ILocalizer localizer;
    private final DirectoryChooserFactory factory;

    /**
     * Constructs a new DirectoryChooserService with its required dependencies.
     *
     * @param localizer The localization service used to retrieve the dialog's title.
     * @param factory The factory responsible for creating new {@link DirectoryChooser} instances.
     */
    public DirectoryChooserService(ILocalizer localizer, DirectoryChooserFactory factory) {
        this.localizer = localizer;
        this.factory = factory;
    }

    /**
     * Creates, configures, and displays a native directory selection dialog.
     * <p>
     * The method first obtains a localized title, then creates and configures the
     * {@code DirectoryChooser} with a default initial directory (the user's home folder). Finally,
     * it presents the dialog to the user, parented to the window that triggered the event.
     *
     * @param event The {@link ActionEvent} that triggered the dialog. It is used to obtain the
     *        parent {@link Stage} (window) for the dialog.
     * @return The selected {@link File} object if the user confirms the selection, or {@code null}
     *         if the dialog is cancelled.
     */
    @Override
    public File showDialog(ActionEvent event) {
        String startDirectoryTitle = localizer.get("directory.choose.dialog.title");

        DirectoryChooser directoryChooser = createDirectoryChooser(startDirectoryTitle);

        return directoryChooser.showDialog(getStageFromEvent(event));
    }

    private DirectoryChooser createDirectoryChooser(String title) {
        DirectoryChooser directoryChooser = factory.create();
        directoryChooser.setTitle(title);
        String userHomeDirectory = System.getProperty("user.home");
        File initialDirectory = new File(userHomeDirectory);
        directoryChooser.setInitialDirectory(initialDirectory);
        return directoryChooser;
    }

    private Stage getStageFromEvent(ActionEvent event) {
        Node source = (Node) event.getSource();
        return (Stage) source.getScene().getWindow();
    }
}
