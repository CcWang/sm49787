package edu.cmu.sv.app17.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


public class APPUnauthorizedException extends WebApplicationException {

    public APPUnauthorizedException(int errorCode, String errorMessage) {
        super(Response.status(Status.UNAUTHORIZED).entity(new APPExceptionInfo(
                Status.UNAUTHORIZED.getStatusCode(),
                Status.UNAUTHORIZED.getReasonPhrase(),
                errorCode,
                errorMessage)
        ).type("application/json").build());
    }
}