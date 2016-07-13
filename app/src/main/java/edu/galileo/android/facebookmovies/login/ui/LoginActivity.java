package edu.galileo.android.facebookmovies.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.facebookmovies.R;
import edu.galileo.android.facebookmovies.moviemain.ui.MovieMainActivity;

/**
 * Created by ajv.
 */
public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.btnLogin) LoginButton loginButton;
    @Bind(R.id.loginContainer) RelativeLayout loginContainer;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (AccessToken.getCurrentAccessToken() != null) {
            navigateToMainScreen();
        }

        callbackManager = CallbackManager.Factory.create();
        loginButton.setPublishPermissions(Arrays.asList("publish_actions"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                navigateToMainScreen();
            }

            @Override
            public void onCancel() {
                Snackbar.make(loginContainer, R.string.login_cancel_error, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                String msgError = String.format(getString(R.string.login_error), exception.getMessage());
                Snackbar.make(loginContainer, msgError, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void navigateToMainScreen() {
        startActivity(new Intent(this, MovieMainActivity.class));
    }
}
