package xyz.isatimur.knowledgebase.ratpack.app.todo;

import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 * Created by isati on 23.02.2017.
 */
public class TodoChain implements Action<Chain> {
    @Override
    public void execute(Chain chain) throws Exception {
        chain.path(TodoBaseHandler.class)
                .path(":id", TodoHandler.class);
    }
}
