package ru.antondobrov.filesorter.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.ObservableList;
import ru.antondobrov.filesorter.controllers.IRuleService;

public class RuleService implements IRuleService {

    private final PatternFactory factory;

    public RuleService(PatternFactory factory) {
        this.factory = factory;
    }

    @Override
    public void addPatternsToPatternsList(ObservableList<String> patternsList) {
        if (patternsList == null) {
            return;
        }
        patternsList.add(factory.create());

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
