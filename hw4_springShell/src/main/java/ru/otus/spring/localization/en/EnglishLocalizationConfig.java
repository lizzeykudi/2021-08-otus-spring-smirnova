package ru.otus.spring.localization.en;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring.localization.LocalizationConfig;

@Component
@ConditionalOnProperty(prefix = "config", name = "locale", havingValue = "en")
@Configuration
public class EnglishLocalizationConfig implements LocalizationConfig {

    @Value("${language.enQuestionsFilePath}")
    private String questionsFileName;

    public String getQuestionsFileName() {
        return questionsFileName;
    }

    @Bean("messageSource")
    @ConditionalOnProperty(prefix = "config", name = "locale", havingValue = "en")
    public ResourceBundleMessageSource EnMessageSource() {
        var source = new ResourceBundleMessageSource();
        source.setBasenames("i18n.messages");
        source.setDefaultEncoding("UTF-8");
        return source;
    }
}
