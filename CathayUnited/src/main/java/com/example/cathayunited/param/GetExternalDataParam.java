package com.example.cathayunited.param;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class GetExternalDataParam {

    private Req req;

    public Req getReq() {
        return req;
    }

    public void setReq(Req req) {
        this.req = req;
    }

    public static class Req {

        @Schema(description = "List of keys", example = "[\"10480016\"]")
        private List<String> keys;

        @Schema(description = "From date", example = "2023/03/10")
        private String from;

        @Schema(description = "To date", example = "2024/03/10")
        private String to;

        // Getters and Setters
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
