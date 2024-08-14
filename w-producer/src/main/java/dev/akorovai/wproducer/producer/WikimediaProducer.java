package dev.akorovai.wproducer.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WikimediaProducer {
	private final KafkaTemplate<String, String> kafkaTemplate;


	public void sendMessage( String message ) {
		kafkaTemplate.send("wikimedia-stream", message);
	}
}
