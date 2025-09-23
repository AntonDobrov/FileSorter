package ru.antondobrov.filesorter.services;

import java.io.File;

public interface IConfigLoadService {
    boolean saveConfig(File configFile);

    boolean openConfig(File configFile);
}
