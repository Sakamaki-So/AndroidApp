package jp.ac.meijo.android.s221205073;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import jp.ac.meijo.android.s221205073.databinding.ActivityMain4Binding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity4 extends AppCompatActivity {
    private static final String URL = "https://placehold.jp/";
    private static final String FILE_NAME = "OkHttp.txt";
    private final OkHttpClient client = new OkHttpClient();
    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);
    private ActivityMain4Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.getButton.setOnClickListener(v -> {
            var text = binding.editableText.getText().toString();
            var size = Optional.of(binding.editableNum.getText().toString())
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt)
                    .orElse(150);
            var url = Uri.parse(URL + getUrlPng(size))
                    .buildUpon()
                    .appendQueryParameter("text", text)
                    .build()
                    .toString();
            getImage(url);
        });
    }
    private String getUrlPng(int size) {
        return size + "x" + size + ".png";
    }

    private void getImage(String url) {
        var request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Optional.ofNullable(response.body())
                        .ifPresent(b -> {
                            var bitmap = BitmapFactory.decodeStream(b.byteStream());
                            runOnUiThread(() -> binding.imageView.setImageBitmap(bitmap));
                        });
            }
        });
    }
}