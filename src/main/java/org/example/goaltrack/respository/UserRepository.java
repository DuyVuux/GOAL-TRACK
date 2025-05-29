package org.example.goaltrack.respository;

import org.example.goaltrack.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository kế thừa CrudRepository và bổ sung phân trang, batch delete...
    @Query(value = "select * from public.user", nativeQuery = true)
    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    @Query(
            value = "SELECT * FROM public.user",
            countQuery = "SELECT count(*) FROM public.user",
            nativeQuery = true
    )


    Page<User> findAllWithPaging(Pageable pageable);

    @Query("SELECT u FROM User u WHERE " +
            "(LOWER(u.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.phone) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<User> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    User findByUsername(String userName);

    User findByEmail(String email);





    //
    Optional<User> findBySecretCode(String secretCode);
    boolean existsBySecretCode(String secretCode);
}
