import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.NullWritable; 
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;




public class CleanMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        
        String row = value.toString();
        String[] arrayRow = row.split(",");
        // sometimes the state is appended after the city name
        int shift = 0;
        if(!arrayRow[0].equals("name") && arrayRow[1].charAt(0)!='2'){
            arrayRow[0] = arrayRow[0].substring(1);
            shift = 1;
        }
        int CITYNAME = 0; int DATETIME = 1; int FEELTEMP = 7; int HUMIDITY = 9; int PRECIP = 10; int PRECIPCOVER = 12;
        int WINDSPEED = 17; int VISIBILITY = 21; int UVINDEX = 24;

        int[] ADDCOL = {DATETIME, FEELTEMP, HUMIDITY, PRECIP, PRECIPCOVER, WINDSPEED, VISIBILITY, UVINDEX};
        List<String> newRow = new ArrayList<String>();
        newRow.add(arrayRow[CITYNAME]);
        for (int i=0; i<ADDCOL.length; i++){
            newRow.add(arrayRow[ADDCOL[i]+shift]);
        }
        String filteredRow = String.join(",",newRow);
        context.write(new Text(filteredRow), NullWritable.get());
    }
} 
