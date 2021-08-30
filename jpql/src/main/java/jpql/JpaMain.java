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
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

            List<MemberDTO> resultList = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();

            MemberDTO memberDTO = resultList.get(0);
            System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());

            /* List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();
            
            List<Team> result2 = em.createQuery("select m.team from Member as m", Team.class)
                    .getResultList();*/

           /* List<Object[]> resultList = em.createQuery("select m.username, m.age from Member as m")
                    .getResultList();

            Object[] result = resultList.get(0);

            System.out.println("result.username = " + result[0]);
            System.out.println("result.age = " + result[1]);*/

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
}
