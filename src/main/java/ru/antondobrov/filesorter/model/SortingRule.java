package ru.antondobrov.filesorter.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a rule for sorting files.
 * <p>
 * A rule consists of a destination path ({@code destinationPath}) and a list of filename patterns
 * ({@code patterns}). Files that match one of the patterns should be moved to the specified
 * destination folder.
 * <p>
 * This class is designed using JavaFX Properties, making it easy to bind its fields to UI
 * components.
 */
public class SortingRule {
    private final StringProperty destinationPath;
    private final ObservableList<String> patterns;

    /**
     * Constructs a new, empty sorting rule. The destination path is initialized as an empty string,
     * and the patterns list is empty.
     */
    public SortingRule() {
        this.destinationPath = new SimpleStringProperty("");
        this.patterns = FXCollections.observableArrayList();
    }

    /**
     * Constructs a new sorting rule with a specified destination path.
     *
     * @param destinationPath The initial destination path.
     */
    public SortingRule(String destinationPath) {
        this.destinationPath = new SimpleStringProperty(destinationPath);
        this.patterns = FXCollections.observableArrayList();
    }

    /**
     * Constructs a new sorting rule with a specified destination path and list of patterns.
     *
     * @param destinationPath The destination path.
     * @param patterns The list of filename patterns.
     */
    public SortingRule(String destinationPath, ObservableList<String> patterns) {
        this.destinationPath = new SimpleStringProperty(destinationPath);
        this.patterns = patterns;
    }

    /**
     * Gets the destination path.
     *
     * @return The current destination path.
     */
    public String getDestinationPath() {
        return destinationPath.get();
    }

    /**
     * Sets the destination path.
     *
     * @param destinationPath The new destination path.
     */
    public void setDestinationPath(String destinationPath) {
        this.destinationPath.set(destinationPath);
    }

    /**
     * Returns the destination path property.
     * <p>
     * This is useful for binding to JavaFX UI components.
     *
     * @return The {@link StringProperty} for the destination path.
     */
    public StringProperty getDestinationPathProperty() {
        return destinationPath;
    }

    /**
     * Returns the observable list of filename patterns.
     * <p>
     * Since the list is observable, any modifications to it will be automatically reflected in
     * bound UI components.
     *
     * @return The {@link ObservableList} of patterns.
     */
    public ObservableList<String> getPatterns() {
        return patterns;
    }

    @Override
    public String toString() {
        return "SortingRule{" + "\n\tdestinationPath='" + getDestinationPath() + ";\n"
                + "\n\tpatterns=[" + String.join(", ", patterns) + "];" + "\n}";
    }
}
