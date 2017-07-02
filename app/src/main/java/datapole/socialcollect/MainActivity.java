package datapole.socialcollect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetPostLikes postLikes = new GetPostLikes("EAACEdEose0cBAGNL0e3rGuwIxoHJI9RkCfpJLLMZBS7lD6a0my8SSBjplQIeB3dZAtqC5A86IfVWs9hDNZA5SAuF2LT6MUVRFP50YTMPmzTAQsgFeZBj9KWvB0INehZAVmB8mV3kHrkNUKSrktsfyj3xU2Ve7rgKQjPIbf4CJLvP56rD5zvMEg3jHAkcukXoZD", "10152832773477094_10155478705882094");
        postLikes.execute();
        GetPostComments postComments = new GetPostComments("EAACEdEose0cBAGNL0e3rGuwIxoHJI9RkCfpJLLMZBS7lD6a0my8SSBjplQIeB3dZAtqC5A86IfVWs9hDNZA5SAuF2LT6MUVRFP50YTMPmzTAQsgFeZBj9KWvB0INehZAVmB8mV3kHrkNUKSrktsfyj3xU2Ve7rgKQjPIbf4CJLvP56rD5zvMEg3jHAkcukXoZD", "10152832773477094_10155478705882094");
        postComments.execute();
    }
}