package datapole.socialcollect;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONObject;

import java.util.Arrays;

public class ShareActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    ShareDialog shareDialog;
    ShareButton shareButton;
    public static final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);


        LoginManager.getInstance().logInWithReadPermissions(
                ShareActivity.this,
                Arrays.asList("user_posts"));

        FacebookSdk.sdkInitialize(this.getApplicationContext());
        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com")).setQuote("Connect to the global scale.")
                .build();

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                if(result.getPostId()!=null) {
                    Log.d(TAG,result.getPostId());
                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        shareButton = (ShareButton) findViewById(R.id.share_button);
        shareButton.setShareContent(content);
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                    .build();
            shareDialog.show(linkContent);
            GraphRequestAsyncTask request = new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/me/feed",
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
            /* handle the result */
                            Log.d(TAG, "onCompleted: " + response.toString());
                        }
                    }
            ).executeAsync();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
