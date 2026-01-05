package ru.antondobrov.filesorter.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ru.antondobrov.filesorter.model.IRuleConfig;

public class CopyingService implements ICopyingService {

    private boolean needReplacing;

    private record FileCopyTask(Path destinationPath, Path filePath) {
    }

    public CopyingService(boolean needReplacing) {
        this.needReplacing = needReplacing;
    }

    @Override
    public Map<Path, List<FileInfo>> copy(List<Path> filesList, List<IRuleConfig> copyRules) {
        if (filesList == null) {
            throw new IllegalArgumentException("File list cannot be null");
            // TODO: add logger
        }
        if (copyRules == null) {
            throw new IllegalArgumentException("Rules list cannot be null");
            // TODO: add logger
        }

        if (filesList.isEmpty() || copyRules.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<IRuleConfig, List<Pattern>> compiledRules = preparePatterns(copyRules);

        Map<Path, List<FileInfo>> copySettings = createCopySettings(filesList, compiledRules);

        if (copySettings.isEmpty()) {
            return copySettings;
        }

        copyFiles(copySettings);

        return copySettings;

    }

    private FileInfo createFileInfo(Path file) {
        try {
            return new FileInfo(file, Files.size(file), FileState.PENDING);
            // TODO: add logger
        } catch (IOException e) {
            return new FileInfo(file, 0, FileState.COPY_PLANNING_FAILED);
            // TODO: add logger
        }
    }

    private Map<Path, List<FileInfo>> createCopySettings(List<Path> filesList,
            Map<IRuleConfig, List<Pattern>> copyRules) {

        return filesList.stream().flatMap(file -> findMatchingRules(file, copyRules))
                .collect(Collectors.groupingBy(FileCopyTask::destinationPath, Collectors
                        .mapping(task -> createFileInfo(task.filePath()), Collectors.toList())));
    }

    private Stream<FileCopyTask> findMatchingRules(Path file,
            Map<IRuleConfig, List<Pattern>> rules) {
        String fileName = file.getFileName().toString();
        return rules.entrySet().stream()
                .filter(entry -> matchesPatterns(fileName, entry.getValue())).flatMap(entry -> {
                    IRuleConfig rule = entry.getKey();
                    String destProp = rule.getDestinationPathProperty().get();
                    if (!destProp.isEmpty()) {
                        return Stream.of(new FileCopyTask(Paths.get(destProp), file));
                    }
                    return Stream.empty();
                });
    }

    private boolean matchesPatterns(String fileName, List<Pattern> patterns) {
        return patterns.stream().anyMatch(p -> p.matcher(fileName).matches());
    }

    private void copyFiles(Map<Path, List<FileInfo>> settings) {
        for (Map.Entry<Path, List<FileInfo>> entry : settings.entrySet()) {
            Path targetDirectory = entry.getKey();

            List<FileInfo> files = entry.getValue();
            if (files == null || files.isEmpty()) {
                continue;
            }

            for (FileInfo fileInfo : files) {
                if (fileInfo.getState() != FileState.PENDING) {
                    continue;
                }

                fileInfo.setState(FileState.COPYING);

                Path sourcePath = fileInfo.getFile();
                Path targetPath = targetDirectory.resolve(sourcePath.getFileName());

                if (needReplacing) {
                    try {
                        Files.copy(sourcePath, targetPath, StandardCopyOption.COPY_ATTRIBUTES,
                                StandardCopyOption.REPLACE_EXISTING);
                        fileInfo.setState(FileState.REPLACE_COMPLETED);
                    } catch (IOException e) {
                        fileInfo.setState(FileState.REPLACE_FAILED);
                        // TODO: add logger
                    }
                } else {
                    if (Files.exists(targetPath)) {
                        targetPath = getUniqueDestinationPath(targetPath);
                    }
                    try {
                        Files.copy(sourcePath, targetPath, StandardCopyOption.COPY_ATTRIBUTES);

                        fileInfo.setState(FileState.COPYING_COMPLETED);
                    } catch (IOException e) {
                        fileInfo.setState(FileState.COPYING_FAILED);
                        // TODO: add logger
                    }
                }
            }
        }
    }

    private Path getUniqueDestinationPath(Path originalTarget) {
        Path parent = originalTarget.getParent();
        String fileName = originalTarget.getFileName().toString();

        String name;
        String extension;
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            name = fileName.substring(0, dotIndex);
            extension = fileName.substring(dotIndex);
        } else {
            name = fileName;
            extension = "";
        }

        int counter = 1;
        Path newPath = originalTarget;

        while (Files.exists(newPath)) {
            String newName = name + " (" + counter + ")" + extension;
            newPath = parent.resolve(newName);
            counter++;
        }

        return newPath;
    }

    private Map<IRuleConfig, List<Pattern>> preparePatterns(List<IRuleConfig> rules) {
        return rules.stream().collect(Collectors.toMap(rule -> rule,
                rule -> rule.getPatterns().stream().map(patternString -> {
                    try {
                        return Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
                    } catch (PatternSyntaxException e) {
                        // TODO: add logger
                        return null;
                    }
                }).filter(Objects::nonNull).toList()));
    }
}
