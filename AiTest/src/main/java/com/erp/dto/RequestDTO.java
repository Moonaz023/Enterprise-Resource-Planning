package com.erp.dto;

import java.util.List;

public class RequestDTO {

    private List<Content> contents;

    public static class Content {
        private List<Part> parts;

        // Getters and setters
        public List<Part> getParts() {
            return parts;
        }

        public void setParts(List<Part> parts) {
            this.parts = parts;
        }
    }

    public static class Part {
        private String text;

        // Getters and setters
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    // Getters and setters
    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
