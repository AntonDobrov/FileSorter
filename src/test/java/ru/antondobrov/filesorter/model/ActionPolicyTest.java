package ru.antondobrov.filesorter.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.antondobrov.filesorter.utils.ILocalizer;

@ExtendWith(MockitoExtension.class)
public class ActionPolicyTest {

    @ParameterizedTest
    @CsvSource({"YES, actionpolicy.yes", "NO, actionpolicy.no", "ASK, actionpolicy.ask",
            "EMPTY, actionpolicy.empty"})
    void shouldReturnDescription(ActionPolicy actualPolicy, String expectedPolicy) {

        assertThat(actualPolicy.getDescription()).isEqualTo(expectedPolicy);
    }

    @Mock
    private ILocalizer localizer;

    @ParameterizedTest
    @CsvSource({"YES, Always yes", "NO, Always no", "ASK, Always ask", "EMPTY, "})
    void shouldReturnTranslatedDisplayName_whenKeyExist(ActionPolicy actualPolicy,
            String expectedDisplayName) {
        when(localizer.get(actualPolicy.getDescription())).thenReturn(expectedDisplayName);

        assertThat(actualPolicy.getDisplayName(localizer)).isEqualTo(expectedDisplayName);
    }

    @ParameterizedTest
    @CsvSource({"YES", "NO", "ASK", "EMPTY"})
    void shouldReturnDescriptionInsteadOfDisplayName_whenKeyNotExist(ActionPolicy actualPolicy) {
        when(localizer.get(actualPolicy.getDescription()))
                .thenReturn(actualPolicy.getDescription());

        assertThat(actualPolicy.getDisplayName(localizer)).isEqualTo(actualPolicy.getDescription());
    }
}
