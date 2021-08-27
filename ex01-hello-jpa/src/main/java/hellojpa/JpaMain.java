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
            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("A");
            //member.setTeamId(team.getId());
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            //연관관계가 없는 상태
//            Member findMember = em.find(Member.class, member.getId());
//            Team findTeam = em.find(Team.class, member.getTeamId());

            //연관 관계
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = em.find(Team.class, findMember.getTeam());

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
