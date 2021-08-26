package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //정석
        try {
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            //영속 컨텍스트에서 분리
            //em.detach(member);

            //연속 컨텍스트 비우기
            em.clear();

            //영속 컨텍스트 닫기
            //em.close();

            System.out.println("================================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        //close
        emf.close();
    }
}
