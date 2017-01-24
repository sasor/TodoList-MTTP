package src.controllers.propietary;

import src.stuff.DB;

public class BodyController
{
    private DB db;
    private Integer room_id;

    public BodyController(Integer id)
    {
        db = DB.instance();
        room_id = id;
    }
   
    public Integer getID()
    {
        return room_id;
    }

    public void add()
    {
        /*   */
        new AddElementParentController(room_id);     
    }
}
