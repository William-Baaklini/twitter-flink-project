
package twitter_streaming;

import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;

import java.util.Properties;

public class StreamingJob {

	private static final String API_key = "yimjCtrm5I6T0UM29CLxHy2g1";
	private static final String API_secret = "t0s0sHxMzPTDa9qmre3u12aChoC0xBKsED4ttkbXWre7Ialwl3";
	private static final String Access_Token = "1729438742094524416-PJTiRXHZkpH23gUnB7pz8MQLMO0KSd";
	private static final String Access_Token_secret = "BScDth9Hio1fUTtRxbJrKmifEcD3TWDCxFO8DAi6g1HFz";


	public static void main(String[] args) throws Exception {

		// set up the streaming execution environment
//		Configuration config = new Configuration();
//		config.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER, true);
//		final StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(config);
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		env.setMaxParallelism(1);

		Properties props = new Properties();
		props.setProperty(TwitterSource.CONSUMER_KEY, API_key);
		props.setProperty(TwitterSource.CONSUMER_SECRET, API_secret);
		props.setProperty(TwitterSource.TOKEN, Access_Token);
		props.setProperty(TwitterSource.TOKEN_SECRET, Access_Token_secret);
		DataStream<String> tweetStream = env.addSource(new TwitterSource(props));

		tweetStream.print();

		// execute program
		env.execute("Flink Streaming Java API Skeleton");
	}
}
