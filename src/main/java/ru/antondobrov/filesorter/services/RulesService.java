package ru.antondobrov.filesorter.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.ObservableList;
import ru.antondobrov.filesorter.controllers.IRulesService;
import ru.antondobrov.filesorter.model.IRuleConfig;
import ru.antondobrov.filesorter.utils.RuleFactory;

/**
 * A concrete implementation of the {@link IRulesService} interface responsible for managing the
 * collection of sorting rules.
 * <p>
 * This service centralizes the business logic for list-wide operations, such as adding a new
 * default rule or deleting multiple selected rules. It utilizes a {@link RuleFactory} to decouple
 * itself from the concrete instantiation of rule objects. A key feature of this implementation its
 * robust deletion logic, which correctly handles the removal of multiple items from a list by their
 * indices.
 */
public class RulesService implements IRulesService {
    private final RuleFactory factory;

    /**
     * Constructs a new RulesService with its required factory dependency.
     *
     * @param factory The factory used to create new instances of {@link IRuleConfig}.
     */
    public RulesService(RuleFactory factory) {
        this.factory = factory;
    }

    /**
     * Deletes one or more rules from the provided list based on their indices.
     * <p>
     * This implementation ensures safe removal by first sorting the provided indices in descending
     * order. This prevents {@code IndexOutOfBoundsException} that would occur if items were removed
     * in ascending order, as removing an item shifts the indices of all subsequent items. The
     * method gracefully handles null or empty input lists.
     *
     * @param rules The master observable list of rules from which to remove items.
     * @param indices A list of integer indices specifying the rules to be deleted. The order of
     *        indices in this list does not matter.
     */
    @Override
    public void deleteRulesInRulesList(ObservableList<IRuleConfig> rules,
            ObservableList<Integer> indices) {
        if (rules == null || indices == null || indices.isEmpty()) {
            return;
        }
        // Create a mutable copy and sort in reverse to avoid index shifting issues.
        List<Integer> sortedIndices = new ArrayList<>(indices);
        Collections.sort(sortedIndices, Collections.reverseOrder());

        for (Integer index : sortedIndices) {
            rules.remove(index.intValue());
        }
    }

    /**
     * Creates a new default sorting rule and adds it to the end of the list.
     * <p>
     * This method delegates the instantiation of the new rule to the injected {@link RuleFactory}.
     * The newly created rule is then appended to the provided {@code ObservableList}, which will
     * trigger an automatic update in any bound UI components.
     *
     * @param rules The master observable list of rules to which the new rule will be added.
     */
    @Override
    public void addNewRuleInRulesList(ObservableList<IRuleConfig> rules) {
        if (rules == null) {
            return;
        }
        rules.add(factory.create());
    }
}
