package ru.antondobrov.filesorter.services;

import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
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
    public File showDialog(Stage ownerWindow) {
        String startDirectoryTitle = localizer.get("start.directory.choose.dialog.title");

        DirectoryChooser directoryChooser = createDirectoryChooser(startDirectoryTitle);

        return directoryChooser.showDialog(ownerWindow);
    }

    private DirectoryChooser createDirectoryChooser(String title) {
        DirectoryChooser directoryChooser = factory.create();
        directoryChooser.setTitle(title);
        String userHomeDirectory = System.getProperty("user.home");
        File initialDirectory = new File(userHomeDirectory);
        directoryChooser.setInitialDirectory(initialDirectory);
        return directoryChooser;
    }
}
