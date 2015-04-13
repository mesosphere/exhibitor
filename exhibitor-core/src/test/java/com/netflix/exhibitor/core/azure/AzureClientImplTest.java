package com.netflix.exhibitor.core.azure;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Azure Client
 *
 * Will need to probably mock out a bunch of Azure classes
 *
 * Created by marco on 4/13/15.
 */
public class AzureClientImplTest {

    public static class FakeCredentials implements AzureCredential {

        @Override
        public String getAccountName() {
            return "fakeAccount";
        }

        @Override
        public String getAccountKey() {
            return "fakeAccountKey";
        }
    }

    AzureClient client;

    @Test
    public void testCanCreate() {
        client = new AzureClientImpl(new FakeCredentials());
        assertNotNull(client);
        // TODO: should check the the connection string matches what we expect
        //  this will require mocking the CloudStorageAccount class and grabbing the contents of
        // the call to parse()
    }

    // Not best practice, but, while we are at it, we may as well test the factory too
    @Test
    public void testFactory() throws Exception {

        // TODO: this interface ought to be removed, it's of no use currently
        AzureClientFactory factory = new AzureClientFactoryImpl();
        client = factory.makeNewClient(new FakeCredentials());
    }

    @Test
    public void testGetClient() throws Exception {

    }

    @Test
    public void testGetBlob() throws Exception {

    }

    @Test
    public void testGetBlobProperties() throws Exception {

    }

    @Test
    public void testListBlobs() throws Exception {

    }

    @Test
    public void testPutBlob() throws Exception {

    }

    @Test
    public void testDeleteBlob() throws Exception {

    }

    @Test
    public void testClose() throws Exception {

    }
}