package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (this.findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        this.authors = Arrays.copyOf(this.authors, this.authors.length + 1);
        this.authors[this.authors.length - 1] = author;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author aut : this.authors) {
            if (aut.getName().equals(name) && aut.getLastName().equals(lastname)) {
                return aut;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (this.findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        }
        int i;
        for (i = 0; i < this.authors.length; i++) {
            if (this.authors[i].equals(author)) {
                break;
            }
        }
        for (; i < this.authors.length - 1; i++) {
            this.authors[i] = this.authors[i + 1];
        }
        this.authors = Arrays.copyOf(this.authors, this.authors.length - 1);
        return true;
    }

    @Override
    public int count() {
        return this.authors.length;
    }
}
