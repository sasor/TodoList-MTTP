package src.dto.gestion.users;

public class ProfileUserDTO
{
    private Integer _id;  
    private Integer profile_id;
    private Integer user_id;

    public ProfileUserDTO()
    {}

    public ProfileUserDTO(Integer profile, Integer user)
    {
        profile_id = profile;
        user_id = user;
    }

    public Integer getID()
    {
        return _id;
    }

    public Integer getProfileId()
    {
        return profile_id;
    }

    public Integer getUserId()
    {
        return user_id;
    }

    public void setID(Integer id)
    {
        _id = id;
    }

    public void setProfileId(Integer id)
    {
        profile_id = id;
    }

    public void setUserId(Integer id)
    {
        user_id = id;
    }
}
