package jp.ac.meijo.android.s221205073;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import jp.ac.meijo.android.s221205073.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;
    private static final String KEY_NAME = "name";
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // do not touch above this line
        setContentView(binding.getRoot());
        prefDataStore = PrefDataStore.getInstance(this);

        prefDataStore.getString(KEY_NAME)
                .ifPresent(s -> binding.activityMainText.setText(s));
        binding.saveButton.setOnClickListener(v -> {
            var text = binding.editText.getText().toString();
            prefDataStore.setString(KEY_NAME, text);
        });
        binding.loadButton.setOnClickListener(v -> {
            prefDataStore.getString(KEY_NAME)
                    .ifPresent(s -> binding.activityMainText.setText(s));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        prefDataStore.getString(KEY_NAME)
                .ifPresent(s -> binding.activityMainText.setText(s));
    }
}