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
        assertThat(config.getStartDirectoryPath()).isEqualTo("");
        assertThat(ActionPolicy.ASK).isEqualTo(config.getTraverseSubdirectories());
        assertThat(ActionPolicy.ASK).isEqualTo(config.getDeleteOnSuccessPolicy());
        assertThat(ActionPolicy.ASK).isEqualTo(config.getDuplicateFilesPolicy());
        assertThat(config.getSortingRules()).isNotNull();
        assertThat(config.getSortingRules()).isEmpty();
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
        assertThat(config.getTraverseSubdirectories()).isEqualTo(ActionPolicy.YES);
    }

    @Test
    void shouldReturnNonNullTraverseSubdirectoriesProperty() {
        assertThat(config.getTraverseSubdirectoriesProperty()).isNotNull();
        assertThat(config.getTraverseSubdirectoriesProperty().get())
                .isEqualTo(config.getTraverseSubdirectories());
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
        ObservableList<SortingRuleView> rules = config.getSortingRules();
        SortingRuleView ruleView = new SortingRuleView(new SortingRule("/home/user/test"));

        rules.add(ruleView);

        assertThat(config.getSortingRules().size()).isEqualTo(1);
        assertThat(config.getSortingRules().get(0)).isEqualTo(ruleView);
    }
}
