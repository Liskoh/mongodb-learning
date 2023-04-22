package io.github.mdb.access.impl;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import io.github.mdb.access.IAccess;

@Getter
public class MongoAccess implements IAccess<MongoClient> {

    private final ServerAddress address;
    private final MongoCredential credential;
    private final MongoClientOptions options;
    private MongoClient client;

    public MongoAccess(ServerAddress address, MongoCredential credential, MongoClientOptions options) {
        this.address = address;
        this.credential = credential;
        this.options = options;

        this.client = this.connect();
    }

    @Override
    public MongoClient connect() {
        return new MongoClient(this.address, this.credential, this.options);
    }

    @Override
    public MongoClient get() {
        if (this.client == null)
            this.client = this.connect();

        return client;
    }

    @Override
    public void close() {
        if (this.client != null)
            this.client.close();
    }

    public MongoDatabase getDatabase(String databaseName) {
        return this.get().getDatabase(databaseName);
    }

}
