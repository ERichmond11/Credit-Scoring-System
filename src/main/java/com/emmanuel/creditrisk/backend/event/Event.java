package com.emmanuel.creditrisk.backend.event;

import java.time.Instant;

public class Event {

    private String type;
    private String details;
    private Instant timestamp;

    public Event() {}

    public Event(String type, String details) {
        this.type = type;
        this.details = details;
        this.timestamp = Instant.now();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}