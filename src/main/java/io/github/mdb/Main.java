package io.github.mdb;

import com.mongodb.*;
import io.github.mdb.access.impl.MongoAccess;
import io.github.mdb.consts.ConstCredentials;
import io.github.mdb.objects.Biography;
import io.github.mdb.repositores.impl.UserRepository;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import io.github.mdb.objects.datas.impl.User;

import java.util.Optional;

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
        Optional<User> optionalUser = repository.findOneByUsername("default");

        if (optionalUser.isPresent()) {
            final User findedUser = optionalUser.get();
            Biography biography = findedUser.getBiography();

            System.out.println("default biography: " + biography);

            //update biography
            biography.setBiographyName("default biography");
            biography.setBiographyDescription("description.");
            biography.getBiographyData().clear();
            biography.getBiographyData().put("key", "value");

            //update user
            findedUser.update(repository);
        }

        //check if biography was updated
        optionalUser = repository.findOneByUsername("default");

        if (optionalUser.isPresent()) {
            final User findedUser = optionalUser.get();
            Biography biography = findedUser.getBiography();

            System.out.println("edited biography: " + biography);
        }


        //close connection
        access.close();
    }
}