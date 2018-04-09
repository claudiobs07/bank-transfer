package br.com.claudiobs.banktransfer.repository;

import br.com.claudiobs.banktransfer.domain.BankTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankTransferRepository extends JpaRepository<BankTransfer, Long> {

}
