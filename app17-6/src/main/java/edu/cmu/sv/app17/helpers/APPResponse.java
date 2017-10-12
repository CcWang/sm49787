package edu.cmu.sv.app17.helpers;

public class APPResponse {
    public boolean success;
    public Object content;
    public APPResponse(Object content) {
        this.success = true;
        this.content = content;
    }

    public APPResponse() {
        this.success = true;
    }
}