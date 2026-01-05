package ru.antondobrov.filesorter.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import ru.antondobrov.filesorter.model.ActionPolicy;

public class FileScanningService implements IFileScanningService {

    @Override
    public List<Path> scanFiles(String startDirectoryPath, ActionPolicy traversingPolicy) {
        if (traversingPolicy != ActionPolicy.NO || traversingPolicy != ActionPolicy.YES
                || traversingPolicy == null) {
            // TODO: add exception
        }
        if (startDirectoryPath.isBlank()) {
            // TODO: add exception
        }

        Path startDirectory = Paths.get(startDirectoryPath);

        if (Files.notExists(startDirectory)) {
            // TODO: add exception
        }
        if (!Files.isDirectory(startDirectory)) {
            // TODO: add exception
        }
        if (!Files.isReadable(startDirectory)) {
            // TODO: add exception
        }

        List<Path> scanningFiles = new ArrayList<>();

        int maxDepth = (traversingPolicy == ActionPolicy.YES) ? Integer.MAX_VALUE : 1;
        try (Stream<Path> filesStream = Files.walk(startDirectory, maxDepth)) {
            scanningFiles.addAll(filesStream.filter(p -> !p.equals(startDirectory))
                    .filter(Files::isRegularFile).toList());
        } catch (IOException e) {
            // TODO: add exception
        }

        return scanningFiles;
    }


}
