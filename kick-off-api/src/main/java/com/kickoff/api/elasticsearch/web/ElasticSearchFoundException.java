package com.kickoff.api.elasticsearch.web;

public class ElasticSearchFoundException extends RuntimeException {
    private String message;

    public ElasticSearchFoundException(String message) {
        super(message);

    }
}
