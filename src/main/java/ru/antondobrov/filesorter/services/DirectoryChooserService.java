package ru.antondobrov.filesorter.services;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ru.antondobrov.filesorter.controllers.IDirectoryChooserService;
import ru.antondobrov.filesorter.utils.DirectoryChooserFactory;
import ru.antondobrov.filesorter.utils.ILocalizer;

public class DirectoryChooserService implements IDirectoryChooserService {
    private final ILocalizer localizer;
    private final DirectoryChooserFactory factory;

    public DirectoryChooserService(ILocalizer localizer, DirectoryChooserFactory factory) {
        this.localizer = localizer;
        this.factory = factory;
    }

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
