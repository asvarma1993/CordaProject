package com.template.webserver.project;

public class Response {
    private String status;
    private String response;
    private String error;

    public String getLocId() {
        return locId;
    }

    public void setLocId(String locId) {
        this.locId = locId;
    }

    private String locId;

    public Response() {
    }

    public Response(String status, String response, String error) {
        this.status = status;
        this.response = response;
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
