package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

//            Member findMember = em.find(Member.class, member.getId());
            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("findMember.getUsername() = " + refMember.getUsername());//proxy
            refMember.getUsername();

            //프록시 인스턴스 초기화 여부 확인
            //System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

            //클래스 확인 방법
            //System.out.println("refMember.getClass() = " + refMember.getClass());

            //강제 초기화
            Hibernate.initialize(refMember);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        //close
        emf.close();
    }
}
