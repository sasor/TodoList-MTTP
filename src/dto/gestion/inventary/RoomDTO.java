package src.dto.gestion.inventary;

public class RoomDTO
{
    private Integer room_id;
    private Integer home_id;
    private Integer room_place_id;

    public RoomDTO()
    {}

    public RoomDTO(Integer home, Integer place)
    {
        home_id = home;
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

    public Integer getHomeId()
    {
        return home_id;
    }

    public void setHomeId(Integer h_id)
    {
        home_id = h_id;
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
