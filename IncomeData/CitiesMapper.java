import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

@SuppressWarnings("all")
public class CitiesMapper extends Mapper<Object, Text, Text, Text>{
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String str[] = value.toString().split(","); //length 17
        String noQuotes1 = str[0].substring(1,str[0].length()-1);
        String noQuotes2 = str[6].substring(1,str[6].length()-1);
        String noQuotes3 = str[7].substring(1,str[7].length()-1);
        String noQuotes4 = str[2].substring(1,str[2].length()-1);
        String[] cities = {"Austin","New York","Seattle","San Diego","Houston","Boston"};//could convert to list to use a different method
        String[] states = {"TX","NY","WA","CA","TX","MA"};
        for (int i =0;i<cities.length;i++){
            if(cities[i].equals(noQuotes1) && states[i].equals(noQuotes4)){
                String location = noQuotes1 + ", "+noQuotes4;
                String latlong = ","+noQuotes2+","+noQuotes3;
                context.write(new Text(location),new Text(latlong));
            }
        }
      }
    }