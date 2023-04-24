package io.github.mdb.repositores;

import com.mongodb.client.MongoCollection;
import io.github.mdb.access.impl.MongoAccess;
import lombok.Getter;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Repository<T> {

    private final MongoAccess access;
    private final String databaseName;
    private final String collectionName;
    private final Class<T> clazz;

    public Repository(MongoAccess access, String databaseName, String collectionName, Class<T> clazz) {
        this.access = access;
        this.databaseName = databaseName;
        this.collectionName = collectionName;
        this.clazz = clazz;
    }

    public T insert(T object) {
        this.getCollection().insertOne(object);
        return object;
    }

    public T updateOne(T object, Bson filter) {
        this.getCollection().replaceOne(filter, object);
        return object;
    }

    @SafeVarargs
    public final void insert(T... objects) {
        this.getCollection().insertMany(List.of(objects));
    }

    public List<T> find(Bson filter) {
        return this.getCollection()
                .find(filter)
                .into(new ArrayList<>());
    }

    public List<T> find(Bson filter, int limit) {
        return this.getCollection()
                .find(filter)
                .limit(limit)
                .into(new ArrayList<>());
    }

    public T findOne(Bson filter) {
        return this.getCollection()
                .find(filter)
                .first();
    }

    public List<T> findAll() {
        return this.getCollection()
                .find()
                .into(new ArrayList<>());
    }

    public void deleteOne(Bson filter) {
        this.getCollection().deleteOne(filter);
    }

    public void deleteMany(Bson filter) {
        this.getCollection().deleteMany(filter);
    }

    public void clear() {
       this.getCollection().deleteMany(new Document());
    }

    public MongoCollection<T> getCollection() {
        return this.access.getDatabase(this.databaseName).getCollection(this.collectionName, this.clazz);
    }
}
