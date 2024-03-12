package dev.urner.volodb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "salary")
@Getter
@Setter
@RequiredArgsConstructor
public class Salary {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int Id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "pocket_money")
  private Long pocketMoney;

  @Column(name = "free_of_charge_service")
  private Long freeOfChargeService;

  @Column(name = "food_allowance")
  private Long foodAllowance;

  @Column(name = "free_of_charge_food")
  private Long freeOfChargeFood;

  @Column(name = "accommodation_allowance")
  private Long accomodationAllowance;

  @Column(name = "free_of_charge_accommodation")
  private Long freeOfChargeAccommodation;

  @Column(name = "insurance_contributions")
  private Long insuranceContributions;

}
