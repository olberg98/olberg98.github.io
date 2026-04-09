package no.javaspill;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import no.javaspill.controller.ApiController;
import no.javaspill.data.CourseDataExporter;

public class Main {
    public static void main(String[] args) {
        // Generate courseData.js for the Android/offline version
        CourseDataExporter.export();

        ApiController api = new ApiController();

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public", Location.CLASSPATH);
        });

        app.get("/api/modules",         api::getModules);
        app.get("/api/lessons/{id}",    api::getLesson);
        app.post("/api/check",          api::checkAnswer);

        app.start(7070);

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   Java Lærlingspill kjører!          ║");
        System.out.println("║   Åpne: http://localhost:7070        ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
}
