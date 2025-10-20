package ma.emsi.ouazane.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ChatBean implements Serializable
{
    private String question;
    private String reponse;
    private String conversation = "";
    private String roleApi;
    private boolean roleChoisi = false;

    private List<String> rolesDisponibles;

    public ChatBean()
    {
        rolesDisponibles = new ArrayList<>();
        rolesDisponibles.add("helpful assistant");
        rolesDisponibles.add("traducteur français-anglais");
        rolesDisponibles.add("guide touristique");
    }

    public String envoyerQuestion()
    {
        if (question == null || question.trim().isEmpty())
        {
            FacesContext.getCurrentInstance().addMessage(
                    "form:questionTextArea",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Veuillez saisir une question.")
            );
            reponse = "";
            return null;
        }

        roleChoisi = true;

        String questionFormattee = "Utilisateur: " + question;
        conversation += questionFormattee + "\n";

        String reponseApi;
        if (roleApi != null && !roleApi.isEmpty())
        {
            reponseApi = "||" + roleApi.toUpperCase() + ": " + inverserCasse(question) + "||";
        } else
        {
            reponseApi = "||API: " + inverserCasse(question) + "||";
        }

        reponse = reponseApi;
        conversation += "API: " + reponseApi + "\n\n";

        question = "";
        return null;
    }

    public String nouveauChat()
    {
        return "index.xhtml?faces-redirect=true";
    }

    private String inverserCasse(String texte)
    {
        StringBuilder result = new StringBuilder();
        for (char c : texte.toCharArray())
        {
            if (Character.isUpperCase(c))
            {
                result.append(Character.toLowerCase(c));
            } else if (Character.isLowerCase(c))
            {
                result.append(Character.toUpperCase(c));
            } else
            {
                result.append(c);
            }
        }
        return result.toString();
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getReponse()
    {
        return reponse;
    }

    public void setReponse(String reponse)
    {
        this.reponse = reponse;
    }

    public String getConversation()
    {
        return conversation;
    }

    public void setConversation(String conversation)
    {
        this.conversation = conversation;
    }

    public String getRoleApi()
    {
        return roleApi;
    }

    public void setRoleApi(String roleApi)
    {
        this.roleApi = roleApi;
    }

    public boolean isRoleChoisi()
    {
        return roleChoisi;
    }

    public void setRoleChoisi(boolean roleChoisi)
    {
        this.roleChoisi = roleChoisi;
    }

    public List<String> getRolesDisponibles()
    {
        return rolesDisponibles;
    }

    public void setRolesDisponibles(List<String> rolesDisponibles)
    {
        this.rolesDisponibles = rolesDisponibles;
    }
}