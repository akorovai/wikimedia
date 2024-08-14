package dev.akorovai.wproducer.controller;

import dev.akorovai.wproducer.config.stream.WikimediaStreamConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/wikimedia")
public class WikimediaController {
	private final WikimediaStreamConsumer streamConsumer;

	@GetMapping
	public void startPublishing(){
		streamConsumer.consumeStreamAndPublish();
	}
}
