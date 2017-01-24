package src.dto.gestion.users;

public class LicenseUserDTO
{
    private Integer _id;
    private Integer license_id;
    private Integer user_id;
 
    public LicenseUserDTO()
    {}

    public LicenseUserDTO(Integer license, Integer user)
    {
        license_id = license;
        user_id = user;
    }

    public Integer getID()
    {
        return _id;
    }

    public Integer getLicenseId()
    {
        return license_id;
    }

    public Integer getUserId()
    {
        return user_id;
    }

    public void setID(Integer id)
    {
        _id = id;
    }

    public void setLicenseId(Integer id)
    {
        license_id = id;
    }

    public void setUserId(Integer id)
    {
        user_id = id;
    }
}
