package no.javaspill.model;

import java.util.List;

public class Exercise {
    private int id;
    private ExerciseType type;
    private String question;
    private String hint;

    // Multiple choice fields
    private List<String> options;
    private int correctOptionIndex;
    private String explanation;

    // Code exercise fields
    private String codeTemplate;
    private List<String> requiredPatterns;
    private String solutionCode;
    private String correctFeedback;
    private String incorrectFeedback;

    public Exercise() {}

    public boolean checkAnswer(String answer) {
        if (type == ExerciseType.MULTIPLE_CHOICE) {
            try {
                return Integer.parseInt(answer.trim()) == correctOptionIndex;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            if (requiredPatterns == null || requiredPatterns.isEmpty()) {
                return answer != null && !answer.trim().isEmpty();
            }
            for (String pattern : requiredPatterns) {
                if (!answer.contains(pattern)) {
                    return false;
                }
            }
            return true;
        }
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public ExerciseType getType() { return type; }
    public void setType(ExerciseType type) { this.type = type; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getHint() { return hint; }
    public void setHint(String hint) { this.hint = hint; }

    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }

    public int getCorrectOptionIndex() { return correctOptionIndex; }
    public void setCorrectOptionIndex(int correctOptionIndex) { this.correctOptionIndex = correctOptionIndex; }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }

    public String getCodeTemplate() { return codeTemplate; }
    public void setCodeTemplate(String codeTemplate) { this.codeTemplate = codeTemplate; }

    public List<String> getRequiredPatterns() { return requiredPatterns; }
    public void setRequiredPatterns(List<String> requiredPatterns) { this.requiredPatterns = requiredPatterns; }

    public String getSolutionCode() { return solutionCode; }
    public void setSolutionCode(String solutionCode) { this.solutionCode = solutionCode; }

    public String getCorrectFeedback() { return correctFeedback; }
    public void setCorrectFeedback(String correctFeedback) { this.correctFeedback = correctFeedback; }

    public String getIncorrectFeedback() { return incorrectFeedback; }
    public void setIncorrectFeedback(String incorrectFeedback) { this.incorrectFeedback = incorrectFeedback; }
}
