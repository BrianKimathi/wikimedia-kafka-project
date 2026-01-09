package com.briankimathi.wikimediaproducer.kafka;

import com.briankimathi.wikimediaproducer.utils.WikimediaChangesHandler;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import okhttp3.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaKafkaProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {

        String topic = "wikimedia_recentchange";
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topic);
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url))
                .headers(
                        Headers.of("User-Agent",
                                "Spring-Boot-Kafka-Producer-Bot/1.0 (Contact: kimathibrian71@gmail.com)"
                        )
                );
        EventSource eventSource = builder.build();

        eventSource.start();

        TimeUnit.MINUTES.sleep(10);

    }

}
