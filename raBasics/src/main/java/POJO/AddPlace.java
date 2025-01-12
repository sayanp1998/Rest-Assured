package POJO;

import java.util.List;

public class AddPlace {

    private int accuracy;
    private String name;
    private String phone_number;
    private String address;
    private String website;
    private String languagage;
    private addPlaceLocation location;
    private List<String> types;

    public int getAccuracy(int i) {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number(String s) {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLanguagage() {
        return languagage;
    }

    public void setLanguagage(String languagage) {
        this.languagage = languagage;
    }

    public addPlaceLocation getLocation() {
        return location;
    }

    public void setLocation(addPlaceLocation location) {
        this.location = location;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
