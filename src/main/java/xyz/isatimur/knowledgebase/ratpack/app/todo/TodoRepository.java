package xyz.isatimur.knowledgebase.ratpack.app.todo;

import jooq.tables.records.TodoRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import ratpack.exec.Blocking;
import ratpack.exec.Operation;
import ratpack.exec.Promise;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

import static jooq.tables.Todo.TODO;

/**
 * Created by isati on 23.02.2017.
 */
public class TodoRepository {
    private final DSLContext create;

    public TodoRepository(DataSource ds) {
        this.create = DSL.using(ds, SQLDialect.H2);
    }

    public Promise<List<TodoModel>> getAll() {
        SelectJoinStep all = create
                .select()
                .from(TODO);
        return Blocking.get(() -> all
                .fetchInto(TodoModel.class));
    }

    public Promise<TodoModel> getById(Long id) {
        SelectConditionStep where = create
                .select()
                .from(TODO)
                .where(TODO.ID.eq(id));
        return Blocking.get(() -> where.fetchOne().into(TodoModel.class));
    }

    public Promise<TodoModel> add(TodoModel model) {
        TodoRecord record = create.newRecord(TODO, model);
        return Blocking.op(record::store)
                .next(Blocking.op(record::refresh))
                .map(() -> record.into(TodoModel.class));
    }

    public Promise<TodoModel> update(Map<String, Object> model) {
        TodoRecord record = create.newRecord(TODO, model);
        return Blocking.op(() -> create.executeUpdate(record))
                .next(Blocking.op(record::refresh))
                .map(() -> record.into(TodoModel.class));
    }


    public Operation delete(Long id) {
        DeleteConditionStep<TodoRecord> deleteConditionStep = create.deleteFrom(TODO).where(TODO.ID.eq(id));
        return Blocking.op(deleteConditionStep::execute);
    }

    public Operation deleteAll() {
        DeleteWhereStep<TodoRecord> delete = create.deleteFrom(TODO);
        return Blocking.op(delete::execute);
    }

}
