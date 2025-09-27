package ru.antondobrov.filesorter.controllers;

import javafx.beans.property.ObjectProperty;
import ru.antondobrov.filesorter.model.ActionPolicy;

/**
 * Defines a contract for a configuration model that provides access to properties for advanced
 * sorting options.
 * <p>
 * This interface isolates the settings for subdirectory traversal, file deletion, and duplicate
 * handling. It allows a UI controller, such as {@link AdditionalSettingsPanelController}, to be
 * decoupled from the main configuration model, depending only on the specific properties it needs
 * to manage. This enhances modularity and simplifies testing.
 */
public interface IAdditionalSettingsConfig {

    /**
     * Returns the {@link ObjectProperty} for the subdirectory traversal policy. This policy
     * determines whether the sorting process should scan nested folders.
     * <p>
     * The returned property is intended for bidirectional binding with a UI control (e.g., a
     * {@code ChoiceBox}), ensuring that changes in the UI are reflected in the model and
     * vice-versa.
     *
     * @return The property representing the {@link ActionPolicy} for traversing subdirectories.
     */
    ObjectProperty<ActionPolicy> getTraverseSubdirectoriesPolicyProperty();

    /**
     * Returns the {@link ObjectProperty} for the policy on deleting original files after a
     * successful sort. This policy determines if source files should be removed after being copied
     * or moved.
     * <p>
     * The returned property is intended for bidirectional binding with a UI control (e.g., a
     * {@code ChoiceBox}), ensuring that changes in the UI are reflected in the model and
     * vice-versa.
     *
     * @return The property representing the {@link ActionPolicy} for deleting files on success.
     */
    ObjectProperty<ActionPolicy> getDeleteOnSuccessPolicyProperty();

    /**
     * Returns the {@link ObjectProperty} for the policy on handling duplicate files. This policy
     * defines the action to take when a file with the same name already exists in the destination
     * directory.
     * <p>
     * The returned property is intended for bidirectional binding with a UI control (e.g., a
     * {@code ChoiceBox}), ensuring that changes in the UI are reflected in the model and
     * vice-versa.
     *
     * @return The property representing the {@link ActionPolicy} for duplicate file handling.
     */
    ObjectProperty<ActionPolicy> getDuplicateFilesPolicyProperty();
}
