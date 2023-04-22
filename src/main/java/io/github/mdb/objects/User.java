package io.github.mdb.objects;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    public User(String username, String email) {
        this.uuid = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
    }

    private String uuid;
    private String username;
    private String email;
}
