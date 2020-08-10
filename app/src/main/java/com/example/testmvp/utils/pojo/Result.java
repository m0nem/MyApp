package com.example.testmvp.utils.pojo;

import java.util.List;

/**
 * Created by Mohsen on 11/7/2017.
 */
public class Result {


    /// <summary>
    /// return 'true' when that no errors
    /// </summary>
    private boolean Success;

    public Object getReturnValue() {
        return ReturnValue;
    }

    public void setReturnValue(Object returnValue) {
        ReturnValue = returnValue;
    }

    /// <summary>
    /// return entity Id
    /// </summary>
    private Object ReturnValue;
    private boolean EndOfData = false;

    /// <summary>
    /// to set 'Success = true'
    /// </summary>
    public Result() {
        Success = true;
    }

    public List<Error> getErrors() {
        return Errors;
    }

    /// <summary>
    /// List of error! If no error then return null
    /// </summary>
    public List<Error> Errors;

    /// <summary>
    /// Add error to errors collection and set 'Success' to false
    /// </summary>
    /// <param name="title"></param>
    /// <param name="message"></param>
    /// <param name="errorType"></param>
    public void AddError(String title, String message, ErrorType errorType) {
        Errors.add(new Error(title, message, (ErrorType) errorType));
        //
        Success = false;
    }

    public boolean isEndOfData() {
        return EndOfData;
    }

    public void setEndOfData(boolean endOfData) {
        EndOfData = endOfData;
    }

    /// <summary>
    ///
    /// </summary>
    /// <param name="messages"></param>
    public void AddErrors(List<Error> messages) {
        for (Error error : messages) {
            AddError(error.getTitle(), error.getMessage(), ErrorType.Error);
        }


    }

    public boolean isSuccess() {
        return Success;
    }
}
