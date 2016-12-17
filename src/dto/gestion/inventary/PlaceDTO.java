package src.dto.gestion.inventary;

public class PlaceDTO
{
    private Integer place_id;
    private String place_object;

    public PlaceDTO()
    {}

    public PlaceDTO(String place)
    {
        place_object = place;
    }

    public Integer getPlaceId()
    {
        return place_id;
    }
   
    public void setPlaceId(Integer id)
    {
        place_id = id;
    }

    public String getPlaceObject()
    {
        return place_object;
    }

    public void setPlaceObject(String place)
    {
        place_object = place;
    }
}
