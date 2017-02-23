package xyz.isatimur.knowledgebase.ratpack.app.todo;

import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.MutableHeaders;
import ratpack.registry.Registry;

/**
 * Created by isati on 21.02.2017.
 */
public class CORSHandler implements Handler {
    @Override
    public void handle(Context ctx) throws Exception {
        MutableHeaders headers = ctx.getResponse().getHeaders();
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Headers", "x-request, origin, accept, content-type");

        String baseUrl = "http://" + ctx.getRequest().getHeaders().get("Host");
        ctx.next(Registry.single(String.class, baseUrl));
    }
}

