package ru.otus.spring.localization.ru;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring.localization.LocalizationConfig;

import java.util.Locale;

@Component
@ConditionalOnProperty(prefix = "config", name = "locale", havingValue = "ru")
@Configuration
public class RussianLocalizationConfig implements LocalizationConfig {

    @Value("${language.ruQuestionsFilePath}")
    private String questionsFileName;

    public String getQuestionsFileName() {
        return questionsFileName;
    }

    @Bean("messageSource")
    @ConditionalOnProperty(prefix = "config", name = "locale", havingValue = "ru")
    public ResourceBundleMessageSource ruMessageSource() {
        var source = new ResourceBundleMessageSource();
        source.setBasenames("i18n.messages");
        source.setDefaultEncoding("UTF-8");
        source.setDefaultLocale(Locale.forLanguageTag("ru-RU"));
        return source;
    }
}
