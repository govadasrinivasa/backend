package com.capco.capcopay.federal.request;

public class RemmiterDetails {

	private String AccNumber;

    private String Email;

    private String Mobile;

    private String Notification_Flag;

    private String Name;

    public String getAccNumber ()
    {
        return AccNumber;
    }

    public void setAccNumber (String AccNumber)
    {
        this.AccNumber = AccNumber;
    }

    public String getEmail ()
    {
        return Email;
    }

    public void setEmail (String Email)
    {
        this.Email = Email;
    }

    public String getMobile ()
    {
        return Mobile;
    }

    public void setMobile (String Mobile)
    {
        this.Mobile = Mobile;
    }

    public String getNotification_Flag ()
    {
        return Notification_Flag;
    }

    public void setNotification_Flag (String Notification_Flag)
    {
        this.Notification_Flag = Notification_Flag;
    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [AccNumber = "+AccNumber+", Email = "+Email+", Mobile = "+Mobile+", Notification_Flag = "+Notification_Flag+", Name = "+Name+"]";
    }
}
