package src.dto.gestion.inventary;

public class ElementDTO
{
    private Integer element_id;
    private Integer room_id;
    private int element_level;

    private byte have_parent;
    private byte have_child;

    public ElementDTO()
    {}

    public ElementDTO(Integer room, int level)
    {
        room_id = room;
        element_level = level;
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
        return room_id;
    }
  
    public void setRoomId(Integer r_id)
    {
        room_id = r_id;
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
