package ru.antondobrov.filesorter.model;

import static org.assertj.core.api.Assertions.assertThat;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SorterConfigTest {

    private SorterConfig config;

    @BeforeEach
    void setUp() {
        config = new SorterConfig();
    }

    @Test
    void shouldInitializeWithDefaultValues() {
        assertThat(config.getStartDirectoryPath()).isEmpty();
        assertThat(config.getTraverseSubdirectoriesPolicy()).isEqualTo(ActionPolicy.ASK);
        assertThat(config.getDeleteOnSuccessPolicy()).isEqualTo(ActionPolicy.ASK);
        assertThat(config.getDuplicateFilesPolicy()).isEqualTo(ActionPolicy.ASK);
        assertThat(config.getRulesList()).isNotNull();
        assertThat(config.getRulesList()).isEmpty();
    }

    @Test
    void shouldSetAndGetStartDirectoryPath() {
        String testPath = "/home/user/test";
        config.setStartDirectoryPath(testPath);
        assertThat(config.getStartDirectoryPath()).isEqualTo(testPath);
    }

    @Test
    void shouldReturnNonNullStartDirectoryPathProperty() {
        assertThat(config.getStartDirectoryPathProperty()).isNotNull();
        assertThat(config.getStartDirectoryPathProperty().get())
                .isEqualTo(config.getStartDirectoryPath());
    }

    @Test
    void shouldSetAndGetTraverseSubdirectories() {
        config.setTraverseSubdirectories(ActionPolicy.YES);
        assertThat(config.getTraverseSubdirectoriesPolicy()).isEqualTo(ActionPolicy.YES);
    }

    @Test
    void shouldReturnNonNullTraverseSubdirectoriesProperty() {
        assertThat(config.getTraverseSubdirectoriesPolicyProperty()).isNotNull();
        assertThat(config.getTraverseSubdirectoriesPolicyProperty().get())
                .isEqualTo(config.getTraverseSubdirectoriesPolicy());
    }

    @Test
    void shouldSetAndGetDeleteOnSuccessPolicy() {
        config.setDeleteOnSuccessPolicy(ActionPolicy.YES);
        assertThat(config.getDeleteOnSuccessPolicy()).isEqualTo(ActionPolicy.YES);
    }

    @Test
    void shouldReturnNonNullDeleteOnSuccessPolicyProperty() {
        assertThat(config.getDeleteOnSuccessPolicyProperty()).isNotNull();
        assertThat(config.getDeleteOnSuccessPolicyProperty().get())
                .isEqualTo(config.getDeleteOnSuccessPolicy());
    }

    @Test
    void shouldSetAndGetDuplicateFilesPolicy() {
        config.setDuplicateFilesPolicy(ActionPolicy.YES);
        assertThat(config.getDuplicateFilesPolicy()).isEqualTo(ActionPolicy.YES);
    }

    @Test
    void shouldReturnNonNullDuplicateFilesPolicyProperty() {
        assertThat(config.getDuplicateFilesPolicyProperty()).isNotNull();
        assertThat(config.getDuplicateFilesPolicyProperty().get())
                .isEqualTo(config.getDuplicateFilesPolicy());
    }

    @Test
    void shouldAllowAddingAndRetrievingSortingRules() {
        ObservableList<IRuleConfig> rules = config.getRulesList();
        IRuleConfig rule = new SortingRule();
        rule.getDestinationPathProperty().set("/home/user/test");

        rules.add(rule);

        assertThat(config.getRulesList()).hasSize(1);
        assertThat(config.getRulesList().get(0)).isEqualTo(rule);
    }
}
