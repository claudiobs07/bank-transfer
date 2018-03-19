package br.com.claudiobs.transacao.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "bank_transfer")
@EqualsAndHashCode(of = "id")
public class BankTransfer implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "[\\d]{6}")
    @Column(name = "source_account")
    private String sourceAccount;

    @NotNull
    @Pattern(regexp = "[\\d]{6}")
    @Column(name = "destination_account")
    private String destinationAccount;

    @NotNull
    private BigDecimal amount;

    private BigDecimal tax;

    @NotNull
    private LocalDate date;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDate.now();
    }
}
