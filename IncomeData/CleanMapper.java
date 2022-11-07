import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.*;

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
public class CleanMapper extends Mapper<Object, Text, Text, Text>{
    private final static IntWritable one = new IntWritable(1);
    private final static IntWritable zero = new IntWritable(0);
    private Text word = new Text();
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String numbers[] = {"1","2","3","4","5","6","7","8","9","0"};
        String str[] = value.toString().split(",");
        String holder = value.toString();
        String cleaned = "";
        boolean middle = false;
        String merge1 = "";
        String merge2 = "";
        // Regex to check string
        // contains only digits
        String regex = "[0-9]+";
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
        for (int i=0;i<str.length;i++){
            if(str[i].substring(0,1).equals("\"")){
                str[i]=str[i].substring(1,str[i].length());
            }
            if(str[i].substring(str[i].length()-1,str[i].length()).equals("\"")){
                str[i]=str[i].substring(0,str[i].length()-1);
            }
            //for (String num : numbers){
                //if left side contains no numbers and right side contains only numbers, keep comma
                //else remove comma
            //}
        }
        for(int i=0;i<str.length-1;i++){
            if(!p.matcher(str[i]).matches()&&!p.matcher(str[i+1]).matches()){
                merge1 = str[i]+str[i+1]+", ";
                i++;
            }
            else if(p.matcher(str[i]).matches()&&p.matcher(str[i+1]).matches()){
                merge2 = str[i]+str[i+1];
                i++;
            }
            else{
                System.out.println("Why are you here");
            }
        }
        if(!merge1.equals("") && !merge2.equals("")){
            context.write(new Text(merge1),new Text(merge2));
        }
        //}
        // Text word = new Text(holder);
        // context.write(word,one);
      }
    }