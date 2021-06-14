package Controladoras;

import Classes.SystemData;
import JDBC.Banco;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class ctrSystem 
{
    public boolean CheckSystem()
    {
        ResultSet rs = null;
        boolean result = false;
        
        try 
        {
           rs = Banco.getCon().consultar("SELECT COUNT(*) AS num FROM system");
           
           if(rs.next())
               result = rs.getInt("num") == 0;
           
        }
        catch ( SQLException sqlex )
        { 
           
        }
        
        return result;
    }
    
    public boolean SetSystem(SystemData b) throws FileNotFoundException, SQLException
    {
        Connection connection = null;
        PreparedStatement statement = null;
        FileInputStream inputStreamBig = null;
        FileInputStream inputStreamSmall = null;
        boolean result = false;
        
        try 
        {
            connection = Banco.getCon().getConnection();
            statement = connection.prepareStatement("INSERT INTO system(software_name, branch, logo_big, logo_small, email, fone) " + "VALUES(?,?,?,?,?,?)");
            statement.setString(1, b.getSoftware_name());
            statement.setString(2, b.getBranch());
            
            if(!b.getLogo_big().isEmpty())
            {
                File logo_big = new File(b.getLogo_big());
                inputStreamBig = new FileInputStream(logo_big);
                statement.setBinaryStream(3, (InputStream) inputStreamBig, (int)(logo_big.length()));
            }
            else
                statement.setNull(3, Types.NULL);
            
            if(!b.getLogo_small().isEmpty())
            {
                File logo_small = new File(b.getLogo_small());
                inputStreamSmall = new FileInputStream(logo_small);
                statement.setBinaryStream(4, (InputStream) inputStreamSmall, (int)(logo_small.length()));
            }
            else
                statement.setNull(4, Types.NULL);
            
            statement.setString(5, b.getEmail());
            statement.setString(6, b.getFone());
 
            statement.executeUpdate();
            statement.close();
            result = true;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
        return result;
    }
    
    public SystemData getSystemData()
    {
        ResultSet rs = null;
        SystemData result = null;
        
        try 
        {
           rs = Banco.getCon().consultar("SELECT * FROM system");
           
           if(rs.next())
               result = new SystemData(rs.getString("software_name"), rs.getString("branch"), rs.getString("email"), rs.getString("fone"), rs.getBytes("logo_big"), rs.getBytes("logo_small"));
           
        }
        catch ( Exception ex )
        { 
            
        }
        
        return result;
    }
}
