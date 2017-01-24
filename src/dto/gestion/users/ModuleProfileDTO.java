package src.dao.gestion.users;

public class ModuleProfileDTO
{
    private Integer _id;
    private Integer module_id;
    private Integer profile_id;

    public ModuleProfileDTO()
    {}

    public ModuleProfileDTO(Integer module, Integer profile)
    {
        module_id = module;
        profile_id = profile;
    }

    public Integer getID()
    {
        return _id;
    }

    public Integer getModuleId()
    {
        return module_id;
    }

    public Integer getProfileId()
    {
        return profile_id;
    }

    public void setID(Integer id)
    {
        _id = id;
    }

    public void setModuleId(Integer id)
    {
        module_id = id;
    }

    public void setProfileId(Integer id)
    {
        profile_id = id;
    }
}
