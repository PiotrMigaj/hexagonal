package pl.migbud.hexagonal.infrastructure.notification.inmemory.email;

import lombok.extern.slf4j.Slf4j;
import pl.migbud.hexagonal.domain.common.emailnotification.EmailNotificationPort;

import java.util.List;

@Slf4j
public class EmailNotificationAdapter implements EmailNotificationPort {
    @Override
    public void send(List<String> recipients, String message) {
        log.info("Sending email inmemory..., message: {}",message);
    }
}
