package ru.antondobrov.filesorter.services;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ru.antondobrov.filesorter.controllers.IConfig;
import ru.antondobrov.filesorter.controllers.ISorterConfig;
import ru.antondobrov.filesorter.controllers.ISortingService;
import ru.antondobrov.filesorter.model.ActionPolicy;

public class SortingService implements ISorterConfig, ISortingService {
    IFileScanningService scanningService;
    ICopyingService copyingService;
    IDeletingService deletingService;

    private IConfig config;

    private DoubleProperty sortingProgress;

    public SortingService(IConfig config, IFileScanningService scanningService,
            ICopyingService copyingService, IDeletingService deletingService) {
        this.config = config;
        sortingProgress = new SimpleDoubleProperty(-1);

        this.scanningService = scanningService;
        this.copyingService = copyingService;
        this.deletingService = deletingService;
    }

    @Override
    public DoubleProperty getSortingProgressProperty() {
        return sortingProgress;
    }

    @Override
    public void startSorting() {
        List<Path> filesFromStartDirectory =
                scanningService.scanFiles(config.getStartDirectoryPathProperty().get(),
                        config.getTraverseSubdirectoriesPolicyProperty().get());

        Map<Path, List<FileInfo>> sortingReport =
                copyingService.copy(filesFromStartDirectory, config.getRulesList());


        if (config.getDeleteOnSuccessPolicyProperty().get() == ActionPolicy.YES) {
            deletingService.delete(sortingReport);
        }

        // TODO: add sortingReport
    }


}
