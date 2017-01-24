package src.dto.gestion.inventary;

public class ElementDTO
{
    private Integer element_id;
    private Integer element_room_id;
    private Integer element_level;
    private Integer element_parent;
    private byte element_has_child;

    public ElementDTO()
    {}

    public ElementDTO(Integer room)
    {
        element_room_id = room;
    }

    public ElementDTO(Integer room, Integer level, Integer parent)
    {
        element_room_id = room;
        element_level = level;
        element_parent = parent;
    }

    public ElementDTO(Integer room, Integer level, Integer parent, byte child)
    {
        element_room_id = room;
        element_level = level;
        element_parent = parent;
        element_has_child = child;
    }

    public Integer getElementId()
    {
        return element_id;
    }

    public void setElementId(Integer id)
    {
        element_id = id;    
    }

    public Integer getRoomId()
    {
        return element_room_id;
    }
  
    public void setRoomId(Integer r_id)
    {
        element_room_id = r_id;
    }

    public int getElementLevel()
    {
        return element_level;
    }

    public void setElementLevel(int level)
    {
        element_level = level;
    }

    public Integer getElementParent()
    {
        return element_parent;
    }
   
    public void setElementParent(Integer value)
    {
        element_parent = value;
    }

    public byte getHaveChild()
    {
        return element_has_child;
    }

    public void setHaveChild(byte value)
    {
        element_has_child = value;
    }

    public String toString()
    {
        return ""+getElementId();
    }
}
