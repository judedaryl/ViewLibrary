package com.savvysherpa.loginview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.savvysherpa.loginview.listeners.LoginViewListener;

/**
 * TODO: document your custom view class.
 */
public class LoginView extends LinearLayout {

    // LAYOUT VARIABLES
    TextView mTitleTextView;
    TextInputLayout mLoginTextInputLayout, mPasswordInputLayout;
    EditText mLoginEditText, mPasswordEditText;
    Button mSubmitButton;
    ImageView mLogoImageView;


    // CLASS VARIABLES
    private String mTitle = getResources().getString(R.string.defaultTitle);
    private String mLoginHint = getResources().getString(R.string.defaultLoginHint);
    private String mPasswordHint = getResources().getString(R.string.defaultPasswordHint);
    private String mSubmitText = getResources().getString(R.string.defaultSubmitText);
    private int mSubmitTextColor = getContext().getColor(R.color.defaultSubmitTextColor);
    private int mSubmitBackgroundColor = getContext().getColor(R.color.defaultSubmitBackgroundColor);
    private Drawable mLogo;
    private LoginViewListener mListener;

    public LoginView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public LoginView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        inflate(context, R.layout.login_view, this);
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.LoginView, defStyle, 0);

        // Initialize Title
        if(a.hasValue(R.styleable.LoginView_title))
            mTitle = a.getString(R.styleable.LoginView_title);

        // Initialize Login Hint
        if(a.hasValue(R.styleable.LoginView_loginHint))
            mLoginHint = a.getString(R.styleable.LoginView_loginHint);

        // Initialize Password Hint
        if(a.hasValue(R.styleable.LoginView_passwordHint))
            mPasswordHint = a.getString(R.styleable.LoginView_passwordHint);

        // Initialize Submit Text
        if(a.hasValue(R.styleable.LoginView_submit_text))
            mSubmitText = a.getString(R.styleable.LoginView_submit_text);

        // Initialize Submit Background Color
        if(a.hasValue(R.styleable.LoginView_submit_background_color))
            mSubmitBackgroundColor = a.getColor(R.styleable.LoginView_submit_background_color, mSubmitBackgroundColor);

        // Initialize Submit Text Color
        if(a.hasValue(R.styleable.LoginView_submit_text_color))
            mSubmitTextColor = a.getColor(R.styleable.LoginView_submit_text_color, mSubmitTextColor);
        // Initialize Logo
        if (a.hasValue(R.styleable.LoginView_logo))
            mLogo = a.getDrawable(R.styleable.LoginView_logo);

        setupUserInterface();
        if(mTitle != null) {
            mTitleTextView.setText(mTitle);
        }
        a.recycle();
    }

    private void setupUserInterface() {
        mTitleTextView = findViewById(R.id.LoginViewTitle);
        mLoginTextInputLayout = (TextInputLayout) findViewById(R.id.LoginViewLoginInputLayout);
        mPasswordInputLayout = (TextInputLayout)  findViewById(R.id.LoginViewPasswordInputLayout);
        mLoginEditText = findViewById(R.id.LoginViewLoginEditText);
        mPasswordEditText = findViewById(R.id.LoginViewPasswordEditText);
        mSubmitButton = findViewById(R.id.LoginViewSubmitButton);
        mLogoImageView = findViewById(R.id.LoginViewLogo);
    }



    /**
     * Resets login edit text
     */
    public void resetLogin() {
        mLoginEditText.setText("");
    }

    /**
     * Resets login edit text
     * @param defaultValue Default Value for login edit text
     */
    public void resetLogin(String defaultValue) {
        mLoginEditText.setText(defaultValue);
    }

    /**
     * Resets password edit text
     */
    public void resetPassword() {
        mPasswordEditText.setText("");
    }

    /**
     * Resets password edit text
     * @param defaultValue Default Value for password edit text
     */
    public void resetPassword(String defaultValue) {
        mPasswordEditText.setText(defaultValue);
    }

    /**
     * Resets login and password edit texts
     */
    public void resetLoginAndPassword() {
        mLoginEditText.setText("");
        mPasswordEditText.setText("");
    }

    /**
     * Resets password edit text
     * @param defaultLoginValue Default Value for login edit text
     * @param defaultPasswordValue Default Value for password edit text
     */
    public void resetLoginAndPassword(String defaultLoginValue, String defaultPasswordValue) {
        mPasswordEditText.setText(defaultLoginValue);
        mPasswordEditText.setText(defaultPasswordValue);
    }

    public void showLoginError(String error) {
        mLoginEditText.setError(error);
    }

    public void showPasswordError(String error) {
        mPasswordEditText.setError(error);
    }

    public void enableSubmit(boolean enable) {
        mSubmitButton.setEnabled(enable);
    }



}
