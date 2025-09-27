package ru.antondobrov.filesorter.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A concrete implementation of {@link IRuleConfig} that defines a sorting rule based on file name
 * patterns.
 * <p>
 * This rule matches files against a list of patterns (e.g., "*.jpg", "document-*.pdf") and
 * specifies a destination directory where matching files should be moved. The class uses JavaFX
 * properties to enable easy binding with UI components and is configured with Jackson annotations
 * for seamless serialization to and from JSON format.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SortingRule implements IRuleConfig {
    private final StringProperty destinationPath;
    private final ObservableList<String> patterns;

    /**
     * Initializes a new {@code SortingRule} with default values. The destination path is set to an
     * empty string, and the list of patterns is empty.
     */
    public SortingRule() {
        this.destinationPath = new SimpleStringProperty("");
        this.patterns = FXCollections.observableArrayList();
    }

    /**
     * Gets the raw value of the destination path. This method is used by Jackson for serialization.
     *
     * @return The destination directory path as a String.
     */
    @JsonProperty("destinationPath")
    public String getDestinationPath() {
        return destinationPath.get();
    }

    /**
     * Sets the value of the destination path. This method is used by Jackson for deserialization.
     *
     * @param destinationPath The new destination directory path.
     */
    public void setDestinationPath(String destinationPath) {
        this.destinationPath.set(destinationPath);
    }

    /**
     * Provides the JavaFX {@link StringProperty} for the destination path. This is intended for UI
     * binding and is ignored by Jackson during serialization.
     *
     * @return The property for the destination path.
     */
    @JsonIgnore
    @Override
    public StringProperty getDestinationPathProperty() {
        return destinationPath;
    }

    /**
     * Returns the list of patterns as a standard {@link java.util.List}. This method is used by
     * Jackson for serialization.
     *
     * @return A list of file patterns.
     */
    @JsonProperty("patterns")
    public List<String> getPatternsList() {
        return new ArrayList<>(patterns);
    }

    /**
     * Sets the patterns from a standard {@link java.util.List}. This method is used by Jackson for
     * deserialization, populating the internal {@link ObservableList}.
     *
     * @param patterns A list of file patterns to set.
     */
    public void setPatternsList(List<String> patterns) {
        this.patterns.setAll(patterns);
    }

    /**
     * Provides the JavaFX {@link ObservableList} of patterns. This is intended for UI binding and
     * is ignored by Jackson during serialization.
     *
     * @return The observable list of patterns.
     */
    @JsonIgnore
    @Override
    public ObservableList<String> getPatterns() {
        return patterns;
    }

    @Override
    public String toString() {
        return "SortingRule{" + "\n\tdestinationPath='" + getDestinationPath() + ";\n"
                + "\n\tpatterns=[" + String.join(", ", patterns) + "];" + "\n}";
    }
}
