package ru.antondobrov.filesorter.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.antondobrov.filesorter.controllers.IConfig;

/**
 * Represents the complete configuration for the file sorting process.
 * <p>
 * This class acts as a data model, holding all user-defined settings for a sorting operation. This
 * includes the source directory to scan, policies for handling subdirectories and file conflicts,
 * and a collection of sorting rules.
 * <p>
 * It uses JavaFX properties for seamless integration with the user interface and is annotated for
 * JSON serialization/deserialization via Jackson.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SorterConfig implements IConfig {
    private final StringProperty startDirectoryPath;
    private final ObjectProperty<ActionPolicy> traverseSubdirectoriesPolicy;
    private final ObjectProperty<ActionPolicy> deleteOnSuccessPolicy;
    private final ObjectProperty<ActionPolicy> duplicateFilesPolicy;
    private final ObservableList<IRuleConfig> sortingRules;

    /**
     * Initializes a new {@code SorterConfig} with default, safe values.
     * <p>
     * The start path is empty, all policies are set to {@code ActionPolicy.ASK} to prompt the user,
     * and the list of sorting rules is empty.
     */
    public SorterConfig() {
        this.startDirectoryPath = new SimpleStringProperty("");
        this.traverseSubdirectoriesPolicy = new SimpleObjectProperty<>(ActionPolicy.ASK);
        this.deleteOnSuccessPolicy = new SimpleObjectProperty<>(ActionPolicy.ASK);
        this.duplicateFilesPolicy = new SimpleObjectProperty<>(ActionPolicy.ASK);
        this.sortingRules = FXCollections.observableArrayList();
    }

    /**
     * Gets the path of the directory to start scanning from.
     *
     * @return The starting directory path.
     */
    @JsonProperty("startDirectoryPath")
    public String getStartDirectoryPath() {
        return startDirectoryPath.get();
    }

    /**
     * Sets the path of the directory to start scanning from.
     *
     * @param startDirectoryPath The starting directory path.
     */
    public void setStartDirectoryPath(String startDirectoryPath) {
        this.startDirectoryPath.set(startDirectoryPath);
    }

    /**
     * Provides the JavaFX {@link StringProperty} for the start directory path. Intended for UI
     * binding.
     *
     * @return The start directory path property.
     */
    @JsonIgnore
    @Override
    public StringProperty getStartDirectoryPathProperty() {
        return startDirectoryPath;
    }

    /**
     * Gets the policy for traversing subdirectories.
     *
     * @return The {@link ActionPolicy} for subdirectory traversal.
     */
    @JsonProperty("traverseSubdirectoriesPolicy")
    public ActionPolicy getTraverseSubdirectoriesPolicy() {
        return traverseSubdirectoriesPolicy.get();
    }

    /**
     * Sets the policy for traversing subdirectories.
     *
     * @param traverseSubdirectories The {@link ActionPolicy} for subdirectory traversal.
     */
    public void setTraverseSubdirectories(ActionPolicy traverseSubdirectories) {
        this.traverseSubdirectoriesPolicy.set(traverseSubdirectories);
    }

    /**
     * Provides the JavaFX {@link ObjectProperty} for the subdirectory traversal policy. Intended
     * for UI binding.
     *
     * @return The subdirectory traversal policy property.
     */
    @JsonIgnore
    @Override
    public ObjectProperty<ActionPolicy> getTraverseSubdirectoriesPolicyProperty() {
        return traverseSubdirectoriesPolicy;
    }

    /**
     * Gets the policy for deleting original files after a successful sort.
     *
     * @return The {@link ActionPolicy} for file deletion.
     */
    @JsonProperty("deleteOnSuccessPolicy")
    public ActionPolicy getDeleteOnSuccessPolicy() {
        return deleteOnSuccessPolicy.get();
    }

    /**
     * Sets the policy for deleting original files after a successful sort.
     *
     * @param deleteOnSuccessPolicy The {@link ActionPolicy} for file deletion.
     */
    public void setDeleteOnSuccessPolicy(ActionPolicy deleteOnSuccessPolicy) {
        this.deleteOnSuccessPolicy.set(deleteOnSuccessPolicy);
    }

    /**
     * Provides the JavaFX {@link ObjectProperty} for the file deletion policy. Intended for UI
     * binding.
     *
     * @return The file deletion policy property.
     */
    @JsonIgnore
    @Override
    public ObjectProperty<ActionPolicy> getDeleteOnSuccessPolicyProperty() {
        return deleteOnSuccessPolicy;
    }

    /**
     * Gets the policy for handling duplicate files in the destination directory.
     *
     * @return The {@link ActionPolicy} for handling duplicates.
     */
    @JsonProperty("duplicateFilesPolicy")
    public ActionPolicy getDuplicateFilesPolicy() {
        return duplicateFilesPolicy.get();
    }

    /**
     * Sets the policy for handling duplicate files.
     *
     * @param duplicateFilePolicy The {@link ActionPolicy} for handling duplicates.
     */
    public void setDuplicateFilesPolicy(ActionPolicy duplicateFilePolicy) {
        this.duplicateFilesPolicy.set(duplicateFilePolicy);
    }

    /**
     * Provides the JavaFX {@link ObjectProperty} for the duplicate file handling policy. Intended
     * for UI binding.
     *
     * @return The duplicate file handling policy property.
     */
    @JsonIgnore
    @Override
    public ObjectProperty<ActionPolicy> getDuplicateFilesPolicyProperty() {
        return duplicateFilesPolicy;
    }

    /**
     * Returns the collection of sorting rules as a standard {@link java.util.List}. This method is
     * used by Jackson for serialization.
     *
     * @return A list of {@link IRuleConfig} instances.
     */
    @JsonProperty("sortingRules")
    public List<IRuleConfig> getSortingRulesList() {
        return new ArrayList<>(sortingRules);
    }

    /**
     * Sets the sorting rules from a standard {@link java.util.List}. This method is used by Jackson
     * for deserialization.
     *
     * @param rules A list of {@link IRuleConfig} instances to set.
     */
    public void setSortingRules(List<IRuleConfig> rules) {
        this.sortingRules.setAll(rules);
    }

    /**
     * Provides the JavaFX {@link ObservableList} of sorting rules. This is intended for UI binding
     * and is ignored by Jackson.
     *
     * @return The observable list of sorting rules.
     */
    @JsonIgnore
    @Override
    public ObservableList<IRuleConfig> getRulesList() {
        return sortingRules;
    }

    @Override
    public String toString() {
        String values = "Start directory path: " + getStartDirectoryPath() + ";"
                + "\nTraverse subdirectories: " + getTraverseSubdirectoriesPolicy().toString() + ";"
                + "\nDelete on success: " + getDeleteOnSuccessPolicy().toString() + ";"
                + "\nDuplicate Files: " + getDuplicateFilesPolicy().toString() + ";"
                + getRulesList().toString() + ";";
        return "SorterConfig{" + values.replace("\n", "\n\t") + "\n" + "}";
    }
}
