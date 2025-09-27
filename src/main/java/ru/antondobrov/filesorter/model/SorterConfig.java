package ru.antondobrov.filesorter.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents the complete configuration for the file sorting process.
 * <p>
 * This model class holds all the settings required for a sorting operation: the start directory,
 * behavior policies (traversing subdirectories, deleting source files, handling duplicates), and a
 * list of sorting rules.
 */
public class SorterConfig {
    private final StringProperty startDirectoryPath;
    private final ObjectProperty<ActionPolicy> traverseSubdirectories;
    private final ObjectProperty<ActionPolicy> deleteOnSuccessPolicy;
    private final ObjectProperty<ActionPolicy> duplicateFilesPolicy;
    private final ObservableList<SortingRule> sortingRules;

    /**
     * Constructs a configuration instance with default values.
     * <p>
     * The start path is set to an empty string, all policies are set to {@code ActionPolicy.ASK},
     * and the list of sorting rules is created empty.
     */
    public SorterConfig() {
        this.startDirectoryPath = new SimpleStringProperty("");
        this.traverseSubdirectories = new SimpleObjectProperty<>(ActionPolicy.ASK);
        this.deleteOnSuccessPolicy = new SimpleObjectProperty<>(ActionPolicy.ASK);
        this.duplicateFilesPolicy = new SimpleObjectProperty<>(ActionPolicy.ASK);
        this.sortingRules = FXCollections.observableArrayList();
    }

    /**
     * Returns the path to the start directory for scanning.
     *
     * @return The directory path.
     */
    public String getStartDirectoryPath() {
        return startDirectoryPath.get();
    }

    /**
     * Sets the path to the start directory.
     *
     * @param startDirectoryPath The directory path.
     */
    public void setStartDirectoryPath(String startDirectoryPath) {
        this.startDirectoryPath.set(startDirectoryPath);
    }

    /**
     * Returns the {@link StringProperty} for the start directory path. Allows binding this field to
     * UI components.
     *
     * @return The path property.
     */
    public StringProperty getStartDirectoryPathProperty() {
        return startDirectoryPath;
    }

    /**
     * Returns the policy for traversing subdirectories. It determines whether to scan nested
     * folders.
     *
     * @return The {@link ActionPolicy}.
     */
    public ActionPolicy getTraverseSubdirectories() {
        return traverseSubdirectories.get();
    }

    /**
     * Sets the policy for traversing subdirectories.
     *
     * @param traverseSubdirectories The {@link ActionPolicy}.
     */
    public void setTraverseSubdirectories(ActionPolicy traverseSubdirectories) {
        this.traverseSubdirectories.set(traverseSubdirectories);
    }

    /**
     * Returns the {@link ObjectProperty} for the subdirectory traversal policy. Allows binding to
     * UI components.
     *
     * @return The policy property.
     */
    public ObjectProperty<ActionPolicy> getTraverseSubdirectoriesProperty() {
        return traverseSubdirectories;
    }

    /**
     * Returns the policy for deleting original files after a successful sort. It determines whether
     * the original file should be deleted after being copied/moved.
     *
     * @return The {@link ActionPolicy}.
     */
    public ActionPolicy getDeleteOnSuccessPolicy() {
        return deleteOnSuccessPolicy.get();
    }

    /**
     * Sets the policy for deleting original files.
     *
     * @param deleteOnSuccessPolicy The {@link ActionPolicy}.
     */
    public void setDeleteOnSuccessPolicy(ActionPolicy deleteOnSuccessPolicy) {
        this.deleteOnSuccessPolicy.set(deleteOnSuccessPolicy);
    }

    /**
     * Returns the {@link ObjectProperty} for the original file deletion policy.
     *
     * @return The policy property.
     */
    public ObjectProperty<ActionPolicy> getDeleteOnSuccessPolicyProperty() {
        return deleteOnSuccessPolicy;
    }

    /**
     * Returns the policy for handling duplicate files in the destination folder. It determines what
     * to do if a file with the same name already exists.
     *
     * @return The {@link ActionPolicy}.
     */
    public ActionPolicy getDuplicateFilesPolicy() {
        return duplicateFilesPolicy.get();
    }

    /**
     * Sets the policy for handling duplicates.
     *
     * @param duplicateFilePolicy The {@link ActionPolicy}.
     */
    public void setDuplicateFilesPolicy(ActionPolicy duplicateFilePolicy) {
        this.duplicateFilesPolicy.set(duplicateFilePolicy);
    }

    /**
     * Returns the {@link ObjectProperty} for the duplicate file handling policy.
     *
     * @return The policy property.
     */
    public ObjectProperty<ActionPolicy> getDuplicateFilesPolicyProperty() {
        return duplicateFilesPolicy;
    }

    /**
     * Returns the observable list of sorting rules.
     * <p>
     * Using an {@link ObservableList} allows the UI to be automatically updated when rules are
     * added, removed, or reordered.
     *
     * @return The observable list of sorting rule views.
     */
    public ObservableList<SortingRule> getSortingRules() {
        return sortingRules;
    }

    @Override
    public String toString() {
        String values = "Start directory path: " + getStartDirectoryPath() + ";"
                + "\nTraverse subdirectories: " + getTraverseSubdirectories().toString() + ";"
                + "\nDelete on success: " + getDeleteOnSuccessPolicy().toString() + ";"
                + "\nDuplicate Files: " + getDuplicateFilesPolicy().toString() + ";"
                + getSortingRules().toString() + ";";
        return "SorterConfig{" + values.replace("\n", "\n\t") + "\n" + "}";
    }
}
