package ru.antondobrov.filesorter.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.ObservableList;

public class RuleService implements IRuleService {

    @Override
    public void addPatternsToPatternsList(ObservableList<String> patternsList,
            Integer newPatternsCount) {
        if (patternsList == null || newPatternsCount == null) {
            return;
        }
        for (int i = newPatternsCount.intValue(); i > 0; i--) {
            patternsList.add("");
        }
    }

    @Override
    public void deletePatternsFromPatternsList(ObservableList<String> patternsList,
            ObservableList<Integer> indices) {
        if (patternsList == null || indices == null) {
            return;
        }
        List<Integer> sortedIndices = new ArrayList<>(indices);
        Collections.sort(sortedIndices, Collections.reverseOrder());
        for (Integer index : sortedIndices) {
            patternsList.remove(index.intValue());
        }
    }
}
