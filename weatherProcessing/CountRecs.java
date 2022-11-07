import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat; import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobConf;

public class CountRecs {
    public static void main(String[] args) throws Exception { 
	if (args.length != 2) {
        System.err.println("Usage: MaxTemperature <input path> <output path>");
        System.exit(-1);
    	}
        Job job = new Job(); 
	job.setJarByClass(CountRecs.class); 
	job.setJobName("Count Records");
        job.setNumReduceTasks(1);
	FileInputFormat.addInputPath(job, new Path(args[0])); 
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setMapperClass(CountRecsMapper.class);
        job.setReducerClass(CountRecsReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        // Configuration conf = getConf();
        Configuration configuration=new Configuration();
        JobConf conf=new JobConf(configuration);
        conf.set("mapred.textoutputformat.separator", "\t"); 
        System.exit(job.waitForCompletion(true) ? 0 : 1); }
}
