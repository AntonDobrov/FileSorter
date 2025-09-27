package ru.antondobrov.filesorter.controllers;

import javafx.collections.ObservableList;

/**
 * Defines a contract for a service that encapsulates the business logic for modifying a single
 * sorting rule.
 * <p>
 * This interface abstracts the operations related to managing the list of file patterns within an
 * individual rule. By centralizing this logic in a service, controllers like {@link RuleController}
 * can remain simple and delegate the details of data manipulation, promoting better separation of
 * concerns and easier testing.
 */
public interface IRuleService {

    /**
     * Adds a new, default pattern to the given list of patterns.
     * <p>
     * Implementations of this method should add a new entry (e.g., an empty string or a placeholder
     * like "new_pattern") to the {@code patternsList}, which will then become visible and editable
     * in the UI thanks to the nature of {@link ObservableList}.
     *
     * @param patternsList The observable list of patterns to which the new entry will be added.
     */
    void addPatternsToPatternsList(ObservableList<String> patternsList);

    /**
     * Deletes one or more patterns from the given list based on their indices.
     * <p>
     * This method is designed to remove items that have been selected by the user in a UI component
     * like a {@code ListView}. The implementation should handle the removal of elements at the
     * specified indices from the {@code patternsList}.
     *
     * @param patternsList The observable list of patterns from which items will be removed.
     * @param indices An observable list of integer indices indicating which patterns to delete.
     */
    void deletePatternsFromPatternsList(ObservableList<String> patternsList,
            ObservableList<Integer> indices);
}
