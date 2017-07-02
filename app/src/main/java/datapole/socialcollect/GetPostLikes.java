package datapole.socialcollect;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dhruv on 2/7/17.
 */

public class GetPostLikes extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "GetPostLikes";
    String accessToken = "";
    String postId = "";
    HttpHandler sh = new HttpHandler();
    String postUrl;
    String likeJson;
    private int totalLikes = 0;
    private String[] idLikes;
    private String[] nameLikes;

    public GetPostLikes(String accessToken, String postId) {
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
        postUrl = "https://graph.facebook.com/" + postId + "/likes?access_token=" + accessToken;
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        JSONObject jsonObject;
        likeJson = sh.makeServiceCall(postUrl);
        Log.d(TAG, "jsonLikes" + likeJson);

        try {
            jsonObject = new JSONObject(likeJson);
            JSONArray jsonData = jsonObject.getJSONArray("data");
            Log.d(TAG, "jsonData: " + jsonData);
            totalLikes = jsonData.length();
            Log.d(TAG, "totalLikes: " + totalLikes);
            idLikes = new String[totalLikes];
            nameLikes = new String[totalLikes];

            for (int i = 0; i < totalLikes; i++) {
                idLikes[i] = String.valueOf(jsonData.getJSONObject(i));
                nameLikes[i] = String.valueOf(jsonData.getJSONObject(i));
            }
            Log.d(TAG, "likesINFO: " + idLikes[2] + "...." + nameLikes[2]);
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













