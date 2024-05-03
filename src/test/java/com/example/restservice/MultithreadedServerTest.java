package com.example.restservice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MultithreadedServerTest {

    @Test
    public void testGetInstance() {
        MultithreadedServer server1 = MultithreadedServer.getInstance(10);
        MultithreadedServer server2 = MultithreadedServer.getInstance(10);
        assertSame(server1, server2);
    }

    @Test
    public void testProcessCommandData() {
        MultithreadedServer server = MultithreadedServer.getInstance(10);
        Runnable task = () -> System.out.println("Task is running");
        server.processCommandData(task);
        // Since processCommandData doesn't return anything, there's not much we can assert here.
        // This is more of a smoke test to ensure that the method runs without throwing an exception.
    }
}