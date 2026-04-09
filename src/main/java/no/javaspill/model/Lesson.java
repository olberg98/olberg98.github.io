package no.javaspill.model;

import java.util.List;

public class Lesson {
    private int id;
    private int moduleId;
    private String title;
    private String guideContent;
    private String codeExample;
    private String codeExampleTitle;
    private List<Exercise> exercises;

    public Lesson() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getModuleId() { return moduleId; }
    public void setModuleId(int moduleId) { this.moduleId = moduleId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGuideContent() { return guideContent; }
    public void setGuideContent(String guideContent) { this.guideContent = guideContent; }

    public String getCodeExample() { return codeExample; }
    public void setCodeExample(String codeExample) { this.codeExample = codeExample; }

    public String getCodeExampleTitle() { return codeExampleTitle; }
    public void setCodeExampleTitle(String codeExampleTitle) { this.codeExampleTitle = codeExampleTitle; }

    public List<Exercise> getExercises() { return exercises; }
    public void setExercises(List<Exercise> exercises) { this.exercises = exercises; }
}
