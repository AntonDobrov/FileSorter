package ru.antondobrov.filesorter.utils;

import javafx.util.StringConverter;
import ru.antondobrov.filesorter.model.ActionPolicy;

/**
 * A {@link StringConverter} for the {@link ActionPolicy} enum.
 * <p>
 * This class is designed for use in JavaFX UI controls like {@code ComboBox} or {@code ChoiceBox}.
 * It converts an {@code ActionPolicy} enum constant into a human-readable, localized string for
 * display in the user interface, using a provided {@link ILocalizer} service.
 */
public class ActionPolicyStringConverter extends StringConverter<ActionPolicy> {

    private final ILocalizer localizer;

    /**
     * Constructs a new converter with the specified localization service.
     *
     * @param localizer The localization service used to retrieve the display names for the
     *        policies.
     */
    public ActionPolicyStringConverter(ILocalizer localizer) {
        this.localizer = localizer;
    }

    /**
     * Converts an {@link ActionPolicy} object into its localized, user-friendly string
     * representation.
     * <p>
     * This method is called by JavaFX controls to get the text to display for each item.
     *
     * @param object The {@link ActionPolicy} enum constant to convert. Can be {@code null}.
     * @return The localized display name for the given policy, or an empty string if the object is
     *         {@code null}.
     */
    @Override
    public String toString(ActionPolicy object) {
        if (object == null) {
            return "";
        }
        return object.getDisplayName(localizer);
    }

    /**
     * Converts a string back into an {@link ActionPolicy}.
     * <p>
     * This implementation is one-way and not intended for parsing user input. It will always return
     * a default, non-functional {@code ActionPolicy.EMPTY} value, as this converter's primary
     * purpose is for display only in non-editable controls.
     *
     * @param string The string to convert (ignored).
     * @return Always returns {@link ActionPolicy#EMPTY}.
     */
    @Override
    public ActionPolicy fromString(String string) {
        // This converter is for display purposes only.
        return ActionPolicy.EMPTY;
    }
}
