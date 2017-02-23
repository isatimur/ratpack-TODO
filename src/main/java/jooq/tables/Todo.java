/**
 * This class is generated by jOOQ
 */
package jooq.tables;


import jooq.Keys;
import jooq.Public;
import jooq.tables.records.TodoRecord;
import org.jooq.*;
import org.jooq.impl.TableImpl;

import javax.annotation.Generated;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.8.1"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Todo extends TableImpl<TodoRecord> {

    /**
     * The reference instance of <code>PUBLIC.TODO</code>
     */
    public static final Todo TODO = new Todo();
    private static final long serialVersionUID = 1403763804;
    /**
     * The column <code>PUBLIC.TODO.ID</code>.
     */
    public final TableField<TodoRecord, Long> ID = createField("ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("(NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_2729CAED_CA18_4B1C_8A81_1DE083695255)", org.jooq.impl.SQLDataType.BIGINT)), this, "");
    /**
     * The column <code>PUBLIC.TODO.TITLE</code>.
     */
    public final TableField<TodoRecord, String> TITLE = createField("TITLE", org.jooq.impl.SQLDataType.VARCHAR.length(256), this, "");
    /**
     * The column <code>PUBLIC.TODO.COMPLETED</code>.
     */
    public final TableField<TodoRecord, Boolean> COMPLETED = createField("COMPLETED", org.jooq.impl.SQLDataType.BOOLEAN.defaultValue(org.jooq.impl.DSL.field("FALSE", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");
    /**
     * The column <code>PUBLIC.TODO.ORDER</code>.
     */
    public final TableField<TodoRecord, Integer> ORDER = createField("ORDER", org.jooq.impl.SQLDataType.INTEGER.defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * Create a <code>PUBLIC.TODO</code> table reference
     */
    public Todo() {
        this("TODO", null);
    }

    /**
     * Create an aliased <code>PUBLIC.TODO</code> table reference
     */
    public Todo(String alias) {
        this(alias, TODO);
    }

    private Todo(String alias, Table<TodoRecord> aliased) {
        this(alias, aliased, null);
    }

    private Todo(String alias, Table<TodoRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TodoRecord> getRecordType() {
        return TodoRecord.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TodoRecord, Long> getIdentity() {
        return Keys.IDENTITY_TODO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TodoRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TodoRecord>> getKeys() {
        return Arrays.<UniqueKey<TodoRecord>>asList(Keys.CONSTRAINT_2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Todo as(String alias) {
        return new Todo(alias, this);
    }

    /**
     * Rename this table
     */
    public Todo rename(String name) {
        return new Todo(name, null);
    }
}
