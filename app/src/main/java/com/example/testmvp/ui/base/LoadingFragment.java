package com.example.testmvp.ui.base;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.example.testmvp.R;


public class LoadingFragment extends DialogFragment {


    public static LoadingFragment newInstance() {

        Bundle args = new Bundle();


        LoadingFragment fragment = new LoadingFragment();
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_loading, null);

        ImageView iv_logo = (ImageView) dialogView.findViewById(R.id.iv_logo);
        TextView tv1 = (TextView) dialogView.findViewById(R.id.tv_message);

        String strtext = getArguments().getString("textMessage");
        tv1.setText(strtext);

        DrawableImageViewTarget imageViewMainTarget2 = new DrawableImageViewTarget(iv_logo);
        Glide.with(getContext()).load(R.raw.loading).into(imageViewMainTarget2);

        iv_logo.setVisibility(View.VISIBLE);
        builder.setView(dialogView);

        final Dialog dialog = builder.create();
        dialog.setCancelable(false);
        return dialog;
    }

    @Override
    public void onStart() {
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.onStart();
    }
}
