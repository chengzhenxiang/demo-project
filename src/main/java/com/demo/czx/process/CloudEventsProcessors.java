package com.demo.czx.process;

import com.demo.czx.model.CloudEventDemoTopicData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class CloudEventsProcessors {

    @Bean
    public Consumer<KStream<String, CloudEventDemoTopicData>> process() {
        return input -> input.foreach(((key, value) -> log.info("process data: {}", value.toString())));
    }
}
