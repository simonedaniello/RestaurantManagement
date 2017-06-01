package uni.isssr.entities;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

import org.springframework.data.repository.CrudRepository;

// Methods auto-implemented by Spring
/*
    <S extends T> S save(S entity);

    <S extends T> Iterable<S> save(Iterable<S> entities);

    User findOne(String id);

    boolean exists(String id);

    Iterable<User> findAll();

    Iterable<User> findAll(Iterable<String> ids);

    long count();

    void delete(String id);

    void delete(User entity);

    void delete(Iterable<? extends User> entities);

    void deleteAll();
 */

public interface UserRepository extends CrudRepository<User, String>{
}
