package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService<SchoolBook> {
    BookRepository<SchoolBook> schoolBookBookRepository;
    AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(SchoolBook book) {
        if(this.authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName()) == null) {
            return false;
        }
        this.schoolBookBookRepository.save(book);
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return this.schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        return this.schoolBookBookRepository.findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        return this.schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return this.schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
        SchoolBook[] findSchoolBook = this.schoolBookBookRepository.findByName(name);
        for (SchoolBook book : findSchoolBook) {
            if (this.authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName()) != null) {
                return this.authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName());
            }
        }
        return null;
    }
}
