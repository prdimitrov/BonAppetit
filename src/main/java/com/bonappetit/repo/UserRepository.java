package com.bonappetit.repo;


import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String value);

    @Query("SELECT u.favouriteRecipes FROM User u WHERE u.username = :username")
    List<Recipe> findUserFavouriteRecipes(@Param("username") String username);
}
