package models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
        private String name;
        private int id;
        private String type;

    public User(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
