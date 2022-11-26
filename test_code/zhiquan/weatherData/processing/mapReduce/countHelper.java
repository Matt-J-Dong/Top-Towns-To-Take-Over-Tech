
import java.beans.Visibility;
import java.util.*;



public class countHelper{

    static int CITYNAME = 0; static int DATETIME = 1; static int FEELTEMP = 2; static int HUMIDITY = 3; static int PRECIP = 4; static int PRECIPCOVER = 5;
    static int WINDSPEED = 6; static int VISIBILITY = 7; static int UVINDEX = 8;
    public static boolean isGoodDay(String[] row){
        try{
          System.out.println(row[FEELTEMP]);

        double feelTemp = Double.parseDouble(row[FEELTEMP]);
        System.out.println(""+feelTemp);

        double humidity = Double.parseDouble(row[HUMIDITY]);
        double precip = Double.parseDouble(row[PRECIP]);
        double precipCover = Double.parseDouble(row[PRECIPCOVER]);
        double windSpeed = Double.parseDouble(row[WINDSPEED]);
        double visibility = Double.parseDouble(row[VISIBILITY]);
        double uv = Double.parseDouble(row[UVINDEX]);

        boolean goodTemp = 60<feelTemp&&feelTemp<80; if (!goodTemp){System.out.println("not good temperature:"+feelTemp);}
        boolean goodHumidity = 40<humidity&&humidity<80; if (!goodHumidity){System.out.println("not good humidity:"+humidity);}
        boolean goodPrecipitation = (precip*precipCover)/100<0.06; if (!goodPrecipitation){System.out.println("not good precipitation:"+(precip*precipCover)/100);}
        boolean goodWind = windSpeed<22; if (!goodWind){System.out.println("not good Wind: "+windSpeed);}
        boolean goodVisibility = visibility >= 9; if (!goodVisibility){System.out.println("not good visibility:"+visibility);}
        boolean goodUV = uv < 10; if (!goodUV){System.out.println("not good uv:"+uv);}
        
        return goodTemp&&goodHumidity&&goodPrecipitation&&goodWind&&goodVisibility&&goodUV;

        }catch (NumberFormatException exception){
          System.out.println("not a good line");

          return false;
        }
        
    }


    public static void main(String[] args) throws Exception { 
        
        String row = "\"Ithaca, NY\",-0.4,12.3,83.4,0,0.4,23,92.8,2.2";
        String[] arrayRow = row.split(",");
        String city = arrayRow[0];
        String year = arrayRow[1].substring(0, 4);

        System.out.println("The number of good days in "+city+" in "+year+" is ");
        System.out.println(isGoodDay(arrayRow));

    }

}
