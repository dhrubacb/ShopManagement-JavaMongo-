package controller;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import java.io.IOException;

/**
 * Created by Xenon on 3/15/2017.
 */
public class Connector {

    MongoDatabase db;

    public void connect()
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("shopmanagement");
        System.out.println("connected");
    }

    public void insert(Document doc)
    {
        MongoCollection<Document> booklist = db.getCollection("sellmemo");
        booklist.insertOne(doc);
    }

    public void delete(String attr, Object val)
    {
        MongoCollection<Document> booklist = db.getCollection("sellmemo");
        booklist.deleteOne(eq(attr, val));
    }

    public void update(String attr, Object val, Document doc)
    {
        MongoCollection<Document> booklist = db.getCollection("sellmemo");
        booklist.updateOne(eq(attr, val), new Document("$set", doc));       
    }
    
    public MongoCollection<Document> getData()
    {
        return db.getCollection("sellmemo");
    }
    
    public int getIndex()
    {
        Document doc = new Document();
        MongoCollection<Document> col = db.getCollection("sellmemo");
        if(col.count()<=0) return 0;
        for(Document d: col.find()) doc = d;
        int j =(int) doc.get("itemId");
        return j+1;
    }
    
    
}
