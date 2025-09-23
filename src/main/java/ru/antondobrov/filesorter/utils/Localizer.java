package ru.antondobrov.filesorter.utils;

import java.util.ResourceBundle;

public class Localizer implements ILocalizer {

    private ResourceBundle bundle;

    Localizer(ResourceBundle bundle) {
        setBundle(bundle);
    }

    public String get(String key) {
        if (bundle == null) {
            return key;
        } else {
            return bundle.containsKey(key) ? bundle.getString(key) : key;
        }
    }

    public void setBundle(ResourceBundle sourceBundle) {
        bundle = sourceBundle;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }
}
