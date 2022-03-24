import model.Post;
import persistence.PostDAOH2;
import service.Service;

public class Main {
    public static void main(String[] args) {
        Service service = new Service(new PostDAOH2());

        var posteurId = service.addUser("Posteur1");
        var commenteurId = service.addUser("Commentateur1");
        var postId = service.addPost(posteurId, "Mon premier post");
        service.addComment(commenteurId, postId, "Mon commentaire 1");

        final Post post = service.getPost(postId);
        System.out.println(post);
    }
}
