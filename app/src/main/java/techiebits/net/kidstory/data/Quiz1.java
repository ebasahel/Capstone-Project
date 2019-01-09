package techiebits.net.kidstory.data;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quiz1 {

    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("Answers")
    @Expose
    private List<Answers> answers = null;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }

}