package ru.antondobrov.filesorter.controllers;

import javafx.collections.ObservableList;

public interface IRuleService {
    public void addPatternsToPatternsList(ObservableList<String> patternsList);

    public void deletePatternsFromPatternsList(ObservableList<String> patternsList,
            ObservableList<Integer> indices);

}
