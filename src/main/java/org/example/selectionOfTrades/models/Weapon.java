package org.example.selectionOfTrades.models;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Weapons")
public class Weapon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_id", nullable = false)
    private Long dataId;
    @Column(name = "exterior_id", nullable = false)
    private Long exteriorId;
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "price", columnDefinition = "money",nullable = false)
    private double price;
    @Column(name = "quantity", nullable = false)
    private Long quantity;


}

