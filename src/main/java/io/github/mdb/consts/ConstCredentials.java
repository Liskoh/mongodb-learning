package io.github.mdb.consts;

import java.util.UUID;

public class ConstCredentials {

    public static final String HOST = "localhost";
    public static final int PORT = 27017;
    public static final String USERNAME = "default";
    public static final char[] PASSWORD = "password".toCharArray();
    public static final String DATABASE_NAME = "matcha";
    public static final String COLLECTION_NAME = "users";
    public static final UUID SECOND_USER_UUID = UUID.fromString("65ae4dd1-fed0-4a70-8f95-a7daf1aed48d");
}
