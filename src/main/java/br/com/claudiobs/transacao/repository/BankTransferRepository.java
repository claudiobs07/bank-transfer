package br.com.claudiobs.transacao.repository;

import br.com.claudiobs.transacao.domain.BankTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankTransferRepository extends JpaRepository<BankTransfer, Long> {

}
