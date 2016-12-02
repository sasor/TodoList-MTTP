package src.dto;

public class ModuleDTO
{
    private Integer module_id;
    private String module_name;
    private String module_description;

    public ModuleDTO()
    {}

    public ModuleDTO(String name, String description)
    {
        module_name = name;
        module_description = description;
    }
   
    public Integer getModuleId()
    {
        return module_id;
    }

    public String getModuleName()
    {
        return module_name; 
    }

    public String getModuleDescription()
    {
        return module_description; 
    }

    public String toString()
    {
        String SS = " ,";
        String id = "ID: " + getModuleId() + SS;
        String name = "NAME: " + getModuleName() + SS;
        String description = "DESCRIPTION: " + getModuleDescription();
        return "Module { " + id + name + description + " }";
    }

    public void setModuleId(Integer id)
    {
        module_id = id; 
    }

    public void setModuleName(String name)
    {
        module_name = name;
    }

    public void setModuleDescription(String description)
    {
        module_description = description; 
    }
}
