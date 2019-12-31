package com.epam.maven;

/**
 * Response class, for correct logic
 * @author Andrei_Gordeev
 */
public class Response {
    final private Integer errorCode;
    final private String responseContent;

    public Response(Integer errorCode, String responseContent) {
        this.errorCode = errorCode;
        this.responseContent = responseContent;
    }
    public Integer getErrorCode(){
        return errorCode;
    }
    public String getResponseContent(){
        return responseContent;
    }
}
