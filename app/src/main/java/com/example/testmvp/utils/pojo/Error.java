package com.example.testmvp.utils.pojo;

/**
 * Created by Mohsen on 11/7/2017.
 */
public class Error {

  /// <summary>
  ///
  /// </summary>
  /// <param name="title"></param>
  /// <param name="message"></param>
  /// <param name="errorType"></param>
  public Error(String title, String message, ErrorType errorType)
  {
    setTitle(title);
    setMessage(message);
    setErrorType(errorType);
  }

  /// <summary>
  /// title of message
  /// </summary>
  private String Title;

  /// <summary>
  /// message string
  /// </summary>
  private String Message;

  /// <summary>
  ///
  /// </summary>
  private ErrorType ErrorType;

  public String getTitle() {
    return Title;
  }

  public void setTitle(String title) {
    Title = title;
  }

  public String getMessage() {
    return Message;
  }

  public void setMessage(String message) {
    Message = message;
  }

  public ErrorType getErrorType() {
    return ErrorType;
  }

  public void setErrorType(ErrorType errorType) {
    ErrorType = errorType;
  }
}
