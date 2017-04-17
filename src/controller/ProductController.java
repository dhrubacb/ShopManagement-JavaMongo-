/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

/**
 *
 * @author Dhruba
 */
public class ProductController {
    
    MongoDatabase db;

    public void connect()
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("shopmanagement");
        System.out.println("connected");
    }

    public void insert(Document doc)
    {
        MongoCollection<Document> booklist = db.getCollection("product");
        booklist.insertOne(doc);
    }

    public void delete(String attr, Object val)
    {
        MongoCollection<Document> booklist = db.getCollection("product");
        booklist.deleteOne(eq(attr, val));
    }

       public String find(String val)
    {
        MongoCollection<Document> booklist = db.getCollection("product");
        FindIterable<Document> res =  booklist.find( eq("Item Name" , val) );   
        String attr =  res.first().get("Price", String.class);
        return attr;
    }
         public String quantity(String val)
    {
        MongoCollection<Document> booklist = db.getCollection("product");
        FindIterable<Document> res =  booklist.find( eq("Item Name" , val) );   
        String attr =  res.first().get("Quantity", String.class);
        return attr;
    }
          public int index(String val)
    {
        MongoCollection<Document> booklist = db.getCollection("product");
        FindIterable<Document> res =  booklist.find( eq("Item Name" , val) );   
        int attr =  res.first().get("itemId", Integer.class);
        return attr;
    }
    public void update(String attr, Object val, Document doc)
    {
        MongoCollection<Document> booklist = db.getCollection("product");
        booklist.updateOne(eq(attr, val), new Document("$set", doc));       
    }
    public void update(String attr, Object val, String doc, String vale)
    {
        MongoCollection<Document> booklist = db.getCollection("product");
        booklist.updateOne(eq(attr, val), new Document("$set", new Document(doc,vale)));       
    }
    
    public MongoCollection<Document> getData()
    {
        return db.getCollection("product");
    }
    
    public int getIndex()
    {
        Document doc = new Document();
        MongoCollection<Document> col = db.getCollection("product");
        if(col.count()<=0) return 0;
        for(Document d: col.find()) doc = d;
        int j =(int) doc.get("itemId");
        return j+1;
    }
    
}
