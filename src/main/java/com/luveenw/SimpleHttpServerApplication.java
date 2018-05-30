package com.luveenw;

import com.luveenw.health.DummyHealthCheck;
import com.luveenw.resources.DummyViewEndpoint;
import com.luveenw.resources.HelloWorldEndpoint;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

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
        bootstrap.addBundle(new AssetsBundle("/assets"));
//        bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", "js"));
//        bootstrap.addBundle(new AssetsBundle("/assets/css", "/css", "css"));
        bootstrap.addBundle(new ViewBundle());
    }

    @Override
    public void run(final SimpleHttpServerConfiguration configuration, final Environment environment) {
        final DummyHealthCheck healthCheck = new DummyHealthCheck();
        final HelloWorldEndpoint helloWorldEndpoint = new HelloWorldEndpoint();
        final DummyViewEndpoint dummyViewEndpoint = new DummyViewEndpoint();

        environment.jersey().register(helloWorldEndpoint);
        environment.jersey().register(dummyViewEndpoint);
        environment.healthChecks().register("template", healthCheck);
    }

}
