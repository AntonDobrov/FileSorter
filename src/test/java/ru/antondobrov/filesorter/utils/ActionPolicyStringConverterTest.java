package ru.antondobrov.filesorter.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.antondobrov.filesorter.model.ActionPolicy;

@ExtendWith(MockitoExtension.class)
class ActionPolicyStringConverterTest {
    @Mock
    ILocalizer localizer;

    @ParameterizedTest
    @CsvSource({"YES, Always yes", "NO, Always no", "ASK, Always ask", "EMPTY, "})
    void shouldGiveHumanReadableStringForActionWhenLocalizerIsNotNull(ActionPolicy actualPolicy,
            String expectedString) {
        when(localizer.get(actualPolicy.getDescription())).thenReturn(expectedString);
        ActionPolicyStringConverter converter = new ActionPolicyStringConverter(localizer);

        assertThat(converter.toString(actualPolicy)).isEqualTo(expectedString);
    }

    @ParameterizedTest
    @CsvSource({"YES", "NO", "ASK", "EMPTY"})
    void shouldGiveNotHumanReadableStringForActionWhenLocalizerIsNull(ActionPolicy actualPolicy) {
        ActionPolicyStringConverter converter = new ActionPolicyStringConverter(null);

        assertThat(converter.toString(actualPolicy)).isEqualTo(actualPolicy.getDescription());
    }

    @Test
    void shouldGiveEmptyStringWhenPolicyIsNull() {
        ActionPolicyStringConverter converter = new ActionPolicyStringConverter(localizer);

        assertThat(converter.toString(null)).isEmpty();
    }

    void shouldReturnEmptyPolicyFromAnyString() {
        ActionPolicyStringConverter converter = new ActionPolicyStringConverter(null);

        assertThat(converter.fromString(anyString())).isEqualTo(ActionPolicy.EMPTY);
    }
}
