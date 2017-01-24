package src.dto.gestion.inventary;

public class DataDTO
{
    private Integer data_id;
    private Integer data_place_id;
    private Integer data_element_id;
    private String data_name;
    private String data_description;
    private java.util.Date data_storage;
    private java.util.Date data_expiration;

    public DataDTO()
    {}

    public DataDTO(Integer place, Integer element, String name, String description, java.util.Date storage, java.util.Date expiration)
    {
        data_place_id = place;
        data_element_id = element;
        data_name = name;
        data_description = description;
        data_storage = storage;
        data_expiration = expiration;
    }

    public Integer getDataId()
    {
        return data_id;
    }
   
    public void setDataId(Integer id)
    {
        data_id = id;
    }

    public Integer getDataPlaceId()
    {
        return data_place_id;
    }

    public void setDataPlaceId(Integer place)
    {
        data_place_id = place;
    }

    public Integer getDataElementId()
    {
        return data_element_id;
    }

    public void setDataElementId(Integer element)
    {
        data_element_id = element;
    }

    public String getDataName()
    {
        return data_name;
    }

    public String getDataDescription()
    {
        return data_description;
    }
  
    public void setDataName(String name)
    {
        data_name = name;
    }
   
    public void setDataDescription(String description)
    {
        data_description = description;
    }

    public java.util.Date getDataStorage()
    {
        return data_storage;
    }

    public void setDataStorage(java.util.Date storage)
    {
        data_storage = storage;
    }

    public java.util.Date getDataExpiration()
    {
        return data_expiration;
    }

    public void setDataExpiration(java.util.Date expiration)
    {
        data_expiration = expiration;
    }
}
