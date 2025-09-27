package ru.antondobrov.filesorter.model;

import java.util.ResourceBundle;
import ru.antondobrov.filesorter.utils.ILocalizer;

/**
 * Defines the execution policy for a sorting action.
 * <p>
 * Each policy is associated with a key in a resource bundle to retrieve its localized display name.
 */
public enum ActionPolicy {
    /**
     * Always execute the action.
     */
    YES("action.policy.yes"),

    /**
     * Never execute the action.
     */
    NO("action.policy.no"),

    /**
     * Always ask the user before executing the action.
     */
    ASK("action.policy.ask"),

    /**
     * Indicates that no policy has been selected or an error has occurred.
     */
    EMPTY("action.policy.empty");

    /**
     * The key for looking up the localized description in a {@link ResourceBundle}.
     */
    private final String description;

    ActionPolicy(String description) {
        this.description = description;
    }

    /**
     * Returns the resource key for this policy.
     * <p>
     * This key can be used to retrieve the localized name via
     * {@link #getDisplayName(ResourceBundle)}.
     *
     * @return The resource key as a string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a localized, human-readable name for this policy.
     * <p>
     * 
     * @param localizer The {@link ILocalizer} service to use for the translation.
     * @return The localized policy name, or the key itself if a translation is not found.
     */
    public String getDisplayName(ILocalizer localizer) {
        if (localizer == null) {
            return getDescription();
        }
        return localizer.get(getDescription());
    }

    @Override
    public String toString() {
        return "ActionPolicy:" + getDescription() + ";";
    }
}
