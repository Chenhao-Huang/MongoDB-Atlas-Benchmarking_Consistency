import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.bson.Document;

public class MongoWriter{
	//input parameters
	public static int numOfIteration = 29;
    public static int sleepTimeMilli = 3000;
	//public static int numOfIteration = 1;
	//public static int sleepTimeMilli = 0;
	public static MongoClientURI uri = new MongoClientURI(
			"");
	
	public static void main(String argv[]){
		String dbName = "MongodbAtlas-Consistency-Benchmark";
		String collectionName = "demo01";
		
		//output file name
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String outputFileName = "Result_Writing_" + sdf.format(timestamp) + ".txt";
		
		//Connecting to mongoDB, and write
		try (
			MongoClient mongoClient = new MongoClient(uri);
			FileWriter fw = new FileWriter(outputFileName, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw)) 
		{
			MongoDatabase db = mongoClient.getDatabase(dbName);
			MongoCollection coll = db.getCollection(collectionName);
			
			for (int i = 1; i <= numOfIteration; i++) {
				//loadData(coll);
				update(coll, out);
				TimeUnit.MILLISECONDS.sleep(sleepTimeMilli);
			}
			out.close();		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void update(MongoCollection coll, PrintWriter out) throws Exception{
		long NanoStart = System.nanoTime();	
		out.print("write\t");
		out.print(NanoStart);		
		
		BasicDBObject newDocument = new BasicDBObject();
				
		//write value to mongoDB
		newDocument.append("$set", new BasicDBObject().append("Nanoseconds", NanoStart));
		BasicDBObject searchQuery = new BasicDBObject().append("_id", 02);
		coll.updateOne(searchQuery, newDocument);
				
		long NanoEnd = System.nanoTime();
		out.print("\t");
		out.print(NanoEnd);
		out.println();
	}
	
	public static void loadData(MongoCollection coll) throws Exception{
		Document doc;
		String jsonDoc = "{	_id: 2, Nanoseconds: 22,}";
		doc = Document.parse(jsonDoc);
		coll.insertOne(doc);		
	}
}
