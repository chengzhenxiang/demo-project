package com.demo.czx.controller;

import com.alibaba.fastjson.JSON;
import com.demo.czx.model.CloudEventDemoTopicData;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class ProducerController {

    private final KafkaTemplate kafkaTemplate;

    @GetMapping("/send")
    public void test() {
        var commissionNumber = "0123456789";
        CloudEvent event = CloudEventBuilder.v1()
                .withId(UUID.randomUUID().toString())
                .withType("stockvehicle.vu_sh_amendment/v1.1")
                .withSource(URI.create("cesar"))
                .withDataContentType("application/json")
                .withSubject(commissionNumber)
                .withTime(OffsetDateTime.now())
                .withData(JSON.toJSONBytes(CloudEventDemoTopicData.builder().commissionNumber(commissionNumber)
                        .netPrice("test").build()))
                .build();

        kafkaTemplate.send("topic-test", commissionNumber, event);
    }
}
