package com.example.restservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The MultithreadedServer class represents a server that can process tasks concurrently using a fixed thread pool.
 */
public class MultithreadedServer {

    private static MultithreadedServer instance;
    private ExecutorService executorService;
    private static final Logger LOGGER = Logger.getLogger(MultithreadedServer.class.getName());

    /**
     * Private constructor to create a MultithreadedServer instance with a fixed number of threads.
     *
     * @param numberOfThreads The number of threads in the thread pool.
     */
    private MultithreadedServer(int numberOfThreads) {
        executorService = Executors.newFixedThreadPool(numberOfThreads);
    }

    /**
     * Returns the singleton instance of MultithreadedServer.
     *
     * @param numberOfThreads The number of threads in the thread pool.
     * @return The MultithreadedServer instance.
     */
    public static synchronized MultithreadedServer getInstance(int numberOfThreads) {
        if (instance == null) {
            instance = new MultithreadedServer(numberOfThreads);
        }
        return instance;
    }

    /**
     * Submits a task to the executor service for processing.
     *
     * @param task The task to be processed.
     */
    public void processCommandData(Runnable task) {
        try {
            executorService.submit(task);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error processing task", e);
        }
    }

    /**
     * Shuts down the executor service, allowing previously submitted tasks to complete.
     */
    public void shutdown() {
        executorService.shutdown();
    }
}