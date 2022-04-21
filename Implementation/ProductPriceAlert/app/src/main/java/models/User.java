package models;

public class User {
    private String name;
    private String email;
    private String password;
    /*private int id;*/
    private String type;

    public User(String name, String email, String password, String type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }
}
