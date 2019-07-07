package com.capco.capcopay.federal.request;

public class Header {

	private String password;

    private String user_id;

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [password = "+password+", user_id = "+user_id+"]";
    }
}
