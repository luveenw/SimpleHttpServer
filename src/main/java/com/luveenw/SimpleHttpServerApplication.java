package com.luveenw;

import com.google.common.base.Throwables;
import com.luveenw.health.DummyHealthCheck;
import com.luveenw.resources.DummyViewEndpoint;
import com.luveenw.resources.HelloWorldEndpoint;
import com.luveenw.resources.JokeEndpoint;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import io.dropwizard.views.ViewRenderException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.glassfish.jersey.spi.ExtendedExceptionMapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.luveenw.StacktraceUtils.*;

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
        registerEndpoints(environment);
        registerHealthChecks(environment);
        registerCustomExceptionMappers(environment);
    }

    private static void registerHealthChecks(final Environment environment) {
        final DummyHealthCheck healthCheck = new DummyHealthCheck();

        environment.healthChecks().register("template", healthCheck);
    }

    private static void registerEndpoints(final Environment env) {
        final HelloWorldEndpoint helloWorldEndpoint = new HelloWorldEndpoint();
        final DummyViewEndpoint dummyViewEndpoint = new DummyViewEndpoint();
        final JokeEndpoint jokeEndpoint = new JokeEndpoint();

        env.jersey().register(helloWorldEndpoint);
        env.jersey().register(dummyViewEndpoint);
        env.jersey().register(jokeEndpoint);
    }

    private static void registerCustomExceptionMappers(final Environment env) {
        // Bubble up FreeMarker template parse/render exceptions:
        // https://www.dropwizard.io/1.1.0/docs/manual/views.html#template-errors
        env.jersey().register(new ExtendedExceptionMapper<WebApplicationException>() {
            @Override
            public Response toResponse(WebApplicationException exception) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(getStackTrace(exception))
                        .type(MediaType.TEXT_HTML)
                        .build();

            }

            @Override
            public boolean isMappable(WebApplicationException e) {
                return ExceptionUtils.indexOfThrowable(e, ViewRenderException.class) != -1;
            }
        });

        env.jersey().register(new ExtendedExceptionMapper<WebApplicationException>() {
            @Override
            public Response toResponse(WebApplicationException exception) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(getStackTrace(exception))
                        .type(MediaType.TEXT_HTML)
                        .build();

            }

            @Override
            public boolean isMappable(WebApplicationException e) {
                return ExceptionUtils.indexOfThrowable(e, TemplateException.class) != -1;
            }
        });

        env.jersey().register(new ExtendedExceptionMapper<WebApplicationException>() {
            @Override
            public Response toResponse(WebApplicationException exception) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(getStackTrace(exception))
                        .type(MediaType.TEXT_HTML)
                        .build();
            }

            @Override
            public boolean isMappable(WebApplicationException e) {
                return Throwables.getRootCause(e).getClass() == TemplateNotFoundException.class;
            }
        });
    }
}
