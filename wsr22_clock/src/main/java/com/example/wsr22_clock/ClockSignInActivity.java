package com.example.wsr22_clock;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.wsr22_clock.databinding.ActivityClockSignInBinding;

public class ClockSignInActivity extends Activity {

    private TextView mTextView;
    private ActivityClockSignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityClockSignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = binding.text;
    }
}