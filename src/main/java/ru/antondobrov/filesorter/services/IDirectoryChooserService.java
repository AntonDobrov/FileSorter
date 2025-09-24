package ru.antondobrov.filesorter.services;

import java.io.File;
import javafx.stage.Stage;

public interface IDirectoryChooserService {
    public File showDialog(Stage ownerWindow);
}
