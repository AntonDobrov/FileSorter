package ru.antondobrov.filesorter.services;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.antondobrov.filesorter.utils.FileChooserFactory;
import ru.antondobrov.filesorter.utils.ILocalizer;

public class FileChooserService implements IFileChooserService {

    private final ILocalizer localizer;
    private final FileChooserFactory factory;

    public FileChooserService(ILocalizer localizer, FileChooserFactory factory) {
        this.localizer = localizer;
        this.factory = factory;
    }

    @Override
    public File showOpenDialog(Stage ownerWindow) {
        String openConfigFileDialogTitle = localizer.get("open.config.file.dialog.title");

        FileChooser fileChooser = createFileChooser(openConfigFileDialogTitle);

        return fileChooser.showOpenDialog(ownerWindow);
    }

    @Override
    public File showSaveDialog(Stage ownerWindow) {
        String saveConfigFileDialogTitle = localizer.get("save.config.file.dialog.title");

        FileChooser fileChooser = createFileChooser(saveConfigFileDialogTitle);

        String configInitialName = localizer.get("config.initial.name");

        fileChooser.setInitialFileName(configInitialName);

        String jsonFilesExtensionDescription = localizer.get("json.files.extension.description");

        FileChooser.ExtensionFilter extensionFilter =
                new FileChooser.ExtensionFilter(jsonFilesExtensionDescription, "*.json");
        fileChooser.getExtensionFilters().add(extensionFilter);

        return fileChooser.showSaveDialog(ownerWindow);
    }


    private FileChooser createFileChooser(String title) {
        FileChooser fileChooser = factory.create();
        fileChooser.setTitle(title);
        String userHomeDirectory = System.getProperty("user.home");
        File initialDirectory = new File(userHomeDirectory);
        fileChooser.setInitialDirectory(initialDirectory);
        return fileChooser;
    }
}
