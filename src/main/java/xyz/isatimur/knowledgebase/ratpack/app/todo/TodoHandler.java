package xyz.isatimur.knowledgebase.ratpack.app.todo;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import ratpack.func.Function;
import ratpack.handling.ByMethodSpec;
import ratpack.handling.Context;
import ratpack.handling.InjectionHandler;
import ratpack.http.Response;
import ratpack.jackson.Jackson;
import ratpack.jackson.JsonRender;

import java.util.Map;

/**
 * Created by isati on 23.02.2017.
 */
public class TodoHandler extends InjectionHandler {

    public void handle(Context ctx, TodoRepository repo, String base) throws Exception {
        Long todoId = Long.parseLong(ctx.getPathTokens().get("id"));

        Function<TodoModel, TodoModel> hostUpdater = todo -> todo.baseUrl(base);
        Function<TodoModel, JsonRender> toJson = hostUpdater.andThen(Jackson::json);

        Response response = ctx.getResponse();

        ctx.byMethod((ByMethodSpec byMethodSpec) -> byMethodSpec
                .options(() -> {
                    response.getHeaders().set("Access-Control-Allow-Methods", "OPTIONS, GET, PATCH, DELETE");
                    response.send();
                })
                .get(() -> repo.getById(todoId).map(toJson).then(ctx::render))
                .patch(() -> ctx.parse(Jackson.fromJson(new TypeToken<Map<String, Object>>() {
                        }))
                                .map(map -> {
                                    Map<String, Object> m = Maps.newHashMap();
                                    map.keySet().forEach(k -> m.put(k.toUpperCase(), map.get(k)));
                                    m.put("ID", todoId);
                                    return m;
                                })
                                .flatMap(repo::update)
                                .map(toJson)
                                .then(ctx::render)
                )
                .delete(() -> repo.delete(todoId).then(response::send)));
    }
}
