package jp.ac.meijo.android.s221205073;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import jp.ac.meijo.android.s221205073.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    private ActivityMain2Binding binding;
    private double value = 0;
    private int operand = 0;
    private int opcode = -1;
    private boolean isError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // do not touch above this line
        initButton();
    }

    private void initButton() {
        binding.buttonAdd.setOnClickListener(v -> opcode = 0);
        binding.buttonSub.setOnClickListener(v -> opcode = 1);
        binding.buttonMul.setOnClickListener(v -> opcode = 2);
        binding.buttonDiv.setOnClickListener(v -> opcode = 3);
        binding.buttonEqual.setOnClickListener(v -> {
            switch(opcode) {
                case 0 :
                    value += operand;
                    break;
                case 1 :
                    value -= operand;
                    break;
                case 2 :
                    value *= operand;
                    break;
                case 3 :
                    if(operand == 0) isError = true;
                    else value /= operand;
                    break;
                default:
                    value = operand;
                    break;
            }
        });
        binding.buttonAC.setOnClickListener(v -> {
            value = 0;
            opcode = -1;
            binding.numView.setText(String.valueOf(value));
        });
        binding.buttonZero.setOnClickListener(v -> {
            operand = 0;
            binding.numView.setText(operand);
        });
        binding.buttonOne.setOnClickListener(v -> {
            operand = 1;
            binding.numView.setText(operand);
        });
        binding.buttonTwo.setOnClickListener(v -> {
            operand = 2;
            binding.numView.setText(operand);
        });
        binding.buttonThree.setOnClickListener(v -> {
            operand = 3;
            binding.numView.setText(operand);
        });
        binding.buttonFour.setOnClickListener(v -> {
            operand = 4;
            binding.numView.setText(operand);
        });
        binding.buttonFive.setOnClickListener(v -> {
            operand = 5;
            binding.numView.setText(operand);
        });
        binding.buttonSix.setOnClickListener(v -> {
            operand = 6;
            binding.numView.setText(operand);
        });
        binding.buttonSeven.setOnClickListener(v -> {
            operand = 7;
            binding.numView.setText(operand);
        });
        binding.buttonEight.setOnClickListener(v -> {
            operand = 8;
            binding.numView.setText(operand);
        });
        binding.buttonNine.setOnClickListener(v -> {
            operand = 9;
            binding.numView.setText(operand);
        });
    }

}