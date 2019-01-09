package techiebits.net.kidstory.data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Answers {

    @SerializedName("Answer1")
    @Expose
    private String answer1;
    @SerializedName("isCorrect")
    @Expose
    private Boolean isCorrect;
    @SerializedName("Answer2")
    @Expose
    private String answer2;

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

}