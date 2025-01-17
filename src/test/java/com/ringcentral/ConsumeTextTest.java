package com.ringcentral;

import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class ConsumeTextTest {
    @Test
    public void updateGlipPostText() throws IOException, RestException {
        RestClient rc = new RestClient(
            System.getenv("RINGCENTRAL_CLIENT_ID"),
            System.getenv("RINGCENTRAL_CLIENT_SECRET"),
            System.getenv("RINGCENTRAL_SERVER_URL")
        );

        rc.authorize(
            System.getenv("RINGCENTRAL_USERNAME"),
            System.getenv("RINGCENTRAL_EXTENSION"),
            System.getenv("RINGCENTRAL_PASSWORD")
        );

        String groupId = rc.restapi().glip().groups().list().records[0].id;
        String postId = rc.restapi().glip().groups(groupId).posts().get().records[0].id;
        String newText = UUID.randomUUID().toString();
        String updatedText = rc.restapi().glip().groups(groupId).posts(postId).text().put(newText);
        assertEquals(updatedText, newText);

        rc.revoke();
    }
}
