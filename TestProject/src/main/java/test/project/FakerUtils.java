package test.companyName.api.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName(){
        Faker faker = new Faker();
        return "Playlist " + faker.regexify("[A-Za-z0-9 ,_-]{10}");
    }

    public static String generateDescription(){
        Faker faker = new Faker();
        return "Description " + faker.regexify("[ A-Za-z0-9_@./#&+-]{50}");
    }
    public static String InvalidTenantID(){
        Faker faker = new Faker();
        return faker.regexify("[A-Za-z0-9]{10}");
    }
    public static String generateWAFTraceId(){
        Faker faker = new Faker();
        return "WAFTraceID"+faker.regexify("[A-Za-z0-9]{10}");
    }
}
