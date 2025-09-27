package ru.antondobrov.filesorter.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.ObservableList;
import ru.antondobrov.filesorter.controllers.IRulesService;
import ru.antondobrov.filesorter.model.IRuleConfig;
import ru.antondobrov.filesorter.utils.RuleFactory;

public class RulesService implements IRulesService {
    private final RuleFactory factory;

    public RulesService(RuleFactory factory) {
        this.factory = factory;
    }

    @Override
    public void deleteRulesInRulesList(ObservableList<IRuleConfig> rules,
            ObservableList<Integer> indices) {
        if (rules == null || indices == null || indices.isEmpty()) {
            return;
        }
        List<Integer> sortedIndices = new ArrayList<>(indices);
        Collections.sort(sortedIndices, Collections.reverseOrder());
        for (Integer index : sortedIndices) {
            rules.remove(index.intValue());
        }
    }

    @Override
    public void addNewRuleInRulesList(ObservableList<IRuleConfig> rules) {
        if (rules == null) {
            return;
        }

        rules.add(factory.create());
    }

}
