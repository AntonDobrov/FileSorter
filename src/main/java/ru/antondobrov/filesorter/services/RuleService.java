package ru.antondobrov.filesorter.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.ObservableList;
import ru.antondobrov.filesorter.controllers.IRuleService;
import ru.antondobrov.filesorter.utils.PatternFactory;

/**
 * A concrete implementation of the {@link IRuleService} interface that manages the list of patterns
 * within a single sorting rule.
 * <p>
 * This service encapsulates the business logic for adding and deleting file patterns (e.g.,
 * "*.jpg", "*.txt") associated with a rule. It relies on a {@link PatternFactory} to generate new
 * default patterns. Like its counterpart {@link RulesService}, it employs a robust method for
 * safely deleting multiple items from a list by their indices.
 */
public class RuleService implements IRuleService {

    private final PatternFactory factory;

    /**
     * Constructs a new RuleService with its required factory dependency.
     *
     * @param factory The factory used to create new default pattern strings.
     */
    public RuleService(PatternFactory factory) {
        this.factory = factory;
    }

    /**
     * Adds a new, default pattern to the given list of patterns.
     * <p>
     * It uses the injected {@link PatternFactory} to create a new pattern string (e.g., an empty or
     * placeholder string) and adds it to the list. This action, on an {@link ObservableList}, will
     * automatically trigger UI updates.
     *
     * @param patternsList The observable list of patterns to which the new entry will be added.
     */
    @Override
    public void addPatternsToPatternsList(ObservableList<String> patternsList) {
        if (patternsList == null) {
            return;
        }
        patternsList.add(factory.create());
    }

    /**
     * Deletes one or more patterns from the given list based on their indices.
     * <p>
     * This implementation ensures safe removal of multiple items by first sorting the indices in
     * descending order. This critical step prevents {@code IndexOutOfBoundsException} that would
     * arise from shifting indices if items were removed in ascending order. The method is robust
     * and handles null inputs gracefully.
     *
     * @param patternsList The observable list of patterns from which items will be removed.
     * @param indices A list of integer indices for the patterns to be deleted. The original order
     *        of indices does not matter.
     */
    @Override
    public void deletePatternsFromPatternsList(ObservableList<String> patternsList,
            ObservableList<Integer> indices) {
        if (patternsList == null || indices == null) {
            return;
        }
        // Create a mutable copy and sort in reverse to avoid index shifting issues.
        List<Integer> sortedIndices = new ArrayList<>(indices);
        Collections.sort(sortedIndices, Collections.reverseOrder());

        for (Integer index : sortedIndices) {
            patternsList.remove(index.intValue());
        }
    }
}
