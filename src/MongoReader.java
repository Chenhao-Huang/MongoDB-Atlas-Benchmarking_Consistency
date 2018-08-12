import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.bson.Document;

public class MongoReader {
	//input parameters
	public static int numberOfIteration = 50000;
	//public static int numberOfIteration = 500;
	//public static int sleepTimeMilli = 100;
	public static MongoClientURI uri = new MongoClientURI(
			"");
	
	//public static MongoClientURI uri = new MongoClientURI(
	//    "mongodb://localhost");

	public static void main(String argv[]) {
		String dbName = "MongodbAtlas-Consistency-Benchmark";
		String collectionName = "demo01";
		
		//output file name
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String outputFileName = "Result_Reading_" + sdf.format(timestamp) + ".txt";
		
		//Connecting to mongoDB, reading and writing
		try (
			MongoClient mongoClient = new MongoClient(uri);
			FileWriter fw = new FileWriter(outputFileName, true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw)) 
		{
			MongoDatabase db = mongoClient.getDatabase(dbName);
			MongoCollection coll = db.getCollection(collectionName);

			for (int i = 1; i <= numberOfIteration; i++) {
				query(coll, out);
				//TimeUnit.MILLISECONDS.sleep(sleepTimeMilli);
			}
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void query(MongoCollection coll, PrintWriter out) throws Exception {
		long NanoStart = System.nanoTime();	
		out.print("read\t");
		out.print(NanoStart);
		
		//Simple query
		Document criteria = new Document("_id", 2);
		FindIterable<Document> iterable = coll.find(criteria);
		out.print("\t");

		//Document result = (Document) iterable.first();
		for (Document result: iterable) {
			out.print(result);
		}
		
		long NanoEnd = System.nanoTime();
		out.print("\t");
		out.print(NanoEnd);
		out.println();
	}
}
