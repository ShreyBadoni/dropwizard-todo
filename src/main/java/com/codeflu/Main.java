package com.codeflu;

import com.codeflu.api.TaskResource;
import com.codeflu.api.SampleResource; // New SampleResource import
import com.codeflu.services.TaskService;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class Main extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        // Allows configuration to be loaded from resources directory
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {
        // Initialize TaskService
        TaskService taskService = new TaskService();

        // Register TaskResource for TODO APIs
        environment.jersey().register(new TaskResource(taskService));

        // Register SampleResource for testing
        environment.jersey().register(new SampleResource()); // New test endpoint

        // Print confirmation in logs
        System.out.println("TODO List application started...");
    }
}
