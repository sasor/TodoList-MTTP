package src.dto.gestion.inventary;

public class DataDTO
{
    private Integer data_id;
    private Integer data_place_id;

    public DataDTO()
    {}

    public DataDTO(Integer place)
    {
        data_place_id = place;
    }

    public Integer getDataId()
    {
        return data_id;
    }
   
    public void setDataId(Integer id)
    {
        data_id = id;
    }

    public Integer getPlaceId()
    {
        return data_place_id;
    }

    public void setPlaceId(Integer place)
    {
        data_place_id = place;
    }
}
