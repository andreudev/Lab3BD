package org.laboratorio3.Repository;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.laboratorio3.enums.BloodType;
import org.laboratorio3.enums.Gender;
import org.laboratorio3.models.Patient;
import org.laboratorio3.utils.MongoDBConnection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientRepositoryMongoImpl implements PatientRepository<Document> {

    private final MongoCollection<Document> collection;

    public PatientRepositoryMongoImpl() {
        this.collection = MongoDBConnection.getInstance().getDatabase().getCollection("patients");
    }

    @Override
    public void create(Document document) {
        collection.insertOne(document);
    }

    @Override
    public void update(Document document) {
        ObjectId id = document.getObjectId("_id");
        collection.updateOne(Filters.eq("_id", id), new Document("$set", document));
    }

    @Override
    public void delete(Long id) {
        collection.deleteOne(Filters.eq("_id", new ObjectId(id.toString())));
    }

    public void delete(Document document) {
        collection.deleteOne(document);
    }

    @Override
    public Document read(Document document) {
        return collection.find(document).first();
    }

    @Override
    public Document readById(int id) {
        return collection.find(Filters.eq("_id", new ObjectId(String.valueOf(id)))).first();
    }

    @Override
    public List<Document> readAll() {
        List<Document> documents = new ArrayList<>();
        collection.find().iterator().forEachRemaining(documents::add);
        return documents;
    }

    public static void main(String[] args) {

        PatientRepositoryMongoImpl repository = new PatientRepositoryMongoImpl();

        // Create a new patient
        Patient patient = new Patient("John", "Doe", new Date(), "123 Main St", Gender.MALE, BloodType.O_POSITIVE);
        Document patientDoc = new Document("firstName", patient.getFirstName())
                .append("lastName", patient.getLastName())
                .append("dateBirth", patient.getDateBirth())
                .append("address", patient.getAddress())
                .append("gender", patient.getGender().toString())
                .append("bloodType", patient.getBloodType().toString());

        repository.create(patientDoc);

        System.out.println("Patients:");
        List<Document> patients = repository.readAll();
        patients.forEach(System.out::println);

        patientDoc.append("address", "456 Elm St");
        repository.update(patientDoc);

        repository.delete(patientDoc);

        System.out.println("Patients after update:");
        patients = repository.readAll();
        patients.forEach(System.out::println);
    }
}