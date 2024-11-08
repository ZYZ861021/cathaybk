package com.example.cathayunited.dto.request;

import java.util.List;

public class ExternalRequestDto {

    private Req req;

    public Req getReq() {
        return req;
    }

    public void setReq(Req req) {
        this.req = req;
    }

    public static class Req {
        private List<String> keys;
        private String from;
        private String to;

        // Getters and setters
        public List<String> getKeys() {
            return keys;
        }

        public void setKeys(List<String> keys) {
            this.keys = keys;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }
}