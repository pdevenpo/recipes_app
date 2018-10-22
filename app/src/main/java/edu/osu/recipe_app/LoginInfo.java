package edu.osu.recipe_app;

public class LoginInfo {

    private String mEmail;
    private String mPassword;
    private String mName;

    public LoginInfo(){

    }

    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getName() {
        return mName;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
