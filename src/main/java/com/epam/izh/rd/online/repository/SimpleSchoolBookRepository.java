package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        this.schoolBooks = Arrays.copyOf(this.schoolBooks, this.schoolBooks.length + 1);
        this.schoolBooks[this.schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] findSchoolBooks = new SchoolBook[0];
        for (SchoolBook book : this.schoolBooks) {
            if (book.getName().equals(name)) {
                findSchoolBooks = Arrays.copyOf(findSchoolBooks, findSchoolBooks.length + 1);
                findSchoolBooks[findSchoolBooks.length - 1] = book;
            }
        }
        return findSchoolBooks;
    }

    @Override
    public boolean removeByName(String name) {
        if (this.findByName(name).length == 0) {
            return false;
        }
        int i;
        for (SchoolBook book : this.schoolBooks) {
            for (i = 0; i < this.schoolBooks.length; i++) {
                if (this.schoolBooks[i].equals(book)) {
                    break;
                }
            }
            for (; i < this.schoolBooks.length - 1; i++) {
                this.schoolBooks[i] = this.schoolBooks[i + 1];
            }
            this.schoolBooks = Arrays.copyOf(this.schoolBooks, this.schoolBooks.length - 1);
        }
        return true;
    }

    @Override
    public int count() {
        return this.schoolBooks.length;
    }
}
