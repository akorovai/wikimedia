package dev.akorovai.wikimedia.wproducer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WikimediaConsumer {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@KafkaListener(topics = "wikimedia-stream", groupId = "wiki-group")
	public void consumeMessage(String message) {
		try {
			JsonNode jsonNode = objectMapper.readTree(message);


			String type = jsonNode.path("type").asText();
			String title = jsonNode.path("title").asText();
			String user = jsonNode.path("user").asText();
			String comment = jsonNode.path("comment").asText();
			String timestamp = jsonNode.path("timestamp").asText();

			log.info("Consumed message:");
			log.info("Type: {}", type);
			log.info("Title: {}", title);
			log.info("User: {}", user);
			log.info("Comment: {}", comment);
			log.info("Timestamp: {}", timestamp);


			switch (type) {
				case "edit":
					handleEditMessage(jsonNode);
					break;
				case "categorize":
					handleCategorizeMessage(jsonNode);
					break;
				default:
					log.warn("Unhandled message type: {}", type);
			}

		} catch (JsonProcessingException e) {
			log.error("Failed to parse message: {}", message, e);
		} catch (Exception e) {
			log.error("Error processing message: {}", message, e);
		}
	}

	private void handleEditMessage(JsonNode jsonNode) {
		String titleUrl = jsonNode.path("title_url").asText();
		String lengthOld = jsonNode.path("length").path("old").asText();
		String lengthNew = jsonNode.path("length").path("new").asText();


		log.info("Edit details:");
		log.info("Title URL: {}", titleUrl);
		log.info("Length old: {}", lengthOld);
		log.info("Length new: {}", lengthNew);


	}

	private void handleCategorizeMessage(JsonNode jsonNode) {

		String titleUrl = jsonNode.path("title_url").asText();
		String comment = jsonNode.path("comment").asText();


		log.info("Categorize details:");
		log.info("Title URL: {}", titleUrl);
		log.info("Comment: {}", comment);


	}
}
