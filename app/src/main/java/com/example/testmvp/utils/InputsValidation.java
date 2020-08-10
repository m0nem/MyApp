package com.example.testmvp.utils;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

public class InputsValidation {

    private InputsValidation(){

    }

    private static boolean isValidEmail(String target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private static boolean isValidPhone(String number){

        if(number.length()<11 || number.length()>11) {
            return false;

        }else {

            return true;
        }


    }

    private static boolean isValidName(String name){

        if(name.length()<5 || name.length()>20) {
            return false;

        }else {

            return true;
        }
    }

    private static boolean isValidDate(String date){

        if(date.length()<8 || date.length()>8) {
            return false;

        }else {

            return true;
        }
    }

    private static boolean isValidCode(String code){

        if(code.length()<10 || code.length()>10) {
            return false;

        }else {

            return true;
        }
    }

    public static boolean validate(String value , int type){

        switch (type){

            case 1 :

                return InputsValidation.isValidName(value);


            case 2 :
                return InputsValidation.isValidEmail(value);


            case 3 :
                return  InputsValidation.isValidPhone(value);


            case 4 :
                return InputsValidation.isValidCode(value);

            case 5 :
                return InputsValidation.isValidDate(value);


            default:
                return false;

        }


    }

}
