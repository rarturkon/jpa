package com.example.jpa.db.entities

import javax.persistence.*

@Entity
class Author (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,

    val name: String,

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL])
    val books: MutableList<Book> = mutableListOf()
) {
    fun addBook(book: Book) {
        books.add(book)
        book.author = this
    }
}