package ru.antondobrov.filesorter.services;

import java.nio.file.Path;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileInfo {
    private final Path file;
    private final long fileSize;
    private FileState state;
}
