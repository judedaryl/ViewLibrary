package com.savvysherpa.loginview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.savvysherpa.loginview.listeners.LoginViewListener;

public class LoginView extends LinearLayout {

    // LAYOUT VARIABLES
    TextView mTitleTextView, mForgotPasswordTextView, mLoginLabel, mLoginError, mPasswordLabel, mPasswordError;
    EditText mLoginEditText, mPasswordEditText;
    Button mSubmitButton;
    ImageView mLogoImageView;



    // CLASS VARIABLES
    private String mTitle = getResources().getString(R.string.defaultTitle);
    private String mLoginHint = getResources().getString(R.string.defaultLoginHint);
    private String mPasswordHint = getResources().getString(R.string.defaultPasswordHint);
    private String mSubmitText = getResources().getString(R.string.defaultSubmitText);
    private String mForgotPasswordText = getResources().getString(R.string.defaultForgotPassword);
    private int mTitleColor = getContext().getColor(R.color.defaultTitleColor);
    private int mInputColor = getContext().getColor(R.color.defaultInputColor);
    private int mSubmitTextColor = getContext().getColor(R.color.defaultSubmitTextColor);
    private int mSubmitBackgroundColor = getContext().getColor(R.color.defaultSubmitBackgroundColor);
    private int mForgotPasswordColor = getContext().getColor(R.color.defaultForgotPasswordColor);
    private boolean mForgotPasswordShow = false;
    private boolean mVisiblePassword = false;
    private Drawable mLogo;
    private float mLogoSize = getResources().getDimension(R.dimen.default_logo_size);
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

        // Initialize Title COlor
        if(a.hasValue(R.styleable.LoginView_title_color))
            mTitleColor = a.getColor(R.styleable.LoginView_title_color, mTitleColor);

        // Initialize Login Hint
        if(a.hasValue(R.styleable.LoginView_login_hint))
            mLoginHint = a.getString(R.styleable.LoginView_login_hint);

        // Initialize Password Hint
        if(a.hasValue(R.styleable.LoginView_password_hint))
            mPasswordHint = a.getString(R.styleable.LoginView_password_hint);

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

        // Initialize Input Color
        if(a.hasValue(R.styleable.LoginView_input_color))
            mInputColor = a.getColor(R.styleable.LoginView_input_color, mInputColor);

        // Initialize Forgot Password Text
        if(a.hasValue(R.styleable.LoginView_forgot_password_text))
            mForgotPasswordText = a.getString(R.styleable.LoginView_forgot_password_text);

        // Initialize Forgot Password Color
        if(a.hasValue(R.styleable.LoginView_forgot_password_color))
            mForgotPasswordColor = a.getColor(R.styleable.LoginView_forgot_password_color, mForgotPasswordColor);

        // Initialize Show Forgot Password
        if (a.hasValue(R.styleable.LoginView_show_forgot_password))
            mForgotPasswordShow = a.getBoolean(R.styleable.LoginView_show_forgot_password, false);

        // Initialize Logo Size
        if (a.hasValue(R.styleable.LoginView_logo_size))
            mLogoSize = a.getDimension(R.styleable.LoginView_logo_size, mLogoSize);

        setupUserInterface();

