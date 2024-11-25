package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    private TextView textView2;
    private int primerValor = 0;
    private int segundoValor = 0;
    private String operador = "";
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        textView2 = findViewById(R.id.textView2);

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();
                if (isOperatorPressed) {
                    textView2.setText(buttonText);  // Start new number after operator
                    isOperatorPressed = false;
                } else {
                    textView2.append(buttonText);
                }
            }
        };

        findViewById(R.id.button0).setOnClickListener(numberClickListener);
        findViewById(R.id.button1).setOnClickListener(numberClickListener);
        findViewById(R.id.button2).setOnClickListener(numberClickListener);
        findViewById(R.id.button3).setOnClickListener(numberClickListener);
        findViewById(R.id.button4).setOnClickListener(numberClickListener);
        findViewById(R.id.button5).setOnClickListener(numberClickListener);
        findViewById(R.id.button6).setOnClickListener(numberClickListener);
        findViewById(R.id.button7).setOnClickListener(numberClickListener);
        findViewById(R.id.button8).setOnClickListener(numberClickListener);
        findViewById(R.id.button9).setOnClickListener(numberClickListener);

        // Botones de operaciones
        View.OnClickListener operatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                operador = button.getText().toString();
                primerValor = Integer.parseInt(textView2.getText().toString());
                isOperatorPressed = true;
            }
        };

        findViewById(R.id.buttonSuma).setOnClickListener(operatorClickListener);
        findViewById(R.id.buttonResta).setOnClickListener(operatorClickListener);
        findViewById(R.id.buttonMultiplicar).setOnClickListener(operatorClickListener);
        findViewById(R.id.buttonDividir).setOnClickListener(operatorClickListener);

        // Botón de igual
        findViewById(R.id.buttonEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                segundoValor = Integer.parseInt(textView2.getText().toString());
                int result = 0;

                switch (operador) {
                    case "+":
                        result = primerValor + segundoValor;
                        break;
                    case "-":
                        result = primerValor - segundoValor;
                        break;
                    case "*":
                        result = primerValor * segundoValor;
                        break;
                    case "/":
                        if (segundoValor != 0) {
                            result = primerValor / segundoValor;
                        } else {
                            textView2.setText("Error");
                            return;
                        }
                        break;
                }
                textView2.setText(String.valueOf(result));
                isOperatorPressed = true;  // Reset for the next calculation
            }
        });

        // Para limpiar el TextView al hacer clic sobre él
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setText("");
                primerValor = 0;
                segundoValor = 0;
                operador = "";
                isOperatorPressed = false;
            }
        });

    }
}