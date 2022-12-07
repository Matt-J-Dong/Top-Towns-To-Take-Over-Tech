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
public class CleanGeoMapper extends Mapper<Object, Text, Text, Text>{
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String str[] = value.toString().split(","); //length 17
        String noQuotes1 = str[0].substring(1,str[0].length()-1);
        String noQuotes2 = str[6].substring(1,str[6].length()-1);
        String noQuotes3 = str[7].substring(1,str[7].length()-1);
        String noQuotes4 = str[2].substring(1,str[2].length()-1);
        if(noQuotes1.equals("city")){
            noQuotes4=noQuotes4.substring(0,noQuotes4.length()-3);
            context.write(new Text(noQuotes1+","+noQuotes4+","),new Text(noQuotes2+","+noQuotes3));
        }
        else{
            String location = "\""+ noQuotes1 + ", "+noQuotes4 + "\","+noQuotes4;
            String latlong = ","+noQuotes2+","+noQuotes3;
            context.write(new Text(location),new Text(latlong));
        }
      }
    }
