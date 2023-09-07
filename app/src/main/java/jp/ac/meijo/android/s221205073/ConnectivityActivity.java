package jp.ac.meijo.android.s221205073;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import jp.ac.meijo.android.s221205073.databinding.ActivityConnectivityBinding;

public class ConnectivityActivity extends AppCompatActivity {
    private ActivityConnectivityBinding binding;
    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConnectivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        connectivityManager = getSystemService(ConnectivityManager.class);
        var currentNetwork = connectivityManager.getActiveNetwork();
        updateTransport(currentNetwork);
        updateIpAddress(currentNetwork);
        registerNetworkCallback();
    }
    private void updateTransport(Network network) {
        var caps = connectivityManager.getNetworkCapabilities(network);
        Optional.ofNullable(caps)
                .ifPresent(c -> binding.transportName.setText(getNetworkName(c)));
    }
    private String getNetworkName(NetworkCapabilities caps) {
        if(caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            return "Wi-Fi";
        } else if(caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            return "モバイル通信";
        } else {
            return "その他";
        }
    }
    private void updateIpAddress(Network network) {
        var linkProperties = connectivityManager.getLinkProperties(network);
        Optional.ofNullable(linkProperties)
                .map(l -> l.getLinkAddresses()
                        .stream()
                        .map(LinkAddress::toString)
                        .collect(Collectors.joining("\n")))
                .ifPresent(binding.ipAddressName::setText);
    }

    private void registerNetworkCallback() {
        connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                runOnUiThread(() -> {
                    updateTransport(network);
                    updateIpAddress(network);
                });
            }
        });
    }
}