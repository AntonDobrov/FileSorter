package ru.antondobrov.filesorter.controllers;

import java.io.File;
import javafx.event.ActionEvent;

public interface IFileChooserService {
    public File showOpenDialog(ActionEvent event);

    public File showSaveDialog(ActionEvent event);
}
