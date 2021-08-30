package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            em.persist(team);

            Member member = new Member();
            member.setUsername("관리자입니다");
            member.setAge(10);
            member.setType(MemberType.ADMIN);

            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

//            String query = "select m.username from Member m";
            String query = "select m.username from Team t join t.members m";

            String resultList = em.createQuery(query, String.class)
                    .getSingleResult();

            System.out.println("resultList = " + resultList);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
}
