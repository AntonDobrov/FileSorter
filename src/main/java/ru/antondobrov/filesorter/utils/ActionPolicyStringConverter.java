package ru.antondobrov.filesorter.utils;

import javafx.util.StringConverter;
import ru.antondobrov.filesorter.model.ActionPolicy;

public class ActionPolicyStringConverter extends StringConverter<ActionPolicy> {

    private ILocalizer localizer;

    public ActionPolicyStringConverter(ILocalizer localizer) {
        this.localizer = localizer;
    }

    @Override
    public String toString(ActionPolicy object) {
        if (object == null) {
            return "";
        }
        return object.getDisplayName(localizer);
    }

    @Override
    public ActionPolicy fromString(String string) {
        return ActionPolicy.EMPTY;
    }

}
