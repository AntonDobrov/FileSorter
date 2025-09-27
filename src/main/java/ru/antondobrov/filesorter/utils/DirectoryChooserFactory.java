package ru.antondobrov.filesorter.utils;

import javafx.stage.DirectoryChooser;

public class DirectoryChooserFactory {
    public DirectoryChooser create() {
        return new DirectoryChooser();
    }
}
