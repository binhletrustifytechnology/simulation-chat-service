package com.example.chatservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // This will exclude null fields during serialization
public class CommonResponse<T> {
    private boolean success;
    private T data;
    private String message;
    private String messageCode;

    // Static method to return success response
    public static <T> CommonResponse<T> ok(T data) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setMessage(null);
        response.setMessageCode(null);
        return response;
    }

    public static <T> CommonResponse<T> ok(T data, ResponseMessage responseMessage) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setMessage(responseMessage.getMessage());
        response.setMessageCode(responseMessage.getCode());
        return response;
    }

    public static <T> CommonResponse<T> ok(ResponseMessage responseMessage) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setSuccess(true);
        response.setData(null);
        response.setMessage(responseMessage.getMessage());
        response.setMessageCode(responseMessage.getCode());
        return response;
    }

    public static <T> CommonResponse<T> failed(ResponseMessage responseMessage) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setSuccess(false);
        response.setData(null);
        response.setMessage(responseMessage.getMessage());
        response.setMessageCode(responseMessage.getCode());
        return response;
    }

    public static <T> CommonResponse<T> failed(String message) {
        return failed(null, message);
    }

    public static <T> CommonResponse<T> failed(String code, String message) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setSuccess(false);
        response.setData(null);
        response.setMessage(message);
        response.setMessageCode(code);
        return response;
    }

    public static <T> CommonResponse<T> failed(T data, ResponseMessage responseMessage) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setSuccess(false);
        response.setData(data);
        response.setMessage(responseMessage.getMessage());
        response.setMessageCode(responseMessage.getCode());
        return response;
    }

}
