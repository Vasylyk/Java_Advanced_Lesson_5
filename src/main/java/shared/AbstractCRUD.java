package shared;

import java.util.List;

public interface AbstractCRUD <T> {

    T create (T t);

    T read (Integer id);

    T update (Integer id, T t);

    void delete (Integer id);

    List<T> readAll ();
}
