package dto;

public class LicenseDTO
{
    private Integer license_id;
    private Integer module_id;
    private String license_action;
    private String license_description;

    public LicenseDTO()
    {}

    public LicenseDTO(Integer id, String action, String description)
    {
        module_id = id;
        license_action = action;
        license_description = description;
    }

    public LicenseDTO(String action, String description)
    {
        license_action = action;
        license_description = description;
    }

    public Integer getLicenseId()
    {
        return license_id;
    }

    public Integer getModuleId()
    {
        return module_id;
    }

    public String getLicenseAction()
    {
        return license_action; 
    }

    public String getLicenseDescription()
    {
        return license_description;
    }

    public void setLicenseId(Integer id)
    {
        license_id = id;
    }

    public void setModuleId(Integer id)
    {
        module_id = id;
    }

    public void setLicenseAction(String action)
    {
        license_action = action;
    }

    public void setLicenseDescription(String description)
    {
        license_description = description;
    }

    public String toString()
    {
        return "";
    }
}
