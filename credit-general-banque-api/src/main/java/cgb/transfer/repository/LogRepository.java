package cgb.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgb.transfer.entity.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}

