package ma.emsi.ouazane.tp0ouazane;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("chat")
@SessionScoped
public class ChatBean implements Serializable {

    private String question;
    private String reponse;
    private String role = "helpful assistant";
    private List<String> conversation = new ArrayList<>();

    public void envoyer() {
        if (question == null || question.trim().isEmpty()) {
            reponse = "❌ Veuillez saisir une question.";
            return;
        }
        reponse = "|| " + role.toUpperCase() + ": " + question.toUpperCase() + " ||";
        conversation.add("👤 Vous : " + question);
        conversation.add("🤖 Réponse : " + reponse);
        question = "";
    }

    public String nouveauChat() {
        conversation.clear();
        question = "";
        reponse = "";
        role = "helpful assistant";
        return "index.xhtml?faces-redirect=true";
    }

    // Getters et Setters
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getReponse() { return reponse; }
    public void setReponse(String reponse) { this.reponse = reponse; }
    public List<String> getConversation() { return conversation; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}