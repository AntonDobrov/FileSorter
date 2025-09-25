package ru.antondobrov.filesorter.services;

import java.io.File;
import javafx.event.ActionEvent;

public interface IFileChooserService {
    File showOpenDialog(ActionEvent event);

    File showSaveDialog(ActionEvent event);
}
