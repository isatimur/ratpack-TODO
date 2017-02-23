package xyz.isatimur.knowledgebase.ratpack.app.todo;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import javax.sql.DataSource;

/**
 * Created by isati on 23.02.2017.
 */
public class TodoModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    public TodoRepository todoRepository(DataSource ds) {
        return new TodoRepository(ds);
    }


}