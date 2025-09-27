package ru.antondobrov.filesorter.controllers;

import javafx.collections.ObservableList;
import ru.antondobrov.filesorter.model.IRuleConfig;

public interface IRulesConfig {

    public ObservableList<IRuleConfig> getRulesList();

}
