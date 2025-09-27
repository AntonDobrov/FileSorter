package ru.antondobrov.filesorter.controllers;

import javafx.collections.ObservableList;
import ru.antondobrov.filesorter.model.IRuleConfig;

public interface IRulesService {

    public void deleteRulesInRulesList(ObservableList<IRuleConfig> rules,
            ObservableList<Integer> indices);

    public void addNewRuleInRulesList(ObservableList<IRuleConfig> rules);

}
