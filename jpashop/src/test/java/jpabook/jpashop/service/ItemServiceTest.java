package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    public void 아이템_등록() {
        //given
        Item book = new Book();
        book.setName("book1");

        //when
        itemService.saveItem(book);
        Item findBook = itemService.findOne(book.getId());

        //then
        Assertions.assertThat("book1").isEqualTo(findBook.getName());
        Assert.assertEquals("book1",findBook.getName());
    }

}