package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            /*Member member = new Member();
            member.setId(1L);
            member.setName("helloA");
            */
 /*           Member findMember = entityManager.find(Member.class, 1L);
            System.out.println("findMember = " + findMember.getId());
            System.out.println("findMember = " + findMember.getName());
            findMember.setName("HelloJPSA");
            System.out.println("findMember = " + findMember.getId());
            System.out.println("findMember = " + findMember.getName());*/

//            entityManager.remove(1L);entityManager.persist(member);

            //비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("hoeeoo");
            //영속
            entityManager.persist(member);
            //영속 분리 준영속
            entityManager.detach(member);
            //remove
            List<Member> findMembers = entityManager.createQuery("select m from Member as m where m.name = 'HelloJPSA'" , Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();
            for (Member members : findMembers){
                System.out.println("member.name = " + members.getName());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            entityManager.close();
        }
        emf.close();

    }
}
