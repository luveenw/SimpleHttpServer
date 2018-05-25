package com.luveenw;

import com.luveenw.health.DummyHealthCheck;
import com.luveenw.resources.HelloWorldEndpoint;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SimpleHttpServerApplication extends Application<SimpleHttpServerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SimpleHttpServerApplication().run(args);
    }

    @Override
    public String getName() {
        return "SimpleHttpServer";
    }

    @Override
    public void initialize(final Bootstrap<SimpleHttpServerConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/"));
//        bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", "js"));
//        bootstrap.addBundle(new AssetsBundle("/assets/css", "/css", "css"));
    }

    @Override
    public void run(final SimpleHttpServerConfiguration configuration, final Environment environment) {
        final DummyHealthCheck healthCheck = new DummyHealthCheck();
        final HelloWorldEndpoint resource = new HelloWorldEndpoint();

        environment.jersey().register(resource);
        environment.healthChecks().register("template", healthCheck);
    }

}
