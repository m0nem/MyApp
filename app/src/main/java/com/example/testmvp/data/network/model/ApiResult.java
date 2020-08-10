package com.example.testmvp.data.network.model;

public class ApiResult {
    /*
            "Result": {

    },
            "DataCount": 0,
            "State": 1,
            "Message": ""*/

    ApiFormData Result;

    Integer DataCount;
    Integer State;
    String Message;

    public ApiResult(ApiFormData result, Integer dataCount, Integer state, String message) {
        Result = result;
        DataCount = dataCount;
        State = state;
        Message = message;
    }

    public ApiResult() {
    }

    public ApiFormData getResult() {
        return Result;
    }

    public void setResult(ApiFormData result) {
        Result = result;
    }

    public Integer getDataCount() {
        return DataCount;
    }

    public void setDataCount(Integer dataCount) {
        DataCount = dataCount;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
