package src.views;

import javax.swing.*;
import java.awt.*;

import src.views.panels.access.LoginPanel;
import src.views.panels.access.SignUpPanel;
import src.controllers.*;

public class LoginRegisterForm extends JTabbedPane
{
    private LoginPanel loginP;
    private SignUpPanel signP;

    public LoginRegisterForm(LoginRegisterController model)
    {
        super();
        this.loginP = new LoginPanel(model);
        this.signP = new SignUpPanel(model);
        
        this.addTab("Login", loginP); 
        this.addTab("SignUp", signP);
    }

    public LoginPanel getLoginView()
    {
        return loginP;
    }

    public SignUpPanel getSignUpView()
    {
        return signP;
    }
}
