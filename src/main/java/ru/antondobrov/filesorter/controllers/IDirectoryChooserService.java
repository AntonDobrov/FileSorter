package ru.antondobrov.filesorter.controllers;

import java.io.File;
import javafx.event.ActionEvent;

public interface IDirectoryChooserService {
    public File showDialog(ActionEvent event);
}
