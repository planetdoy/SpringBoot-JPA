package jpql;

import javax.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            Member singleResult = em.createQuery("select m from Member as m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("singleResult.getUsername() = " + singleResult.getUsername());

            //try, catch
//            Member member1 = query1.getSingleResult();

            /*TypedQuery<String> query2 = em.createQuery("select m.username from Member as m", String.class);
            Query query3 = em.createQuery("select m from Member as m");*/

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
}
