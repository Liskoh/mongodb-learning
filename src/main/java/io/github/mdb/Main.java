package io.github.mdb;

import com.mongodb.*;
import com.mongodb.client.model.Filters;
import io.github.mdb.access.impl.MongoAccess;
import io.github.mdb.consts.ConstCredentials;
import io.github.mdb.repositores.impl.UserRepository;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import io.github.mdb.objects.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Hostname, Port, Username, Password, DatabaseName, CollectionName
        final ServerAddress address = new ServerAddress(ConstCredentials.HOST, ConstCredentials.PORT);
        final MongoCredential credential = MongoCredential.createCredential(ConstCredentials.USERNAME, ConstCredentials.DATABASE_NAME, ConstCredentials.PASSWORD);

        //Codec for POJO and serialization
        final CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        //Options for connection
        final MongoClientOptions options = MongoClientOptions.builder()
                .codecRegistry(codecRegistry)
                .build();

        //Database connection
        final MongoAccess access = new MongoAccess(address, credential, options);

        //repository for User
        final UserRepository repository = new UserRepository(access, ConstCredentials.DATABASE_NAME, ConstCredentials.COLLECTION_NAME, User.class);

        //clear collection
        repository.clear();

        //POJO to insert in database
        final User user = new User("default", "default@default.com");
        final User secondUser = new User("empty", "em");

        //insert user in database
        repository.insert(user, secondUser);

        //find user in database
        final List<User> users = repository.find(Filters.eq("username", "default"));

        //print users
        users.forEach(System.out::println);

        //close connection
        access.close();
    }
}