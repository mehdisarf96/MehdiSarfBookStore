package com.mehdisarf.mehdisarfbookstore.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Category.findAll", query = "select c from Category c order by c.name"),
                @NamedQuery(name = "Category.countAll", query = "select count(*) from Category c"),
                @NamedQuery(name = "Category.find", query = "select c from Category c where c.name = :name")
        }
)
public class Category {
    private Integer categoryId;
    private String name;
    private Set<Book> books = new HashSet<Book>(0);

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(Integer categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public Category(String name, Set<Book> books) {
        this.name = name;
        this.books = books;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", unique = true, nullable = false)
    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "name", nullable = false, length = 30)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}