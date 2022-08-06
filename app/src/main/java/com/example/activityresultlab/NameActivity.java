package com.example.activityresultlab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.activityresultlab.databinding.ActivityNameBinding;

public class NameActivity extends AppCompatActivity {

    private ActivityNameBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityNameBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.buttonSubmit.setOnClickListener(view -> {
            Intent returnIntent = new Intent();
            if (!mBinding.textViewName.getText().toString().isEmpty()) {
                returnIntent.putExtra("nameData", mBinding.textViewName.getText().toString());
            }
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });

        mBinding.buttonCancel.setOnClickListener(view -> {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }
}