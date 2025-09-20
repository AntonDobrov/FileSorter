package ru.antondobrov.filesorter.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.ResourceBundle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ActionPolicyTest {

    @ParameterizedTest
    @CsvSource({"YES, actionpolicy.yes", "NO, actionpolicy.no", "ASK, actionpolicy.ask",
            "EMPTY, actionpolicy.empty"})
    void shouldReturnDescription(ActionPolicy actualPolicy, String expectedPolicy) {

        assertThat(actualPolicy.getDescription()).isEqualTo(expectedPolicy);
    }

    @Mock
    private ResourceBundle mockBundle;

    @ParameterizedTest
    @CsvSource({"YES, Always yes", "NO, Always no", "ASK, Always ask", "EMPTY, "})
    void shouldReturnTranslatedDisplayName_whenKeyExist(ActionPolicy actualPolicy,
            String expectedDisplayName) {
        when(mockBundle.containsKey(actualPolicy.getDescription())).thenReturn(true);
        when(mockBundle.getString(actualPolicy.getDescription())).thenReturn(expectedDisplayName);

        assertThat(actualPolicy.getDisplayName(mockBundle)).isEqualTo(expectedDisplayName);
    }

    @ParameterizedTest
    @CsvSource({"YES", "NO", "ASK", "EMPTY"})
    void shouldReturnDescriptionInsteadOfDisplayName_whenKeyNotExist(ActionPolicy actualPolicy) {
        when(mockBundle.containsKey(actualPolicy.getDescription())).thenReturn(false);

        assertThat(actualPolicy.getDisplayName(mockBundle))
                .isEqualTo(actualPolicy.getDescription());
    }
}
