package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    //상품 등록 및 업데이트..?
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
            //변경되지 않은 값이 null 로 변경된다.
            //사용하지 않는 것이 좋다.
        }
    }

    //단일 상품 조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    //상품 전체 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
