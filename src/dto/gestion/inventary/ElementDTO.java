package src.dto.gestion.inventary;

public class ElementDTO
{
    private Integer element_id;
    private Integer element_room_id;
    private Integer element_level;

    private byte have_parent;
    private byte have_child;

    public ElementDTO()
    {}

    public ElementDTO(Integer room, Integer level)
    {
        room_id = room;
        element_level = level;
    }

    public ElementDTO(Integer room, Integer level, byte parent, byte child)
    {
        room_id = room;
        element_level = level;
        have_parent = parent;
        have_child = child;
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

    public byte getHaveParent()
    {
        return have_parent;
    }
   
    public void setHaveParent(byte value)
    {
        have_parent = value;
    }

    public byte getHaveChild()
    {
        return have_child;
    }

    public void setHaveChild(byte value)
    {
        have_child = value;
    }
}
