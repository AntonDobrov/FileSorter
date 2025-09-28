package ru.antondobrov.filesorter.model;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Defines the contract for a file sorting rule.
 * <p>
 * A rule specifies the criteria for matching files and the destination where they should be moved.
 * This interface is designed for polymorphic deserialization using Jackson. It allows various rule
 * implementations (e.g., {@link SortingRule}) to be managed within a single configuration file and
 * be correctly instantiated when loading from JSON.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({@JsonSubTypes.Type(value = SortingRule.class, name = "sorting_rule")})
public interface IRuleConfig {
    /**
     * Returns the JavaFX property for the destination path.
     * <p>
     * This allows the UI to bind directly to the destination path of a rule.
     *
     * @return The {@link StringProperty} for the destination path.
     */
    StringProperty getDestinationPathProperty();

    /**
     * Returns the observable list of file patterns associated with this rule.
     * <p>
     * The list is observable to allow UI components to automatically update when patterns are
     * added, removed, or changed.
     *
     * @return The {@link ObservableList} of file patterns (e.g., "*.txt", "image_*.jpg").
     */
    ObservableList<String> getPatterns();
}
