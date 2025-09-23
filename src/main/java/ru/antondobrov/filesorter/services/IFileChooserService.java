package ru.antondobrov.filesorter.services;

import java.io.File;
import javafx.stage.Stage;

public interface IFileChooserService {
    File showOpenDialog(Stage ownerWindow);

    File showSaveDialog(Stage ownerWindow);
}
