package com.example.jpa.db.entities

import javax.persistence.*


@Entity
@Table(name = "EVENTS")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    val title: String
)
