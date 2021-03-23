package org.crazyit.app.domain;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartFile;

public class Book {
    //唯一标识id
    private Integer id;
    @NotBlank(message = "图书名不允许为空")
    @Length(min=6, max=30, message = "书名长度必须为6~30个字符")
    private String title;
    @NotBlank(message = "作者名不允许为空")
    @Length(min=2, max = 10, message = "作者名长度必须为2~10个字符")
    private String author;
    @Range(min = 50, max = 200, message = "图书价格必须为50~200")
    private double price;
    //保存图片文件路径
    private String cover;

    // 无参数的构造器
    public Book()
    {
    }
    // 初始化全部成员变量的构造器
    public Book(Integer id, String title,
                String author, double price, String cover)
    {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.cover = cover;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", cover=" + cover +
                '}';
    }
}
