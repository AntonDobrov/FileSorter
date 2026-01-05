package ru.antondobrov.filesorter.services;

import ru.antondobrov.filesorter.utils.ILocalizer;

public enum FileState {
    PENDING("file.state.pending"), COPYING("file.state.copying"), COPYING_COMPLETED(
            "file.state.completed"), DELETED("file.state.deleted"), COPY_PLANNING_FAILED(
                    "file.state.copy.planning.failed"), COPYING_FAILED(
                            "file.state.copying.failed"), REPLACE_COMPLETED(
                                    "file.state.replace.completed"), REPLACE_FAILED(
                                            "file.state.replace.failed");

    private final String description;

    FileState(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public String getDisplayName(ILocalizer localizer) {
        if (localizer == null) {
            return getDescription();
        }
        return localizer.get(getDescription());
    }

    @Override
    public String toString() {
        return "FileState:" + getDescription() + ";";
    }
}
