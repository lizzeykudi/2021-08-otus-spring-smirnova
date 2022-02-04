package ru.otus.spring.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class MyHealthIndicator implements HealthIndicator {

//    private final Random random = new Random();

//    @Override
//    public Health health() {
//        boolean serverIsDown = random.nextBoolean();
//        if (serverIsDown) {
//            return Health.down()
//                    .status(Status.DOWN)
//                    .withDetail("message", "Караул!")
//                    .build();
//        } else {
//            return Health.up().withDetail("message", "Ура, товарищи!").build();
//        }
//    }

    @Override
    public Health health() {
        double chance = ThreadLocalRandom.current().nextDouble();
        Health.Builder status = Health.up();
        if (chance > 0.9) {
            status = Health.down();
        }
        return status.build();
    }
}
