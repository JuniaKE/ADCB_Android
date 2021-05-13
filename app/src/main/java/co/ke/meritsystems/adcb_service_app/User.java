package co.ke.meritsystems.adcb_service_app;

public class User {
    int id;
    String name,email,last_login,phone;

    public User(int id, String phone, String name, String email, String last_login) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.last_login = last_login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }
}
