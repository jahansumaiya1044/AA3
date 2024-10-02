package com.example.assignment3;
package com.example.assignment3;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText usernameInput, emailInput, passwordInput;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameInput = findViewById(R.id.usernameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString();
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (validateRegistrationForm(username, email, password)) {
                    Toast.makeText(MainActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid input, please check your details.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to validate the entire registration form
    private boolean validateRegistrationForm(String username, String email, String password) {
        return validateUsername(username) && validateEmail(email) && validatePassword(password);
    }

    // Validate username: only letters and numbers, length 4-20 characters
    private boolean validateUsername(String username) {
        if (TextUtils.isEmpty(username)) {
            usernameInput.setError("Username is required");
            return false;
        }

        String usernameRegex = "^[a-zA-Z0-9]{4,20}$";
        Pattern pattern = Pattern.compile(usernameRegex);
        Matcher matcher = pattern.matcher(username);

        if (!matcher.matches()) {
            usernameInput.setError("Invalid username! Must be 4-20 characters and contain only letters and numbers.");
            return false;
        }

        return true;
    }

    // Validate email with standard regex
    private boolean validateEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            emailInput.setError("Email is required");
            return false;
        }

        String emailRegex = "^[\\w-\\.]+@[\\w-\\.]+\\.[a-z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            emailInput.setError("Invalid email format");
            return false;
        }

        return true;
    }

    // Validate password: at least 8 characters, with at least one letter and one number
    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            passwordInput.setError("Password is required");
            return false;
        }

        String passwordRegex = "^(?=.[A-Za-z])(?=.\\d)[A-Za-z\\d]{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            passwordInput.setError("Password must be at least 8 characters long and contain at least one letter and one number.");
            return false;
        }

        return true;
    }
}
