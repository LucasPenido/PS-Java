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
   public long id;

   @Column(nullable = false, unique = true)
   public String name;

   @Column(nullable = false)
   public BigDecimal price;

   @Column(nullable = false)
   public short score;

   @Column(nullable = false)
   public String image;

}