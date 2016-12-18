package src.queries;

public class RoomQueries
{
    private static final String KEY = "room_id";
    private static final String HOME_KEY = "room_home_id"; 
    private static final String ROOM_PLACE = "room_place_id";

    public static String create()
    {
        String fields = HOME_KEY+","+ROOM_PLACE;
        return "INSERT INTO rooms ("+fields+") VALUES (?, ?)";
    }

    public static String read()
    {
        return "SELECT * FROM rooms WHERE "+KEY+" = ?";
    }

    public static String update()
    {
        String fields = HOME_KEY+" = ?,"+ROOM_PLACE+" = ?";
        return "UPDATE rooms SET "+fields+" WHERE "+KEY+" = ?";
    }

    public static String delete()
    {
        return "DELETE FROM rooms WHERE "+KEY+" = ?";
    }

    public static String all()
    {
        return "SELECT * FROM rooms";
    }
}
