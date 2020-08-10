package com.example.testmvp.data.network.model;

import com.example.testmvp.utils.AppConst;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ApiResultViewModel<T> {


    List<String> Messages;
    Boolean IsSuccess;
    Object Data;
    int ErrorCode;

    public ApiResultViewModel() {
        Messages = new ArrayList<>();
        IsSuccess = false;
        ErrorCode = AppConst.API_ERROR_CODE;
    }

    public ApiResultViewModel(List<String> messages, Boolean isSuccess, Object data, int errorCode) {
        Messages = messages;
        if (isSuccess == null)
            IsSuccess = false;
        else
            IsSuccess = isSuccess;
        Data = data;
        ErrorCode = errorCode;
    }

    public List<String> getMessages() {
        return Messages;
    }

    public void setMessages(List<String> messages) {
        Messages = messages;
    }

    public Boolean getSuccess() {
        if (IsSuccess == null)
            return false;
        else
            return IsSuccess;
    }

    public void setSuccess(Boolean success) {
        if (success == null)
            IsSuccess = false;
        else
            IsSuccess = success;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public T getConvertedData(Class<T> classType) {
        try {
            Gson g = new Gson();
            String json = g.toJson(Data);
            T model = new Gson().fromJson(json
                    , classType);
            return model;
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> getConvertedListData(Type classType) {
        try {
            Gson g = new Gson();
            String json = g.toJson(Data);
            List<T> list = (List<T>) g.fromJson(json, classType);
            return list;
        } catch (Exception e) {
            return null;
        }
    }


}
