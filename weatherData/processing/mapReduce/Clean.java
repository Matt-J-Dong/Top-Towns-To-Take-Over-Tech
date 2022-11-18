import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable; 
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat; 
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobConf;

public class Clean {
    public static void main(String[] args) throws Exception { 
	if (args.length != 2) {
        System.err.println("Usage: <input path> <output path>");
        System.exit(-1);
    	}
        Job job = new Job(); 
	job.setJarByClass(Clean.class); 
	job.setJobName("Clean the data");
        job.setNumReduceTasks(1);
	FileInputFormat.addInputPath(job, new Path(args[0])); 
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setMapperClass(CleanMapper.class);
        job.setReducerClass(CleanReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        // Configuration conf = getConf();
        Configuration configuration=new Configuration();
        JobConf conf=new JobConf(configuration);
        // conf.setInputFormat(KeyValueTextInputFormat.class);
        // conf.set("key.value.separator.in.input.line", ":::");
        conf.set("mapred.textoutputformat.separator", "\t"); 
        System.exit(job.waitForCompletion(true) ? 0 : 1); }
}
