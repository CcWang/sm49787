package edu.cmu.sv.app17.exceptions;

public class APPExceptionInfo {
    private int httpStatusCode;
    private String httpStatusMessage;
    private int errorCode;
    private String errorMessage;
    public APPExceptionInfo(int hsc, String hsm, int ec, String em) {
        this.httpStatusCode=hsc;
        this.httpStatusMessage=hsm;
        this.errorCode=ec;
        this.errorMessage = em;
    }
    public int getHttpStatusCode() { return httpStatusCode; }
    public String getHttpStatusMessage() { return httpStatusMessage; }
    public int getErroCode() { return errorCode; }
    public String getErrorMessage() {return errorMessage; }
}
