package com.mattjtodd;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.LongAccumulator;

@RestController
public class Sender {

    private final LongAccumulator longAccumulator;

    @Autowired
    private AmqpTemplate amqpTemplate;

    Sender(AmqpTemplate amqpTemplate) {
        longAccumulator = new LongAccumulator((left, right) -> left + right, 0);
        this.amqpTemplate = amqpTemplate;
    }

    @RequestMapping("/greeting")
    public long send(@RequestParam("message") String message) {
        longAccumulator.accumulate(1);
        amqpTemplate.convertAndSend(Application.topicExchangeName, "foo.bar.baz", message);
        return longAccumulator.get();
    }


    @RequestMapping("/greeting-reactive")
    public Mono<ResponseEntity<?>> sendReactive(@RequestParam("message") String message) {
        return Mono.fromCallable(() -> {
            longAccumulator.accumulate(1);
            amqpTemplate.convertAndSend(
                    Application.topicExchangeName,
                    "foo.bar.baz",
                    message);
            return ResponseEntity.accepted().build();
        });
    }
}
