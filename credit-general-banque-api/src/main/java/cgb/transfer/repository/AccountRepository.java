package cgb.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgb.transfer.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}

