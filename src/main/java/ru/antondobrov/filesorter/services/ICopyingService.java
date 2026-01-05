package ru.antondobrov.filesorter.services;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import ru.antondobrov.filesorter.model.IRuleConfig;

public interface ICopyingService {
    Map<Path, List<FileInfo>> copy(List<Path> filesList, List<IRuleConfig> copyRules);
}
