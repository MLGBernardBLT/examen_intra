package persistence;

import model.Comment;
import model.Post;
import model.Utilisateur;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PostDAOH2 {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("examen");

    public <T> void save(T t){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(t);

        em.getTransaction().commit();
        em.close();
    }

    private <T> void merge(T t) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.merge(t);

        em.getTransaction().commit();
        em.close();
    }

    public Utilisateur findUser(long posteurId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final Utilisateur utilisateur = em.find(Utilisateur.class, posteurId);

        em.getTransaction().commit();
        em.close();
        return utilisateur;
    }
    public long addUser(String posteur) {
        final Utilisateur utilisateur = new Utilisateur(posteur);
        save(utilisateur);
        return utilisateur.getId();
    }
    public long addPost(Utilisateur posteur, String post) {
        final Post post1 = new Post(post);
        post1.getUtilisateurs().add(posteur);
        posteur.setPost(post1);
        save(post1);
        merge(posteur);
        return post1.getId();
    }




    public void addComment(Utilisateur commenteur, Post post, String commentaire) {
        final Comment comment = new Comment(commentaire);
        comment.setPost(post);
        post.getComments().add(comment);
        post.getUtilisateurs().add(commenteur);
        save(comment);
        merge(post);
    }


    public Post findPost(long postId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final Post post = em.find(Post.class, postId);

        em.getTransaction().commit();
        em.close();
        return post;
    }

    public Post getPost(long postId) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final Post post = em.find(Post.class, postId);

        em.getTransaction().commit();
        em.close();
        return post;
    }
}
