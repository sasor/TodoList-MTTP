package src.views;

import javax.swing.*;
import java.awt.*;

import src.views.panels.*;
import src.views.panels.users.*;
import src.views.panels.modules.*;
import src.views.panels.profiles.*;
import src.views.panels.licenses.*;

public class AdminJTP extends JTabbedPane
{
    private ProfilePanel my_profile;  

    private UsersPanel usersP;
    private ModulesPanel modulesP;
    private ProfilesPanel profilesP;
    private LicensesPanel licensesP;

    public AdminJTP()
    {
        super();
        this.my_profile = new ProfilePanel();
        this.usersP = new UsersPanel();
        this.modulesP = new ModulesPanel();
        this.profilesP = new ProfilesPanel();
        this.licensesP = new LicensesPanel();
        
        this.addTab("My_Profile", my_profile); 
        this.addTab("Users", usersP);
        this.addTab("Modules", modulesP);
        this.addTab("Profiles", profilesP);
        this.addTab("Licenses", licensesP);
    }

    public ProfilePanel getMyProfileView()
    {
        return my_profile;
    }

    public UsersPanel getUsersView()
    {
        return usersP;
    }

    public ModulesPanel getModulesView()
    {
        return modulesP;
    }

    public ProfilesPanel getProfilesView()
    {
        return profilesP;
    }

    public LicensesPanel getLicensesView()
    {
        return licensesP;
    }
}
