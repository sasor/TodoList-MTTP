package src.dto.gestion.users;

public class ProfileDTO
{
    private Integer profile_id;
    private String profile_name;
    private byte profile_active;

    public ProfileDTO()
    {}

    public ProfileDTO(String name)
    {
        profile_name = name;
    }

    public ProfileDTO(String name, byte active)
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

    public byte getProfileActive()
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

    public void setProfileActive(byte active)
    {
        profile_active = active;
    }

    public String toString()
    {
        String SS = ", ";
        String id = "ID: " + getProfileId() + SS;
        String name = "NAME: " + getProfileName() + SS;
        String active = "ACTIVE:" + getProfileActive();
        return "Profile { " + id + name + active + " }";
    }
}
