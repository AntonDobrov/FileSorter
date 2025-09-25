package ru.antondobrov.filesorter.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.lang.reflect.Field;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationExtension;
import ru.antondobrov.filesorter.model.ActionPolicy;
import ru.antondobrov.filesorter.services.IAdditionalSettingsConfig;

@ExtendWith(ApplicationExtension.class)
@ExtendWith(MockitoExtension.class)
class AdditionalSettingsPanelControllerTest {

    @Mock
    IAdditionalSettingsConfig config;

    @Mock
    StringConverter<ActionPolicy> converter;

    ObjectProperty<ActionPolicy> traverseProperty;
    ObjectProperty<ActionPolicy> deletionProperty;
    ObjectProperty<ActionPolicy> duplicateProperty;

    ChoiceBox<ActionPolicy> traverseBox;
    ChoiceBox<ActionPolicy> deletionBox;
    ChoiceBox<ActionPolicy> duplicateBox;

    AdditionalSettingsPanelController controller;

    @BeforeEach
    void setUp() throws Exception {
        traverseProperty = new SimpleObjectProperty<>();
        traverseProperty.set(ActionPolicy.NO);
        deletionProperty = new SimpleObjectProperty<>();
        deletionProperty.set(ActionPolicy.NO);
        duplicateProperty = new SimpleObjectProperty<>();
        duplicateProperty.set(ActionPolicy.NO);

        when(config.getTraverseSubdirectoriesPolicyProperty()).thenReturn(traverseProperty);
        when(config.getDeleteOnSuccessPolicyProperty()).thenReturn(deletionProperty);
        when(config.getDuplicateFilesPolicyProperty()).thenReturn(duplicateProperty);

        controller = new AdditionalSettingsPanelController(config, converter);

        traverseBox = new ChoiceBox<>();
        deletionBox = new ChoiceBox<>();
        duplicateBox = new ChoiceBox<>();

        Field traverseBoxFromController = AdditionalSettingsPanelController.class
                .getDeclaredField("traverseSubdirectoriesChoiceBox");
        traverseBoxFromController.setAccessible(true);
        traverseBoxFromController.set(controller, traverseBox);

        Field deletionBoxFromController = AdditionalSettingsPanelController.class
                .getDeclaredField("deleteOnSuccessChoiceBox");
        deletionBoxFromController.setAccessible(true);
        deletionBoxFromController.set(controller, deletionBox);

        Field duplicateBoxFromController = AdditionalSettingsPanelController.class
                .getDeclaredField("copyInDestinationExistChoiceBox");
        duplicateBoxFromController.setAccessible(true);
        duplicateBoxFromController.set(controller, duplicateBox);

        controller.initialize();
    }

    @Test
    void shouldChangeTraversePolicyInConfigWhenPolicyChangeInBox() {
        ActionPolicy expectedPolicy = ActionPolicy.YES;

        traverseBox.setValue(expectedPolicy);

        assertThat(config.getTraverseSubdirectoriesPolicyProperty().get())
                .isEqualTo(expectedPolicy);
    }

    @Test
    void shouldChangeDeletionPolicyInConfigWhenPolicyChangeInBox() {
        ActionPolicy expectedPolicy = ActionPolicy.YES;

        deletionBox.setValue(expectedPolicy);

        assertThat(config.getDeleteOnSuccessPolicyProperty().get()).isEqualTo(expectedPolicy);
    }

    @Test
    void shouldChangeDuplicatePolicyInConfigWhenPolicyChangeInBox() {
        ActionPolicy expectedPolicy = ActionPolicy.YES;
        duplicateBox.setValue(expectedPolicy);

        assertThat(config.getDuplicateFilesPolicyProperty().get()).isEqualTo(expectedPolicy);
    }

    @Test
    void shouldChangeTraversePolicyInBoxWhenPolicyChangeInConfig() {
        ActionPolicy expectedPolicy = ActionPolicy.YES;
        config.getTraverseSubdirectoriesPolicyProperty().set(expectedPolicy);
        assertThat(traverseBox.getValue()).isEqualTo(expectedPolicy);
    }

    @Test
    void shouldChangeDeletionPolicyInBoxWhenPolicyChangeInConfig() {
        ActionPolicy expectedPolicy = ActionPolicy.YES;
        config.getDeleteOnSuccessPolicyProperty().set(expectedPolicy);
        assertThat(deletionBox.getValue()).isEqualTo(expectedPolicy);
    }

    @Test
    void shouldChangeDuplicationPolicyInBoxWhenPolicyChangeInConfig() {
        ActionPolicy expectedPolicy = ActionPolicy.YES;
        config.getDuplicateFilesPolicyProperty().set(expectedPolicy);
        assertThat(duplicateBox.getValue()).isEqualTo(expectedPolicy);
    }

}
