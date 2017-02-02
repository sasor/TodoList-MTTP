public class Te
{
    public static void main(String[] args)
    {
        java.util.Date now = new java.util.Date();
        System.out.println(now);

        System.out.println(now.getTime());

        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        System.out.println(sqlDate);
        System.out.println("============================");

        java.text.SimpleDateFormat nn=new java.text.SimpleDateFormat("hh:mm:ss");
        System.out.println(nn.format(now));
    }
}
