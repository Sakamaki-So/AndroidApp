package jp.ac.meijo.android.s221205073;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.Optional;

import jp.ac.meijo.android.s221205073.databinding.ActivityMain3Binding;
import jp.ac.meijo.android.s221205073.databinding.ActivityMainBinding;

public class MainActivity3 extends AppCompatActivity {
    public static final String KEY_MSG = "textMsg";
    public static final String KEY_RES = "res";
    private ActivityMain3Binding binding;
    @SuppressLint("SetTextI18n")
    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch(result.getResultCode()) {
                    case RESULT_OK:
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra(KEY_RES))
                                .map(text -> "Result: " + text)
                                .ifPresent(text -> binding.textView2.setText(text));
                        break;
                    case RESULT_CANCELED:
                        binding.textView2.setText("Result: Canceled");
                        break;
                    default:
                        binding.textView2.setText("Result: Unknown(" + result.getResultCode() + ")");
                        break;
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonExplicit.setOnClickListener(v -> {
            var intent = new Intent(this, SubActivity.class);
            startActivity(intent);
        });
        binding.buttonImplicit.setOnClickListener(v -> {
            var intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });
        binding.buttonSend.setOnClickListener(v -> {
            var text = binding.distText.getText().toString();
            var intent = new Intent(this, SubActivity.class);
            intent.putExtra(KEY_MSG, text);
            startActivity(intent);
        });
        binding.buttonLaunch.setOnClickListener(v -> {
            var intent = new Intent(this, SubActivity.class);
            getActivityResult.launch(intent);
        });
    }
}