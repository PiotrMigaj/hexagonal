package pl.migbud.hexagonal.infrastructure.notification.real.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.migbud.hexagonal.domain.common.emailnotification.EmailNotificationPort;

@Configuration
@Profile("!mem")
class EmailNotificationSmtpConfig {

    @Bean
    EmailNotificationPort emailNotificationPort(){
        return new EmailNotificationSmtpAdapter();
    }
}
