package ru.antondobrov.filesorter.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Represents a View Model for a single {@link SortingRule} object.
 * <p>
 * This wrapper class uses an {@link ObjectProperty} to hold a {@code SortingRule} instance. Its
 * primary purpose is to enable JavaFX components, such as a {@code TableView}, to observe when the
 * entire {@code SortingRule} object is replaced, not just changes to its internal fields.
 */
public class SortingRuleView {
    private final ObjectProperty<SortingRule> rule;

    /**
     * Constructs a new {@code SortingRuleView} that wraps the specified {@code SortingRule}.
     *
     * @param rule The initial {@link SortingRule} to wrap. It should not be null.
     */
    public SortingRuleView(SortingRule rule) {
        this.rule = new SimpleObjectProperty<>(rule);
    }

    /**
     * Returns the wrapped {@link SortingRule} object.
     *
     * @return The current {@link SortingRule}.
     */
    public SortingRule getRule() {
        return rule.get();
    }

    /**
     * Sets the {@link SortingRule} to be held by this property.
     *
     * @param rule The {@link SortingRule} to set.
     */
    public void setRule(SortingRule rule) {
        this.rule.set(rule);
    }

    /**
     * Returns the {@link ObjectProperty} that wraps the {@link SortingRule}.
     * <p>
     * This is the primary method used for data binding with JavaFX components. It allows observers
     * to be notified when the entire {@code SortingRule} object is replaced.
     *
     * @return The property that wraps the {@link SortingRule}.
     */
    public ObjectProperty<SortingRule> getRuleProperty() {
        return rule;
    }

    @Override
    public String toString() {
        return "SortingRuleView{" + getRule().toString().replace("\n", "\n\t") + "\n}";
    }
}
