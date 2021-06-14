package sisdentalfx.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;
import sun.misc.BASE64Encoder;

public class AcessoCEP {

    public static String consultaCep(String cep, String formato)
    {
        String username = "seu login";
        String password = "sua senha";
        String proxyHost = "177.131.35.1";
        String proxyPort = "3128";

        StringBuffer dados = new StringBuffer();
        try {
            //URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato"+formato);
            URL url = new URL("http://apps.widenet.com.br/busca-cep/api/cep."+formato+"?code="+cep);
                        
            //String userpass = username + ":" + password;
            //System.setProperty("http.proxyHost", proxyHost);
            //System.setProperty("http.proxyPort", proxyPort);
            URLConnection con = url.openConnection();
            con.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            //String encodedLogin = new BASE64Encoder().encode(userpass.getBytes());
            //con.setRequestProperty("Proxy-Authorization", "Basic " + encodedLogin);
            //con.setDoInput(true);
            //con.setDoOutput(true);
            //con.setAllowUserInteraction(false);
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String s = "";
            while (null != (s = br.readLine()))
                 dados.append(s);
            br.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dados.toString();
    }
    /*public static void main(String[] args) {
        String str=consultaCep("19053300", "json");
        System.out.println(str);
        JSONObject my_obj = new JSONObject(str);
        System.out.println(my_obj.getString("city"));
        System.out.println(my_obj.getString("district"));
        
    }*/
    
}
