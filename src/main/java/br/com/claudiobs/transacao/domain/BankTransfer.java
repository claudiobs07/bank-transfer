package br.com.claudiobs.transacao.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "bank_transfer")
@EqualsAndHashCode(of = "id")
public class BankTransfer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @NotNull
    @Column(name = "source_account")
    Long sourceAccount;

    @NotNull
    @Column(name = "destination_account")
    Long destinationAccount;

    @NotNull
    BigDecimal amount;

    @NotNull
    BigDecimal tax;

    @NotNull
    LocalDate date;

    @NotNull
    @Column(name = "created_at")
    LocalDate createdAt;
}
