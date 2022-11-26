import java.beans.Visibility;
import java.util.*;



public class cleanHelper{
    public static void main(String[] args) throws Exception { 
        
        // String row = "\"Ann Arbor, MI\",2020-10-15,63.4,42.8,53.5,63.4,40.8,52.8,41.3,63.9,0.01,100,4.17,rain,0,0,31.5,16.9,253.8,1008.7,78.4,9.9,24.3,2.2,1,,2020-10-15T07:48:20,2020-10-15T18:52:15,0.99,\"Rain, Partially cloudy\",Partly cloudy throughout the day with morning rain.,rain,\"72537094847,72537614853,F7404,KARB,KYIP,72537494889,F1648,KDTW\"";
        String row = "name,datetime,tempmax,tempmin,temp,feelslikemax,feelslikemin,feelslike,dew,humidity,precip,precipprob,precipcover,preciptype,snow,snowdepth,windgust,windspeed,winddir,sealevelpressure,cloudcover,visibility,solarradiation,solarenergy,uvindex,severerisk,sunrise,sunset,moonphase,conditions,description,icon,stations";
        String[] arrayRow = row.split(",");
        // sometimes the state is appended after the city name
        int shift = 0;
        System.out.println(arrayRow[0].length());

        if(!arrayRow[0].equals("name") && arrayRow[1].charAt(0)!='2'){
            arrayRow[0] = arrayRow[0].substring(1);
            shift = 1;
        }
        System.out.println(arrayRow[0]);
        System.out.println(arrayRow[1]);
        
        int CITYNAME = 0; int DATETIME = 1; int FEELTEMP = 7; int HUMIDITY = 9; int PRECIP = 10; int PRECIPCOVER = 12;
        int WINDSPEED = 17; int VISIBILITY = 21; int UVINDEX = 24;

        int[] ADDCOL = {DATETIME, FEELTEMP, HUMIDITY, PRECIP, PRECIPCOVER, WINDSPEED, VISIBILITY, UVINDEX};
        List<String> newRow = new ArrayList<String>();
        newRow.add(arrayRow[CITYNAME]);
        for (int i=0; i<ADDCOL.length; i++){
            newRow.add(arrayRow[ADDCOL[i]+shift]);
        }
        String filteredRow = String.join(",",newRow);
        System.out.println(filteredRow);
    }
    
}
