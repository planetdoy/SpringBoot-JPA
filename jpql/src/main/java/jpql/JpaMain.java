package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("관리자입니다");
            member.setAge(10);
            member.setType(MemberType.ADMIN);

            em.persist(member);

            em.flush();
            em.clear();

//            String query = "select concat('a', 'b') from Member m";
//            String query = "select substring(m.username,2,3) from Member m";
//            String query = "select locate('bc','abcdefg') from Member m";
//            String query = "select size(t.members) from Team t";
            String query = "select index(t.members) from Team t";
            List<Integer> resultList = em.createQuery(query, Integer.class)
                    .getResultList();

            for (Integer result : resultList) {
                System.out.println("result = " + result);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
}
