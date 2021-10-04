package com.example.jpa.db.entities

import javax.persistence.*


@Entity
@Table(name = "ITEMS")
data class Item (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    var name: String,
    var price: Double
)