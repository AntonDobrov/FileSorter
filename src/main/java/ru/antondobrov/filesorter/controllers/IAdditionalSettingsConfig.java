package ru.antondobrov.filesorter.controllers;

import javafx.beans.property.ObjectProperty;
import ru.antondobrov.filesorter.model.ActionPolicy;

public interface IAdditionalSettingsConfig {
    public ObjectProperty<ActionPolicy> getTraverseSubdirectoriesPolicyProperty();

    public ObjectProperty<ActionPolicy> getDeleteOnSuccessPolicyProperty();

    public ObjectProperty<ActionPolicy> getDuplicateFilesPolicyProperty();
}
