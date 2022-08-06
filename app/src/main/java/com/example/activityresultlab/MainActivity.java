package com.example.activityresultlab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activityresultlab.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    // Callback for getting the result from Name Activity
    @SuppressLint("SetTextI18n")
    ActivityResultLauncher<Intent> mGetNameActivity =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    (result) -> {

                        ActivityMainBinding mainBinding = this.getBinding();

                        // Validity checks
                        if (Activity.RESULT_OK != result.getResultCode()) {
                            mainBinding.textViewResultData.setText(
                                    "Activity has returned cancelled.");
                            return;
                        }

                        Intent intent = result.getData();

                        if (intent == null){
                            mainBinding.textViewResultData.setText(
                                    "Activity hasn't returned an intent.");
                            return;
                        }

                        if (!intent.hasExtra("nameData")){
                            mainBinding.textViewResultData.setText(
                                    "Activity hasn't returned extra data.");
                            return;
                        }

                        // Valid result returned
                        mainBinding.textViewResultData.setText("Activity has returned : "
                                + intent.getStringExtra("nameData"));
                    });

    private ActivityMainBinding mBinding;

    private ActivityMainBinding getBinding() {return mBinding;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // UI Setup

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        // Attach events to UI
        mBinding.fab.setOnClickListener(view -> launchNameActivity());
    }

    private void launchNameActivity() {
        Intent intent = new Intent(this, NameActivity.class);
        mGetNameActivity.launch(intent);
    }
}
