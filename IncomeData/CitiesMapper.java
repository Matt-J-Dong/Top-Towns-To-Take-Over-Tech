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
        String[] cities = {"Austin","New York","Seattle","San Diego","Houston","Boston"};//could convert to list to use a different method
        for (String city:cities){
            if(city.equals(str[0])){
                String latlong = ","+str[6]+","+str[7];
                context.write(new Text(str[0]),new Text(latlong));
            }
        }
      }
    }