package com.example.jpa.db.entities

import javax.persistence.*

@Entity
class Book (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,

    var title: String,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "author_id")
    var author: Author? = null
)