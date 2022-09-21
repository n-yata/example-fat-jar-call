package example.micronaut;

import jakarta.inject.Singleton;

@Singleton
public class Utils {

    public String call() {
        return "hello layers";
    }
}
