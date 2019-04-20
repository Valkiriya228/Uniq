package com.Valkiriya228.uniq;

import static com.Valkiriya228.uniq.Main.main;
import static org.junit.Assert.assertEquals;

public class Tests {
    public static void testmain() {
        String[] args = {"-i", "-u", "-o", "src/main/java/expected1.txt", "src/main/java/input1.txt"};
        main(args);
        assertEquals("src/main/java/expected1.txt", "src/main/java/actual.txt");
    }

}
