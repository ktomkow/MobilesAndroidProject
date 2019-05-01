package com.example.aprojectktomkow;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aprojectktomkow.Models.Forms.IValidatorResult;
import com.example.aprojectktomkow.Models.Forms.Login.LoginForm;

public class LoginActivity extends AppCompatActivity
{
    private final int REGISTRATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        hideError();
    }

    public void login(View view)
    {
        hideError();

        LoginForm loginForm = new LoginForm();
        loginForm.setEmail(getEmail());
        loginForm.setPassword(getPassword());

        IValidatorResult formValidationResult = loginForm.Validate();
        if(!formValidationResult.isValid())
        {
            showError(formValidationResult.errorMessage());
        }

        hideKeyboard(this);
    }

    private String getEmail()
    {
        EditText emailEditText = findViewById(R.id.email);
        return emailEditText.getText().toString();
    }

    private String getPassword()
    {
        EditText passwordEditText = findViewById(R.id.password);
        return passwordEditText.getText().toString();
    }

    private void showError(String error)
    {
        if(error != null && error.length() > 0)
        {
            TextView errorMessage = findViewById(R.id.error_message);
            errorMessage.setVisibility(View.VISIBLE);
            errorMessage.setText(error);
        }
    }

    private void hideError()
    {
        TextView errorMessage = findViewById(R.id.error_message);
        errorMessage.setVisibility(View.GONE);
    }

    public void goToRegister(View view)
    {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, REGISTRATION);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void finish()
    {
        Intent intent = new Intent();

        intent.putExtra("result", "some result body");

        setResult(RESULT_OK, intent);
        super.finish();
    }
}
