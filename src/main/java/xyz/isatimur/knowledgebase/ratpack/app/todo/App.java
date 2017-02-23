package xyz.isatimur.knowledgebase.ratpack.app.todo;

import ratpack.guice.Guice;
import ratpack.hikari.HikariModule;
import ratpack.server.RatpackServer;

/**
 * Created by isati on 21.02.2017.
 * A simple Ratpack AppToDo  -  entry point
 */
public class App {
    public static void main(String[] args) throws Exception {
        RatpackServer
                .start(serverSpec -> serverSpec
                        .registry(Guice.registry(bindingsSpec -> bindingsSpec
                                .module(HikariModule.class, hikariConfig -> {
                                    hikariConfig.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");
                                    hikariConfig.addDataSourceProperty("URL", "jdbc:h2:mem:todo;INIT=RUNSCRIPT FROM 'classpath:/init.sql'");
                                })
                                .module(TodoModule.class)
                                .bindInstance(new CORSHandler())
                                .bindInstance(new TodoBaseHandler())
                                .bindInstance(new TodoHandler())
                                .bindInstance(new TodoChain())
                        ))

                        .handlers(chain -> chain
                                .all(CORSHandler.class)
                                .insert(TodoChain.class)

                        ));

    }
}
