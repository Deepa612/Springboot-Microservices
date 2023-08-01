package com.telstra.codechallenge;

import com.intuit.karate.junit5.Karate;

public class FunctionalTestRunner {

    @Karate.Test
    Karate testControllers() {
        return Karate.run("gitHubUsers").relativeTo(getClass());
    }

    @Karate.Test
    Karate testMicroservice() {
        return Karate.run("microservice").relativeTo(getClass());
    }
}
