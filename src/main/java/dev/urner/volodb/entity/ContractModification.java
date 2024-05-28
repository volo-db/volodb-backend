package dev.urner.volodb.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import dev.urner.volodb.converter.HashMapConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ContractModification")
@Getter
@Setter
@RequiredArgsConstructor
public class ContractModification {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "timestamp")
  private LocalDateTime timestamp;

  // status (ContractModificationStatus) -> FK
  @ManyToOne
  @JoinColumn(name = "status")
  private ContractModificationStatus status;

  // contract -> FK
  @Column(name = "contract")
  private int contractId;

  // type (contractModificationType) -> FK
  @ManyToOne
  @JoinColumn(name = "type")
  private ContractModificationType type;

  // into_force_from (Date)
  @Column
  private LocalDate intoForceFrom;

  // value (JSON)
  @Column
  private String value;

}
