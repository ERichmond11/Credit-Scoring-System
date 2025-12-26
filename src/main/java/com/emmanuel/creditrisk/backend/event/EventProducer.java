package com.emmanuel.creditrisk.backend.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * EventProducer simulates publishing domain events.
 *
 * In production, this would publish to Kafka / SNS / EventBridge.
 * For now, it logs events safely without breaking the application.
 */
@Component
public class EventProducer {

    private static final Logger log = LoggerFactory.getLogger(EventProducer.class);

    /**
     * Publish an event (non-blocking, fail-safe).
     *
     * @param eventType   logical event name
     * @param description event details
     */
    public void publish(String eventType, String description) {
        try {
            Event event = new Event(eventType, description);

            // Simulated event publish
            log.info("EVENT_PUBLISHED | type={} | details={} | timestamp={}",
                    event.getType(),
                    event.getDetails(),
                    event.getTimestamp());

        } catch (Exception e) {
            // Important: event failures must NOT break credit scoring
            log.warn("EVENT_PUBLISH_FAILED | reason={}", e.getMessage());
        }
    }
}