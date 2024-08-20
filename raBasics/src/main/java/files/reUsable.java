package files;

import io.restassured.path.json.JsonPath;

public class reUsable {
    public static JsonPath rawToJson(String getPlaceResponse){
        JsonPath getPlaceJson = new JsonPath(getPlaceResponse);
        return getPlaceJson;
    }
}
