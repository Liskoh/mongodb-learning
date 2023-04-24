package io.github.mdb.objects.datas.impl;

import com.mongodb.client.model.Filters;
import io.github.mdb.objects.Biography;
import io.github.mdb.objects.datas.IData;
import io.github.mdb.repositores.Repository;
import lombok.*;
import org.bson.conversions.Bson;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User implements IData<User> {

    private String uuid;
    private String username;
    private String email;
    private Biography biography;

    public User(String username, String email) {
        this.uuid = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.biography = new Biography(this.username + "'s biography",
                "This is " + this.username + "'s biography.");
    }

    @Override
    public User save(Repository<User> repository) {
        return repository.insert(this);
    }

    @Override
    public User update(Repository<User> repository) {
        final Bson filter = Filters.eq("uuid", this.uuid);

        return repository.updateOne(this, filter);
    }

    @Override
    public void delete(Repository<User> repository) {
        final Bson filter = Filters.eq("uuid", this.uuid);

        repository.deleteOne(filter);
    }
}
