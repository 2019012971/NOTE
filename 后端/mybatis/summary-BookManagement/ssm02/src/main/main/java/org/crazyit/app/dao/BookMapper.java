package org.crazyit.app.dao;

import java.util.List;

import org.crazyit.app.domain.*;
import org.springframework.stereotype.Component;

/**
 * Description:<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2020, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 * @author Yeeku.H.Lee kongyeeku@163.com 公众号: fkbooks<br>
 * @version 1.0
 */
//@Component("bookMapper")
public interface BookMapper
{
    Integer save(Book book);

    Book findById(int id);

    List<Book> findAll();

    Integer update(Book book);

    Integer delete(Integer id);

}