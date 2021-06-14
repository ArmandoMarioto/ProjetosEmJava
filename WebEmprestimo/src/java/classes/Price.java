package classes;
public class Price {
    public static double calcular(double valor, int meses, double juros)
    {
        return valor*((juros/100)/(1-Math.pow(1+juros/100,-meses)));        
    }
}
