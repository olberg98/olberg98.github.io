package no.javaspill.controller;

import io.javalin.http.Context;
import no.javaspill.data.CourseData;
import no.javaspill.model.*;

import java.util.*;

public class ApiController {

    private final List<CourseModule> modules;

    public ApiController() {
        this.modules = CourseData.getModules();
    }

    public void getModules(Context ctx) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (CourseModule m : modules) {
            List<Map<String, Object>> lessonSummaries = new ArrayList<>();
            for (Lesson l : m.getLessons()) {
                lessonSummaries.add(Map.of(
                    "id", l.getId(),
                    "title", l.getTitle(),
                    "exerciseCount", l.getExercises().size()
                ));
            }
            Map<String, Object> entry = new LinkedHashMap<>();
            entry.put("id", m.getId());
            entry.put("title", m.getTitle());
            entry.put("description", m.getDescription());
            entry.put("icon", m.getIcon());
            entry.put("color", m.getColor());
            entry.put("available", m.isAvailable());
            entry.put("lessonCount", m.getLessons().size());
            entry.put("lessons", lessonSummaries);
            result.add(entry);
        }
        ctx.json(result);
    }

    public void getLesson(Context ctx) {
        int lessonId;
        try {
            lessonId = Integer.parseInt(ctx.pathParam("id"));
        } catch (NumberFormatException e) {
            ctx.status(400).result("Ugyldig lesson-id");
            return;
        }
        for (CourseModule module : modules) {
            for (Lesson lesson : module.getLessons()) {
                if (lesson.getId() == lessonId) {
                    ctx.json(lesson);
                    return;
                }
            }
        }
        ctx.status(404).result("Leksjon ikke funnet");
    }

    public void checkAnswer(Context ctx) {
        Map<?, ?> body = ctx.bodyAsClass(Map.class);
        int exerciseId = ((Number) body.get("exerciseId")).intValue();
        String answer = (String) body.get("answer");

        for (CourseModule module : modules) {
            for (Lesson lesson : module.getLessons()) {
                for (Exercise exercise : lesson.getExercises()) {
                    if (exercise.getId() == exerciseId) {
                        boolean correct = exercise.checkAnswer(answer);
                        Map<String, Object> result = new LinkedHashMap<>();
                        result.put("correct", correct);
                        result.put("feedback", correct
                            ? exercise.getCorrectFeedback()
                            : exercise.getIncorrectFeedback());
                        result.put("explanation", exercise.getExplanation());
                        result.put("solution", exercise.getSolutionCode());
                        ctx.json(result);
                        return;
                    }
                }
            }
        }
        ctx.status(404).result("Øvelse ikke funnet");
    }
}
