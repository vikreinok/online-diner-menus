package ee.ttu.catering.cloudservice;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

public class MongoDbConnctor {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public MongoDbConnctor() {
	}

	public DB connection() {
		MongoCredential credential = MongoCredential.createCredential("test", "catering", "test".toCharArray());
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(new ServerAddress("ds033831.mongolab.com", 33831), Arrays.asList(credential));
		} catch (UnknownHostException e) {
			logger.error("Problem with connection to MongoDB ", e);
		}
		
		mongoClient.setWriteConcern(WriteConcern.JOURNALED);
		DB db = mongoClient.getDB("catering");
		return db;
	}
	
}
