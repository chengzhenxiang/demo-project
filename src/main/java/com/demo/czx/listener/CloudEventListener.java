package com.demo.czx.listener;

import com.alibaba.fastjson.JSON;
import com.demo.czx.model.CloudEventDemoTopicData;
import io.cloudevents.CloudEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CloudEventListener {

    @KafkaListener(topics = "topic-test",
            id = "topic-test",
            groupId = "${spring.application.name}",
            properties = {
                    "spring.json.use.type.headers=false",
                    "spring.json.value.default.type=io.cloudevents.CloudEvent"
            }
    )
    public void test(CloudEvent message) {
        var a = message.getData();

        var data = JSON.parseObject(a.toBytes(), CloudEventDemoTopicData.class);
        log.info("listener data: {}", data.toString());
    }
}
