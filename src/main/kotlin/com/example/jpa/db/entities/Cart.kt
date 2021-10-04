package com.example.jpa.db.entities

import javax.persistence.*

@Entity
@Table(name = "CARTS")
data class Cart (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,

    val title: String,

    @OneToMany(cascade = [CascadeType.ALL])
    val items: List<Item>
)