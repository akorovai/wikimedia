package dev.akorovai.wproducer.config.stream;

import dev.akorovai.wproducer.producer.WikimediaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WikimediaStreamConsumer {
	private final WebClient webClient;
	private final WikimediaProducer producer;

	public void consumeStreamAndPublish(){
		webClient.get()
				.uri("/stream/recentchange")
				.retrieve()
				.bodyToFlux(String.class)
				.subscribe(producer::sendMessage);

	}
}
