package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class MemberRepository {


    //직접 주입
    /*@PersistenceUnit
    private EntityManagerFactory emf;*/

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        Member member = em.find(Member.class, id);
        return member;
    }

    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member me", Member.class)
                .getResultList();
        return result;
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
