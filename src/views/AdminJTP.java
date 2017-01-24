package src.views;

import javax.swing.*;
import java.awt.*;

import src.controllers.*;
import src.controllers.admin.*;

import src.views.panels.*;
import src.views.panels.admin.*;
import src.views.panels.users.*;
import src.views.panels.modules.*;
import src.views.panels.profiles.*;
import src.views.panels.licenses.*;

public class AdminJTP extends JTabbedPane
{
    private ProfilePanel my_profile;  
    private ProfileController me_controller;

    private UsersPanel usersP;
    private ModulesPanel modulesP;
    private ProfilesPanel profilesP;
    private LicensesPanel licensesP;
    public static InventaryPanel inventaryP;

    private UserPanelController u_model;
    private ProfilePanelController p_model;
    private ModulePanelController m_model;
    private LicensePanelController l_model; 
    private InventaryController i_model;

    public static ExitPanel exit_panel;

    public AdminJTP()
    {
        super();
        me_controller = new ProfileController();
        u_model = new UserPanelController(); 
        p_model = new ProfilePanelController(); 
        m_model = new ModulePanelController(); 
        l_model = new LicensePanelController(); 
        i_model = new InventaryController();

        this.my_profile = new ProfilePanel(me_controller);
        this.usersP = new UsersPanel(u_model);
        this.modulesP = new ModulesPanel(m_model);
        this.profilesP = new ProfilesPanel(p_model);
        this.licensesP = new LicensesPanel(l_model);
        this.inventaryP = new InventaryPanel(i_model);
        this.exit_panel = new ExitPanel();
        
        this.addTab("My_Profile", my_profile); 
        this.addTab("Users", usersP);
        this.addTab("Modules", modulesP);
        this.addTab("Profiles", profilesP);
        this.addTab("Licenses", licensesP);
        this.addTab("Inventary", inventaryP);
        this.addTab("Exit", exit_panel);
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
