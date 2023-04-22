package io.github.mdb.repositores.impl;

import io.github.mdb.access.impl.MongoAccess;
import io.github.mdb.objects.User;
import io.github.mdb.repositores.Repository;

public class UserRepository extends Repository<User> {
    public UserRepository(MongoAccess access, String databaseName, String collectionName, Class<User> clazz) {
        super(access, databaseName, collectionName, clazz);
    }
}
