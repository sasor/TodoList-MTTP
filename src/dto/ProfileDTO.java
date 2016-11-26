package dto;

public class ProfileDTO
{
    private Integer profile_id;
    private String profile_name;
    private int profile_active;

    public ProfileDTO()
    {}

    public ProfileDTO(String name)
    {
        profile_name = name;
    }

    public ProfileDTO(String name, int active)
    {
        profile_name = name;
        profile_active = active;
    }

    public Integer getProfileId()
    {
        return profile_id;
    }

    public String getProfileName()
    {
        return profile_name;
    }

    public int getProfileActive()
    {
        return profile_active;
    }

    public void setProfileId(Integer id)
    {
        profile_id = id;
    }

    public void setProfileName(String name)
    {
        profile_name = name;
    }

    public void setProfileActive(int active)
    {
        profile_active = active;
    }

    public String toString()
    {
        return "";
    }
}
