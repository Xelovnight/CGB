package cgb.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgb.transfer.entity.LotTransfer;


@Repository
public interface LotTransferRepository extends JpaRepository<LotTransfer, Long> {
	
}