package ru.antondobrov.filesorter.services;

import java.nio.file.Path;
import java.util.List;
import ru.antondobrov.filesorter.model.ActionPolicy;

public interface IFileScanningService {

    List<Path> scanFiles(String startDirectory, ActionPolicy traversingPolicy);
}
