package org.crazyit.app.controller;

import org.crazyit.app.domain.Book;
import org.crazyit.app.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import java.io.IOException;
//import javax.servlet.ServletRequest;

@Controller
public class BookController {

    @Resource(name = "bookService")
    private BookService bookService;

//    @GetMapping("/{url}")
//    public String url(@PathVariable String url) {
//        return url;
//    }

    // 啥都不用做，触发对web层对book参数的传递
    @GetMapping("/bookForm")
    public void bookForm(Book book) {

    }

    // 获取图书列表信息
    @GetMapping("/books")
    public void books(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
    }

    //更新图书信息
    @GetMapping("/updateForm")
    public void updateForm(Integer id, Model model) {
        model.addAttribute("book", bookService.getBook(id));
    }

    // 删除图书信息
    @GetMapping("/deleteBook")
    public String deleteBook(Integer id, RedirectAttributes attrs) {
        Integer count = bookService.deleteBook(id);
        if (count > 0) {
            attrs.addFlashAttribute("tip", "图书删除成功");
        } else {
            attrs.addFlashAttribute("tip", "图书删除失败，请您重新尝试");
        }
        return "redirect:books";
    }

    /**
     *
     * @param file 传入需要上传的文件的各种信息
     * @param book 把网页表单中的信息注入book实例中
     * @param bindingResult 统计错误（没按要求填写好）的信息个数
     * @param attrs 重定向参数
     * @param request 获取request参数
     * @return 返回对应的jsp页面
     * @throws IOException
     */
    @PostMapping("/addBook")
    public String addBook(@RequestParam("file") MultipartFile file, Book book, BindingResult bindingResult, RedirectAttributes attrs,
                          ServletRequest request) throws IOException {
        if (bindingResult.getErrorCount() > 0) {
            return "bookForm";
        }
        String requestFile = request.getServletContext().getRealPath("/WEB-INF/uploads/");
        Integer id = bookService.saveBook(file, book, attrs, requestFile);
        if (id > 0) {
            attrs.addFlashAttribute("tip", "图书添加成功");
            return "redirect:books";
        } else {
            attrs.addFlashAttribute("tip", "图书添加失败，请您重新添加");
            return "redirect:bookForm";
        }
    }

    // 更新书本信息
    @PostMapping("/updateBook")
    public String updateBook(@Validated Book book, BindingResult bindingResult, RedirectAttributes attrs) {
        if (bindingResult.getErrorCount() > 0) {
            return "updateForm";
        }
        Integer count = bookService.updateBook(book);
        if (count > 0) {
            attrs.addFlashAttribute("tip", "图书修改成功");
            return "redirect:books";
        } else {
            attrs.addFlashAttribute("tip", "图书修改失败，请您重新尝试");
            return "updateForm";
        }
    }
}
