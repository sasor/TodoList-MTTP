package src.stuff;

public class DAOException extends Exception
{
    private String message;

    public DAOException()
    {}
   
    public DAOException(String message)
    {
        super(message);
    }

    public DAOException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public DAOException(Throwable cause)
    {
        super(cause);
    }

    public String getMessage()
    {
        return message;
    }
}
