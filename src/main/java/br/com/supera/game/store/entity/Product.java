package br.com.supera.game.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @Column(nullable = false, unique = true)
   private String name;

   @Column(nullable = false)
   private BigDecimal price;

   @Column(nullable = false)
   private short score;

   @Column(nullable = false)
   private String image;

}