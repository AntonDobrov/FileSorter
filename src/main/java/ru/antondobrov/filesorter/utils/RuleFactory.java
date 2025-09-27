package ru.antondobrov.filesorter.utils;

import ru.antondobrov.filesorter.model.IRuleConfig;
import ru.antondobrov.filesorter.model.SortingRule;

public class RuleFactory {
    public IRuleConfig create() {
        return new SortingRule();
    }
}
