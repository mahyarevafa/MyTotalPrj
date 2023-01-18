package samples.mongodb;

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDemo {

	public static void main(String[] args) {
		
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
		
		MongoDatabase database = mongo.getDatabase("mahyardb");
		
		MongoCollection<Document> collection = database.getCollection("movies"); 
	    
		FindIterable<Document> iterDoc = collection.find();
		
		//System.out.println(collection.find().first().getString("director")); 
		
		for (String name : database.listCollectionNames()) {

            System.out.println(name);
        }
		
		System.out.println("*******************************");
		
		int i = 1;
		// Getting the iterator
		Iterator it = iterDoc.iterator();
		while (it.hasNext()) {			
			System.out.println(it.next());
			
			i++;
		}
		System.out.println("*******************************");
		
		try (MongoCursor<Document> cur = collection.find().iterator()) {

            while (cur.hasNext()) {

                var doc = cur.next();
                var movies = new ArrayList<>(doc.values());
                

                
            }
        }
		
	}

}
