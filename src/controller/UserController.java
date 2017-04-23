/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
/**
 *
 * @author Dhruba
 */
public class UserController {
     MongoDatabase db;

    public void connect()
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("shopmanagement");
        System.out.println("connected");
    }

    public void insert(Document doc)
    {
        MongoCollection<Document> mongoCol = db.getCollection("user");
        mongoCol.insertOne(doc);
    }

    public void delete(String attr, Object val)
    {
        MongoCollection<Document> mongoCol = db.getCollection("user");
        mongoCol.deleteOne(eq(attr, val));
    }

       public String find(String val)
    {
        MongoCollection<Document> mongoCol = db.getCollection("user");
        FindIterable<Document> res =  mongoCol.find( eq("Item Name" , val) );   
        String attr =  res.first().get("Price", String.class);
        return attr;
    }
         public String quantity(String val)
    {
        MongoCollection<Document> mongoCol = db.getCollection("user");
        FindIterable<Document> res =  mongoCol.find( eq("Item Name" , val) );   
        String attr =  res.first().get("Quantity", String.class);
        return attr;
    }
          public int index(String val)
    {
        MongoCollection<Document> mongoCol = db.getCollection("user");
        FindIterable<Document> res =  mongoCol.find( eq("Item Name" , val) );   
        int attr =  res.first().get("itemId", Integer.class);
        return attr;
    }
    public void update(String attr, Object val, Document doc)
    {
        MongoCollection<Document> mongoCol = db.getCollection("user");
        mongoCol.updateOne(eq(attr, val), new Document("$set", doc));       
    }
    public void update(String attr, Object val, String doc, String vale)
    {
        MongoCollection<Document> mongoCol = db.getCollection("user");
        mongoCol.updateOne(eq(attr, val), new Document("$set", new Document(doc,vale)));       
    }
    
    public MongoCollection<Document> getData()
    {
        return db.getCollection("user");
    }
    
    public int getIndex()
    {
        Document doc = new Document();
        MongoCollection<Document> col = db.getCollection("user");
        if(col.count()<=0) return 0;
         return (int) col.count();
//        for(Document d: col.find()) doc = d;
//        int j =(int) doc.get("itemId");
//        return j+1;
    }

    public boolean search(String text) throws NullPointerException {
        MongoCollection<Document> mongoCol = db.getCollection("user");
        try (MongoCursor<Document> cursor = mongoCol.find(eq("handle" , text)).iterator()) {
            return !cursor.hasNext();
        }
    }
        public boolean logFind(String val, String pass)
    {
        boolean cond=false;
        MongoCollection<Document> mongoCol = db.getCollection("user");
         try (MongoCursor<Document> cursor = mongoCol.find(eq("handle" , val)).iterator()) {
            if(cursor.hasNext()){
        
        FindIterable<Document> res =  mongoCol.find( eq("handle" , val) );   
        String attr =  res.first().get("password", String.class);
        if(attr.equals(pass)){
        cond = true;
               }
             }
         }
         return cond;
    }
}