package org.crazyit.app.service;

import org.crazyit.app.domain.Book;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

public interface BookService {

//    Integer saveBook(Book book);
    Integer saveBook(MultipartFile file, Book book, RedirectAttributes attrs, String requestFile) throws IOException;

    Book getBook(Integer id);

    List<Book> getAllBooks();

    Integer updateBook(Book book);

    Integer deleteBook(Integer id);
}
