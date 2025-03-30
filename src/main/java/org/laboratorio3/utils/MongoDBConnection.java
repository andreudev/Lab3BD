package org.laboratorio3.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.internal.MongoClientImpl;

public class MongoDBConnection {
    private static String URI = "mongodb://root:123456@localhost:27018/";
    private static String DATABASE_NAME = "hospitalDB";
    private static MongoDBConnection instance;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private MongoDBConnection() {
        try {
            mongoClient = MongoClients.create(URI);
            database = mongoClient.getDatabase(DATABASE_NAME);
            System.out.println("✅ Conexión establecida con MongoDB");
        } catch (Exception e) {
            System.err.println("❌ Error en la conexión: " + e.getMessage());
        }
    }

    public static MongoDBConnection getInstance() {
        if (instance == null) {
            instance = new MongoDBConnection();
        }
        return instance;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

}
