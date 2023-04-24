package io.github.mdb.repositores.impl;

import com.mongodb.client.model.Filters;
import io.github.mdb.access.impl.MongoAccess;
import io.github.mdb.objects.datas.impl.User;
import io.github.mdb.repositores.Repository;
import org.bson.conversions.Bson;

import java.util.Optional;
import java.util.UUID;

public class UserRepository extends Repository<User> {
    public UserRepository(MongoAccess access, String databaseName, String collectionName, Class<User> clazz) {
        super(access, databaseName, collectionName, clazz);
    }

    public Optional<User> findOneByUsername(String username) {
        final Bson filter = Filters.eq("username", "default");

        return Optional.ofNullable(this.findOne(filter));
    }

    public Optional<User> findOneByUuid(UUID uuid) {
        final Bson filter = Filters.eq("uuid", uuid);

        return Optional.ofNullable(this.findOne(filter));
    }
}
