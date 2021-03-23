package org.crazyit.app.service.Impl;

import org.crazyit.app.dao.BookMapper;
import org.crazyit.app.domain.Book;
import org.crazyit.app.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service("bookService")
@Transactional(propagation = Propagation.REQUIRED,
    isolation = Isolation.DEFAULT, timeout = 5)
public class BookServiceImpl implements BookService {

    @Resource(name = "bookMapper")
    private BookMapper bookMapper;

    /**
     *
     * @param file 传入web端上传文件的各种信息
     * @param book book实例
     * @param attrs 用于之后的重定向的参数保存
     * @param requestFile 获取图片需要保存的路径
     * @return 返回是否成功上传图书信息的识别码
     * @throws IOException
     */
    @Override
    public Integer saveBook(MultipartFile file, Book book, RedirectAttributes attrs, String requestFile) throws IOException {
        // 保存图片的路径，图片上传成功后，将路径保存到数据库
        String filePath = requestFile;
        // 获取原始图片的扩展名
        String originalFilename = file.getOriginalFilename();
        // 生成文件新的名字
        String newFileName = UUID.randomUUID() + originalFilename;
        // 封装上传文件位置的全路径
        File targetFile = new File(filePath, newFileName);
        file.transferTo(targetFile);

        // 保存到数据库
        book.setCover(newFileName);
        return bookMapper.save(book);
//        return "redirect:/listImages";

//        return bookMapper.save(book);
    }

    @Override
    public Book getBook(Integer id) {
        return bookMapper.findById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookMapper.findAll();
    }

    @Override
    public Integer updateBook(Book book) {
        return bookMapper.update(book);
    }

    @Override
    public Integer deleteBook(Integer id) {
        return bookMapper.delete(id);
    }
}
