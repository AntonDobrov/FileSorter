package ru.antondobrov.filesorter.model;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public interface IRuleConfig {
    public StringProperty getDestinationPathProperty();

    public ObservableList<String> getPatterns();
}
