package src.dto.gestion.inventary;

public class RoomDTO
{
    private Integer room_id;
    private String room_name;
    private Integer room_home_id;
    private Integer room_place_id;

    public RoomDTO()
    {}

    public RoomDTO(Integer home, Integer place)
    {
        room_home_id = home;
        room_place_id = place;
    }

    public RoomDTO(String name, Integer home, Integer place)
    {
        room_name = name;
        room_home_id = home;
        room_place_id = place;
    }

    public Integer getRoomId()
    {
        return room_id;
    }

    public void setRoomId(Integer id)
    {
        room_id = id;
    }

    public String getRoomName()
    {
        return room_name; 
    }

    public void setRoomName(String name)
    {
        room_name = name; 
    }

    public Integer getHomeId()
    {
        return room_home_id;
    }

    public void setHomeId(Integer h_id)
    {
        room_home_id = h_id;
    }

    public Integer getPlaceId()
    {
        return room_place_id;
    }

    public void setPlaceId(Integer place)
    {
        room_place_id = place;
    }
}
