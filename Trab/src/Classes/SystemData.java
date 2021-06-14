package Classes;

import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;
import static sun.security.krb5.Confounder.bytes;

public class SystemData
{
    private String software_name;
    private String branch;
    private String logo_big;
    private String logo_small;
    private String email;
    private String fone;
    private Image img_big;
    private Image img_small;

    public SystemData(String software_name, String branch,String logo_big, String logo_small, String email, String fone)
    {
        this.software_name = software_name;
        this.branch = branch;
        this.logo_big = logo_big;
        this.logo_small = logo_small;
        this.email = email;
        this.fone = fone;
    }

    public SystemData(String software_name, String branch, String email, String fone, byte[] img_big, byte[] img_small) throws IOException
    {
        BufferedImage img;
        this.software_name = software_name;
        this.branch = branch;
        this.email = email;
        this.fone = fone;
        
        if(img_big != null)
        {
            img = ImageIO.read(new ByteArrayInputStream(img_big));
            this.img_big =  SwingFXUtils.toFXImage(img, null);
        }
        else
            this.img_big = null;
        
        if(img_small != null)
        {
            img = ImageIO.read(new ByteArrayInputStream(img_small));
            this.img_small =  SwingFXUtils.toFXImage(img, null);
        }
        else
            this.img_small = null;
        
    }
    
    public String getSoftware_name()
    {
        return software_name;
    }

    public void setSoftware_name(String software_name)
    {
        this.software_name = software_name;
    }

    public String getBranch()
    {
        return branch;
    }
    
    public void setBranch(String branch)
    {
        this.branch = branch;
    }
    
    public String getLogo_big()
    {
        return logo_big;
    }

    public void setLogo_big(String logo_big)
    {
        this.logo_big = logo_big;
    }

    public String getLogo_small()
    {
        return logo_small;
    }

    public void setLogo_small(String logo_small)
    {
        this.logo_small = logo_small;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFone()
    {
        return fone;
    }

    public void setFone(String fone)
    {
        this.fone = fone;
    }

    public Image getImg_big()
    {
        return img_big;
    }

    public void setImg_big(Image img_big)
    {
        this.img_big = img_big;
    }

    public Image getImg_small()
    {
        return img_small;
    }

    public void setImg_small(Image img_small)
    {
        this.img_small = img_small;
    }
    
    
}
