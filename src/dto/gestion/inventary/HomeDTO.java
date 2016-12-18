package src.dto.gestion.inventary;

public class HomeDTO
{
    private Integer home_id;
    private String home_name;
    private Integer home_place_id;

    public HomeDTO()
    {}

    public HomeDTO(String name, Integer place)
    {
        home_name = name; 
        home_place_id = place;
    }

    public Integer getHomeId()
    {
        return home_id;
    }

    public void setHomeId(Integer id)
    {
        home_id = id;
    }

    public String getHomeName()
    {
        return home_name;
    }

    public void setHomeName(String name)
    {
        home_name = name;
    }

    public Integer getPlaceId()
    {
        String name = rs.getString();
        return home_place_id;
    }

    public void setPlaceId(Integer place)
    {
        home_place_id = place;
    }
}
