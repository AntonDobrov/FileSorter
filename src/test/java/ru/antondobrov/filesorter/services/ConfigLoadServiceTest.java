package ru.antondobrov.filesorter.services;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.antondobrov.filesorter.model.ActionPolicy;
import ru.antondobrov.filesorter.model.SorterConfig;
import ru.antondobrov.filesorter.model.SortingRule;

class ConfigLoadServiceTest {

    @TempDir
    Path tempDir;

    private SorterConfig config;
    private ConfigLoadService service;

    @BeforeEach
    void setUp() {
        config = new SorterConfig();
        service = new ConfigLoadService(config);
    }

    @Test
    void shouldReturnTrueWhenFileIsWrittenSuccessfully() {
        File configFile = tempDir.resolve("config.json").toFile();
        boolean result = service.saveConfig(configFile);
        assertThat(result).isTrue();
    }

    @Test
    void shouldCreateFileWithCorrectContentWhenConfigHasData() throws IOException {
        config.setStartDirectoryPath("/home/testuser/Documents");
        config.setDeleteOnSuccessPolicy(ActionPolicy.YES);
        SortingRule rule = new SortingRule();
        rule.setDestinationPath("/var/data/images");
        rule.getPatterns().add("*.jpg");
        config.getRulesList().add(rule);

        File configFile = tempDir.resolve("config.json").toFile();

        service.saveConfig(configFile);

        assertThat(configFile).exists();
        String fileContent = Files.readString(configFile.toPath());
        assertThat(fileContent).contains("\"startDirectoryPath\" : \"/home/testuser/Documents\"")
                .contains("\"deleteOnSuccessPolicy\" : \"YES\"")
                .contains("\"destinationPath\" : \"/var/data/images\"")
                .contains("\"patterns\" : [ \"*.jpg\" ]");
    }

    @Test
    void shouldReturnTrueWhenFileIsValidAndIsReadSuccessfully() throws IOException {
        String jsonContent = "{\"startDirectoryPath\":\"/tmp/loaded_config\"}";
        File configFile = tempDir.resolve("valid_config.json").toFile();
        Files.write(configFile.toPath(), Collections.singleton(jsonContent));

        boolean result = service.openConfig(configFile);

        assertThat(result).isTrue();
    }

    @Test
    void shouldUpdateExistingConfigObjectWhenFileIsLoaded() throws IOException {
        assertThat(config.getStartDirectoryPath()).isEmpty();

        String jsonContent = "{\n" + "  \"startDirectoryPath\" : \"/home/user/from_file\",\n"
                + "  \"duplicateFilesPolicy\" : \"NO\"\n" + "}";
        File configFile = tempDir.resolve("update_config.json").toFile();
        Files.write(configFile.toPath(), Collections.singleton(jsonContent));

        service.openConfig(configFile);

        assertThat(config.getStartDirectoryPath()).isEqualTo("/home/user/from_file");
        assertThat(config.getDuplicateFilesPolicy()).isEqualTo(ActionPolicy.NO);
    }

    @Test
    void shouldReturnFalseWhenFileDoesNotExist() {
        File nonExistentFile = tempDir.resolve("non_existent.json").toFile();

        boolean result = service.openConfig(nonExistentFile);

        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseWhenFileContainsInvalidJson() throws IOException {
        String invalidJsonContent = "{\"startDirectoryPath\": \"/some/path\"";
        File configFile = tempDir.resolve("invalid_config.json").toFile();
        Files.write(configFile.toPath(), Collections.singleton(invalidJsonContent));

        boolean result = service.openConfig(configFile);

        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseWhenFileIsEmpty() throws IOException {
        File emptyFile = tempDir.resolve("empty.json").toFile();
        emptyFile.createNewFile();

        boolean result = service.openConfig(emptyFile);

        assertThat(result).isFalse();
    }
}
