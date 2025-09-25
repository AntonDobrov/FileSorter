package ru.antondobrov.filesorter.services;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public interface IRuleConfig {
    public StringProperty getDestinationDirectoryPathProperty();

    public ObservableList<String> getPatternList();
}
