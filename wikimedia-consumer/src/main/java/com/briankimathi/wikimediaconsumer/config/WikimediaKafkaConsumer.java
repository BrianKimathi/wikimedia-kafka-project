package com.briankimathi.wikimediaconsumer.config;

import com.briankimathi.wikimediaconsumer.entity.WikimediaData;
import com.briankimathi.wikimediaconsumer.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WikimediaKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaKafkaConsumer.class);

    private WikimediaDataRepository dataRepository;

    public WikimediaKafkaConsumer(WikimediaDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @KafkaListener(topics = "wikimedia_recentchange", groupId = "wikimediaConsumers")
    public void consume(String eventMsg) {
        LOGGER.info(String.format("Receive message from WikimediaConsumers topic: %s", eventMsg));

        WikimediaData wikimediaData = new WikimediaData();

        wikimediaData.setWikiEventData(eventMsg);

        dataRepository.save(wikimediaData);
    }

}
