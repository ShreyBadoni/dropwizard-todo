package com.codeflu;

import com.codeflu.models.Task;
import com.codeflu.dao.TaskDAO;
import com.codeflu.services.TaskService;
import com.codeflu.api.TaskResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.db.DataSourceFactory;

public class Main extends Application<AppConfiguration> {

    private final HibernateBundle<AppConfiguration> hibernateBundle =
            new HibernateBundle<AppConfiguration>(Task.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }

                @Override
                protected void configure(org.hibernate.cfg.Configuration configuration) {
                    configuration.setProperty("hibernate.hbm2ddl.auto", "update");
                }
            };


    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {
        // Initialize DAO and Service
        TaskDAO taskDAO = new TaskDAO(hibernateBundle.getSessionFactory());
        TaskService taskService = new TaskService(taskDAO);

        // Register Resource
        environment.jersey().register(new TaskResource(taskService));

        System.out.println("Application started!");
    }

    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }
}
