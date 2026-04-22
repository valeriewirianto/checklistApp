package org.example.checklistapp.response;

public class ApiResponse<T> {
    private ApiResponseInfo responseInfo;
    private T responseResult;

    public ApiResponse(T responseResult, String message){
        this.responseInfo = new ApiResponseInfo(message);
        this.responseResult = responseResult;
    }

    public ApiResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseResult(T responseResult) {
        this.responseResult = responseResult;
    }

    public T getResponseResult() {
        return responseResult;
    }

    public void setResponseInfo(ApiResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
