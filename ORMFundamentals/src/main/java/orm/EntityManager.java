package orm;

import annotations.Entity;
import annotations.Id;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Arrays;

public class EntityManager<E> implements DBContext<E> {

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException {
        Field idColumn = getIdColumn(entity.getClass());
        idColumn.setAccessible(true);
        Object idValue = idColumn.get(entity);

        if (idValue == null || (long) idValue <= 0) {
            return doInsert(entity, idColumn);
        }
        return doUpdate(entity, idColumn);
    }

    private boolean doUpdate(E entity, Field idColumn) {
        return false;
    }

    private boolean doInsert(E entity, Field idColumn) {
        String tableName = getTableName(entity.getClass());
        return false;
    }

    private String getTableName(Class<?> aClass) {
        Entity[] annotationsByType = aClass.getAnnotationsByType(Entity.class);
        Id[] annotationsId = aClass.getAnnotationsByType(Id.class);

        return "";
    }

    @Override
    public Iterable<E> find(Class<E> table) {
        return null;
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) {
        return null;
    }

    @Override
    public E findFirst(Class<E> table) {
        return null;
    }

    @Override
    public E findFirst(Class<E> table, String where) {
        return null;
    }

    private Field getIdColumn(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields()).filter(x -> x.isAnnotationPresent(Id.class))
                .findFirst().orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary key"));
    }
}
