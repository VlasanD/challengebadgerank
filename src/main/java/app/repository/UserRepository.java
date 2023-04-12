package app.repository;

import app.model.User;

public interface UserRepository extends CRUDRepository<User, Integer> {
    User findByUsername(String username);
}
