package datapole.socialcollect;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dhruv on 2/7/17.
 */

public class GetPostComments extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "GetPostComments";
    String accessToken = "";
    String postId = "";
    HttpHandler sh = new HttpHandler();
    String postUrl;
    String cmntJson;

    public GetPostComments(String accessToken, String postId) {
        this.accessToken = accessToken;
        this.postId = postId;
    }

    //    change the names... and uncomment
    @Override
    protected void onPreExecute() {
//        progressDialog = new ProgressDialog(CabActivity.this);
//        progressDialog.setTitle("Loading");
//        progressDialog.setMessage("Estimating Ride Cost and Time...");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
        postUrl = "https://graph.facebook.com/" + postId + "/comments?access_token=" + accessToken;
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        JSONObject jsonObject;
        JSONObject jsonObject1;
        String cmntList[];
        cmntJson = sh.makeServiceCall(postUrl);
        Log.d(TAG, "jsonLikes" + cmntJson);

        try {
            jsonObject = new JSONObject(cmntJson);
            JSONArray data = jsonObject.getJSONArray("data");
            Log.d(TAG, "jsonCommentsLen:: " + data.length());
            cmntList = new String[data.length()];
            for (int i = 0; i < data.length(); i++) {
                cmntList[i] = data.getJSONObject(i).getString("message");
            }
            Log.d(TAG, "message:: " + cmntList[2]);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
//        progressDialog.dismiss();

        super.onPostExecute(aVoid);
    }
}
