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

    @SafeVarargs
    public final void insert(T... objects) {
        this.getCollection().insertMany(List.of(objects));
    }

    public List<T> find(Bson filter) {
        return this.getCollection()
                .find(filter)
                .into(new ArrayList<>());
    }

    public List<T> findAll() {
        return this.getCollection()
                .find()
                .into(new ArrayList<>());
    }

    public void delete(Bson filter) {
        this.getCollection().deleteMany(filter);
    }

    public void clear() {
       this.getCollection().deleteMany(new Document());
    }

    public MongoCollection<T> getCollection() {
        return this.access.getDatabase(this.databaseName).getCollection(this.collectionName, this.clazz);
    }
}
