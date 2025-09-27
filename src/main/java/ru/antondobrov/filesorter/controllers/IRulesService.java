package ru.antondobrov.filesorter.controllers;

import javafx.collections.ObservableList;
import ru.antondobrov.filesorter.model.IRuleConfig;

/**
 * Defines a contract for a service that manages the collection of sorting rules.
 * <p>
 * This interface handles high-level, list-wide operations such as creating new rules or deleting
 * existing ones. It complements {@link IRuleService}, which handles modifications *within* a single
 * rule.
 */
public interface IRulesService {

    /**
     * Deletes one or more rules from the master list based on their indices.
     * <p>
     * This method is intended to be called in response to a user action, such as clicking a
     * 'Delete' button after selecting items in a {@code ListView}. The implementation should
     * efficiently remove the rules at the specified indices from the source list.
     *
     * @param rules The master observable list of rules from which items will be removed.
     * @param indices An observable list of integer indices corresponding to the rules that should
     *        be deleted.
     */
    void deleteRulesInRulesList(ObservableList<IRuleConfig> rules, ObservableList<Integer> indices);

    /**
     * Creates and adds a new, default sorting rule to the list.
     * <p>
     * When this method is called, a new instance of a concrete {@link IRuleConfig} implementation
     * with default or empty values should be created and appended to the provided list.
     *
     * @param rules The master observable list of rules to which the new rule will be added.
     */
    void addNewRuleInRulesList(ObservableList<IRuleConfig> rules);
}
