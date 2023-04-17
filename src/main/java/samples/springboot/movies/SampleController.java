package samples.springboot;

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.BSONObject;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


@RestController
public class SampleController {

	private MongoCollection<Document> getMongoMovieCollection(){
		//MongoClient mongo = new MongoClient( "host.docker.internal" , 27017 );
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
		
		MongoDatabase database = mongo.getDatabase("mahyardb");
		
		return database.getCollection("movies");
		
	}
	
    @GetMapping("/")
	public String homePage() {
    	return "Welcome!<br/>"+
    			"<a href=\"/movies\">Show Movies</a><br/>"+
    			"<a href=\"/movie-entry\">Add a movie</a><br/>";
    			
	}

    @GetMapping("/movies")
	public ArrayList<Movie> showMovies() {
    	
    	MongoCollection<Document> collection = getMongoMovieCollection(); 

		ArrayList<Movie> movies = new ArrayList<>();
		
		try (MongoCursor<Document> cur = collection.find().iterator()) {

            while (cur.hasNext()) {

                var doc = cur.next();
                
                var curMovie = new ArrayList<>(doc.values());
                movies.add(new Movie(curMovie.get(1).toString(), curMovie.get(2).toString()));
                
            }
        }
		
		return movies;
            
	}        

    
    @GetMapping("/movie-entry")
	public String movieEntry() {
    	
    	String outputHTML = 
			"<p>\n"+ 
		        
		        "title:<br/><input type=\"text\" id=\"title\"><br/>\n"+
		        "director:<br/><input type=\"text\" id=\"director\"><br/>\n"+
		        "<button onclick=\"sendJSON()\">Send</button><br/>\n"+
		        "<p class=\"result\" style=\"color:green\"></p>\n"+
		     
		   "</p>\n"+    	 
   
			"<script>\n"+
				"function sendJSON(){\n"+
		            
		            "let result = document.querySelector('.result');\n"+
		            "let title = document.querySelector('#title');\n"+
		            "let director = document.querySelector('#director');\n"+
		            
		            "let xhr = new XMLHttpRequest();\n"+
		            "let url = \"insertmovie\";\n"+
		            
		            "xhr.open(\"POST\", url, true);\n"+
		            "xhr.setRequestHeader(\"Content-Type\", \"application/json\");\n"+
		            
		            "xhr.onreadystatechange = function () {\n"+
		                "if (xhr.readyState === 4 && xhr.status === 200) {\n"+
		                 
		                    "result.innerHTML = this.responseText;}};\n"+
		 
		            "var data = JSON.stringify({ \"title\": title.value, \"director\": director.value });\n"+
		 
		            "xhr.send(data);}\n"+
	    	"</script>";

   
    	return outputHTML;
    }
    
    @PostMapping("/insertmovie")
    public ResponseEntity<String> insertMovie(@RequestBody Movie movie) {
    	
    	MongoCollection<Document> collection = getMongoMovieCollection();
			
		Document newMovie = new Document();
		newMovie.append("title", movie.getTitle());
		newMovie.append("director", movie.getDirector());
    	
		collection.insertOne(newMovie);
		    	
    	return ResponseEntity.status(HttpStatus.OK).body("inserted suceessfully!");    	
    }
    
}
