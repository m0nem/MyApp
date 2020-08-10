
package com.example.testmvp.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;


public abstract class BaseActivity extends AppCompatActivity {

    final String TAG = "BaseActivity";



    private LoadingFragment mProgressDialog;
  //  private boolean hideDialogCallFlag = false;//

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onViewReady(savedInstanceState, getIntent());
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        //resolveDaggerDependency();
        //To be used by child activities
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    protected void showDialog(String message) {
       // if(!hideDialogCallFlag) {
            if (mProgressDialog == null) {
                mProgressDialog = LoadingFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("textMessage", message);
                mProgressDialog.setArguments(bundle);
                // mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                //    mProgressDialog.setCancelable(false);

            }
            //  mProgressDialog.setMessage(message);
            if (mProgressDialog.getDialog() == null &&
                    mProgressDialog.getTag() == null) {
                try {
                    mProgressDialog.show(getSupportFragmentManager(), "dialog_alert");
                } catch (Exception e) {
//                    Log.e(TAG, "" + e.getMessage());
                }

            }
      //  }
    }

    protected void hideDialog() {
      //  hideDialogCallFlag = true;
        try {
            if (mProgressDialog != null &&
                    mProgressDialog.getDialog() != null
            ) {
               // hideDialogCallFlag = false;
                if( mProgressDialog.getDialog().isShowing()){
                    mProgressDialog.dismiss();
                }
            }
        } catch (Exception e) {
//            Log.e(TAG, "hideDialogError" + e.getMessage());
           // hideDialogCallFlag = false;
        }
    }


}
