package bath.config;

import bath.config.jsonAdapter.EventAdapter;
import bath.config.jsonAdapter.SpringfoxJsonToGsonAdapter;
import bath.parameters.event.EventAddParameters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import springfox.documentation.spring.web.json.Json;

public class GsonFactory {
    public static Gson get() {
        return new GsonBuilder()
                .registerTypeAdapter(EventAddParameters.class, new EventAdapter())
                .registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter())
                .create();
    }
}
