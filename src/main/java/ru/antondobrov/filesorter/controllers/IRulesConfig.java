package ru.antondobrov.filesorter.controllers;

import javafx.collections.ObservableList;
import ru.antondobrov.filesorter.model.IRuleConfig;

/**
 * Defines a contract for a configuration model that provides access to the list of sorting rules.
 * <p>
 * This interface decouples UI controllers, particularly those responsible for displaying and
 * managing the rules list (e.g., in a {@code ListView}), from the concrete implementation of the
 * main application configuration. This allows the controller to focus solely on its responsibility
 * of handling a list of {@link IRuleConfig} items.
 */
public interface IRulesConfig {

    /**
     * Returns the observable list of sorting rules.
     * <p>
     *
     * @return The {@link ObservableList} containing the {@link IRuleConfig} instances.
     */
    ObservableList<IRuleConfig> getRulesList();
}
