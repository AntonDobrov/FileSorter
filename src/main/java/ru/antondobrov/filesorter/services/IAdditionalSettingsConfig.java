package ru.antondobrov.filesorter.services;

import javafx.beans.property.ObjectProperty;
import ru.antondobrov.filesorter.model.ActionPolicy;

public interface IAdditionalSettingsConfig {
    ObjectProperty<ActionPolicy> getTraverseSubdirectoriesPolicyProperty();

    ObjectProperty<ActionPolicy> getDeleteOnSuccessPolicyProperty();

    ObjectProperty<ActionPolicy> getDuplicateFilesPolicyProperty();
}
