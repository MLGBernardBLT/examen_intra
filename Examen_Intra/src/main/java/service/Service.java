package service;
import model.Post;
import model.Utilisateur;
import persistence.PostDAOH2;

public class Service {

    private PostDAOH2 postDao;

    public Service(PostDAOH2 postDao) {
        this.postDao = postDao;
    }

    public long addPost(long posteurId, String post) {
        final Utilisateur posteur = postDao.findUser(posteurId);
        return postDao.addPost(posteur, post);
    }

    public long addUser(String posteur) {
        return postDao.addUser(posteur);
    }

    public void addComment(long commenteurId, long postId, String commentaire) {
        final Utilisateur commenteur = postDao.findUser(commenteurId);
        final Post post = postDao.findPost(postId);
        postDao.addComment(commenteur, post, commentaire);
    }

    public Post getPost(long postId) {
        return postDao.getPost(postId);

    }
}
