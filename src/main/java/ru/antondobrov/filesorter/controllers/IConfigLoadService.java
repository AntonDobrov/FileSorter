package ru.antondobrov.filesorter.controllers;

import java.io.File;

public interface IConfigLoadService {
    public boolean saveConfig(File configFile);

    public boolean openConfig(File configFile);
}
