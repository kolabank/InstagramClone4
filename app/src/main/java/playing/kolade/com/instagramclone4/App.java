package playing.kolade.com.instagramclone4;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("sE7k3EbaXsgrtewi1M6rwphAbNcRJ13qKCRkzX7r")
                // if defined
                .clientKey("P9a5xuv6Oj78zuGHl8qgYaKsaIIZBex51SRBCHPF")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }

    }

