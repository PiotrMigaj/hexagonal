package pl.migbud.hexagonal.infrastructure.notification.inmemory.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.migbud.hexagonal.domain.common.emailnotification.EmailNotificationPort;

@Configuration
@Profile("mem")
class EmailNotificationConfig {
    @Bean
    EmailNotificationPort emailNotificationPort(){
        return new EmailNotificationAdapter();
    }
}