        if(mTitle != null) {
            mTitleTextView.setText(mTitle);
        }
        a.recycle();
    }

    @SuppressWarnings("deprecation")
    private void setupUserInterface() {
        mTitleTextView = findViewById(R.id.LoginViewTitle);
        mLoginEditText = findViewById(R.id.LoginViewLoginEditText);
        mPasswordEditText = findViewById(R.id.LoginViewPasswordEditText);
        mSubmitButton = findViewById(R.id.LoginViewSubmitButton);
        mLogoImageView = findViewById(R.id.LoginViewLogo);
        mForgotPasswordTextView = findViewById(R.id.LoginViewForgotPassword);
        mLoginLabel = findViewById(R.id.LoginViewLoginInputLabel);
        mPasswordLabel = findViewById(R.id.LoginViewPasswordInputLabel);
        mLoginError = findViewById(R.id.LoginViewLoginInputError);
        mPasswordError = findViewById(R.id.LoginViewPasswordInputError);



        mTitleTextView.setText(mTitle);
        mTitleTextView.setTextColor(mTitleColor);

        mLoginEditText.setHint(mLoginHint);
        mLoginEditText.setBackgroundTintList(ColorStateList.valueOf(mInputColor));
        mLoginEditText.setTextColor(mInputColor);
        mLoginEditText.setHintTextColor(mInputColor);
        mLoginLabel.setVisibility(INVISIBLE);
        mLoginLabel.setText(mLoginHint);
        mLoginLabel.setTextColor(mInputColor);

        mPasswordEditText.setHint(mPasswordHint);
        mPasswordEditText.setBackgroundTintList(ColorStateList.valueOf(mInputColor));
        mPasswordEditText.setTextColor(mInputColor);
        mPasswordEditText.setHintTextColor(mInputColor);
        mPasswordLabel.setVisibility(INVISIBLE);
        mPasswordLabel.setText(mPasswordHint);
        mPasswordLabel.setTextColor(mInputColor);;


        mSubmitButton.setText(mSubmitText);
        mSubmitButton.setTextColor(mSubmitTextColor);
        mSubmitButton.setBackgroundTintList(ColorStateList.valueOf(mSubmitBackgroundColor));

        mLogoImageView.setImageDrawable(mLogo);
        mLogoImageView.getLayoutParams().height = (int) mLogoSize;
        mLogoImageView.getLayoutParams().width = (int) mLogoSize;
        mLogoImageView.requestLayout();

        mForgotPasswordTextView.setText(mForgotPasswordText);
        mForgotPasswordTextView.setTextColor(mForgotPasswordColor);
        mForgotPasswordTextView.setVisibility(mForgotPasswordShow ? VISIBLE : GONE);

    }


    /**
     * Register Listener
     */
    @SuppressWarnings("unused")
    public void setListener(LoginViewListener listener) {
        mListener = listener;
        if(mListener != null)
            initializeListener(mListener);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initializeListener(final LoginViewListener listener) {
        mLoginEditText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLoginTapped();
            }
        });

        mLoginEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listener.onLoginTyping(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mLoginEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                boolean isEmpty = TextUtils.isEmpty(((TextView) v).getText());
                if(hasFocus) {
                    mLoginLabel.setVisibility(VISIBLE);
                    mLoginEditText.setHint("");
                    mLoginError.setText("");
                    mLoginError.setVisibility(INVISIBLE);
                } else {
                    mLoginEditText.setHint(isEmpty ? mLoginHint : "" );
                    mLoginLabel.setVisibility(isEmpty ? INVISIBLE : VISIBLE);
                }
            }
        });

        mPasswordEditText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPasswordTapped();
            }
        });

        mPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listener.onPasswordTyping(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPasswordEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                boolean isEmpty = TextUtils.isEmpty(((TextView) v).getText());
                if(hasFocus) {
                    mPasswordLabel.setVisibility(VISIBLE);
                    mPasswordEditText.setHint("");
                    mPasswordError.setText("");
                    mPasswordError.setVisibility(INVISIBLE);
                } else {
                    mPasswordEditText.setHint(isEmpty ? mPasswordHint : "" );
                    mPasswordLabel.setVisibility(isEmpty ? INVISIBLE : VISIBLE);
                }
            }
        });

        mPasswordEditText.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (mPasswordEditText.getRight() - mPasswordEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        Drawable visible = getContext().getDrawable(R.drawable.ic_visibility_black_24dp);
                        if(visible != null) {
                            visible = DrawableCompat.wrap(visible);
                            DrawableCompat.setTint(visible, mInputColor);
                        }
                        Drawable invisible = getContext().getDrawable(R.drawable.ic_visibility_off_black_24dp);
                        if(invisible != null) {
                            invisible = DrawableCompat.wrap(invisible);
                            DrawableCompat.setTint(invisible, mInputColor);
                        }
                        mPasswordEditText.setCompoundDrawablesWithIntrinsicBounds(null, null, mVisiblePassword ? visible : invisible,null);
                        int show = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
                        int hide = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
                        mPasswordEditText.setInputType(mVisiblePassword ? hide : show);
                        mPasswordEditText.setSelection(mPasswordEditText.getText().length());
                        mVisiblePassword = !mVisiblePassword;
                        mPasswordEditText.requestFocus();

                        return true;
                    }
                }
                return false;
            }
        });

        mLogoImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLogoTapped();
            }
        });

        mSubmitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = mLoginEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                listener.onLoginSubmit(login, password);
            }
        });

        mForgotPasswordTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onForgotPasswordTapped();
            }
        });
    }

    /**
     * Resets login edit text
     */
    @SuppressWarnings("unused")
    public void resetLogin() {
        mLoginEditText.setText("");
        if(mListener != null)
            mListener.onLoginTyping("");
    }

    /**
     * Resets login edit text
     * @param defaultValue Default Value for login edit text
     */
    @SuppressWarnings("unused")
    public void resetLogin(String defaultValue) {
        mLoginEditText.setText(defaultValue);
        if(mListener != null)
            mListener.onLoginTyping(defaultValue);
    }

    /**
     * Resets password edit text
     */
    @SuppressWarnings("unused")
    public void resetPassword() {
        mPasswordEditText.setText("");
        if(mListener != null)
            mListener.onPasswordTyping("");
    }

    /**
     * Resets password edit text
     * @param defaultValue Default Value for password edit text
     */
    @SuppressWarnings("unused")
    public void resetPassword(String defaultValue) {
        mPasswordEditText.setText(defaultValue);

        if(mListener != null)
            mListener.onPasswordTyping(defaultValue);
    }

    /**
     * Resets login and password edit texts
     */
    @SuppressWarnings("unused")
    public void resetLoginAndPassword() {
        mLoginEditText.setText("");
        mPasswordEditText.setText("");

        if(mListener != null) {
            mListener.onLoginTyping("");
            mListener.onPasswordTyping("");
        }
    }

    /**
     * Resets password edit text
     * @param defaultLoginValue Default Value for login edit text
     * @param defaultPasswordValue Default Value for password edit text
     */
    @SuppressWarnings("unused")
    public void resetLoginAndPassword(String defaultLoginValue, String defaultPasswordValue) {
        mPasswordEditText.setText(defaultLoginValue);
        mPasswordEditText.setText(defaultPasswordValue);

        if(mListener != null) {
            mListener.onLoginTyping(defaultLoginValue);
            mListener.onPasswordTyping(defaultPasswordValue);
        }
    }

    /**
     * Shows an error on the login field
     * @param error Error string shown on field
     */
    @SuppressWarnings("unused")
    public void showLoginError(String error) {
        mLoginError.setVisibility(VISIBLE);
        mLoginError.setText(error);
    }

    /**
     * Shows an error on the password field
     * @param error Error string shown on field
     */
    @SuppressWarnings("unused")
    public void showPasswordError(String error) {
        mPasswordError.setVisibility(VISIBLE);
        mPasswordError.setText(error);
    }

    /**
     * Sets the visibility of the Forgot Password Text View
     * @param show true shows forgot password, false hides forgot password
     */
    @SuppressWarnings("unused")
    public void showForgotPassword(boolean show) {
        mForgotPasswordTextView.setVisibility(show ? VISIBLE : GONE);
    }

    /**
     * Enables or disabled submit button
     * @param enable true enables submit button, false disables submit button
     */
    @SuppressWarnings("unused")
    public void enableSubmit(boolean enable) {
        mSubmitButton.setEnabled(enable);
    }



}
