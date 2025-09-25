package ru.antondobrov.filesorter.services;

import javafx.collections.ObservableList;

public interface IRuleService {
    void addPatternsToPatternsList(ObservableList<String> patternsList, Integer newPatternsCount);

    void deletePatternsFromPatternsList(ObservableList<String> patternsList,
            ObservableList<Integer> indices);

}
