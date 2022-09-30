package it.bitrock.springbootssojwtdemo.repository;

import it.bitrock.springbootssojwtdemo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAll();

    @Query(value = "SELECT a "
            + "FROM Account a "
            + "INNER JOIN Role r ON r.id = a.role.id "
            + "WHERE a.username = :username")
    Account getAccountAndRoleByUsername(@Param("username") String username);

    boolean existsById(Long id);
}
