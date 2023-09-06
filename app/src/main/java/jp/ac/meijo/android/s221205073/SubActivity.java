package jp.ac.meijo.android.s221205073;

import static jp.ac.meijo.android.s221205073.MainActivity3.KEY_MSG;
import static jp.ac.meijo.android.s221205073.MainActivity3.KEY_RES;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Optional;

import jp.ac.meijo.android.s221205073.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {
    private ActivitySubBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonOk.setOnClickListener(v -> {
            var intent = new Intent();
            intent.putExtra(KEY_RES, "meijo");
            setResult(RESULT_OK, intent);
            finish();
        });
        binding.buttonCancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
        binding.textView.setText(Optional
                .ofNullable(getIntent().getStringExtra(KEY_MSG))
                .orElse("TextView"));
    }
}