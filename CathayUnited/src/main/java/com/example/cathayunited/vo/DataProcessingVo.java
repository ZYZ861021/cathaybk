package com.example.cathayunited.vo;

public class DataProcessingVo {

    private boolean success;

    private int processedCount;

    private String message;

    public DataProcessingVo(boolean success, int processedCount, String message) {
        this.success = success;
        this.processedCount = processedCount;
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getProcessedCount() {
        return processedCount;
    }

    public void setProcessedCount(int processedCount) {
        this.processedCount = processedCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
