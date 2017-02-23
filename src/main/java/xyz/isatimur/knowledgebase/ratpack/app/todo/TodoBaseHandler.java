package xyz.isatimur.knowledgebase.ratpack.app.todo;

import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.InjectionHandler;
import ratpack.http.Response;
import ratpack.jackson.Jackson;

/**
 * Created by isati on 23.02.2017.
 */
public class TodoBaseHandler extends InjectionHandler {
    public void handle(Context ctx, TodoRepository repository) throws Exception {
        Response rs = ctx.getResponse();
        ctx.byMethod(byMethodSpec -> byMethodSpec
                .options(() -> {
                    rs.getHeaders().set("Access-Control-Allow-Methods", "OPTIONS, GET, POST, DELETE");
                    rs.send();
                })
                .get(() -> {
                    repository.getAll()
                            .map(Jackson::json)
                            .then(ctx::render);
                })
                .post(() -> {
                    Promise<TodoModel> todo = ctx.parse(Jackson.fromJson(TodoModel.class));
                    todo
                            .flatMap(repository::add)
                            .map(Jackson::json)
                            .then(ctx::render);
                })
                .delete(() -> repository.deleteAll().then(rs::send))
        );
    }

}
