package cgb.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cgb.transfer.entity.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}