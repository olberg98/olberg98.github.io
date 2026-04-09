package no.javaspill.data;

import no.javaspill.model.*;
import java.util.List;

public class CourseDataModules13to20 {

    // ─── MODULE 13: Maven ─────────────────────────────────────────────────────

    static CourseModule createModule13(CourseData.ExIdRef r) {
        CourseModule m = new CourseModule();
        m.setId(13); m.setTitle("Maven");
        m.setDescription("Byggeverktøy, pom.xml og avhengighetsstyring.");
        m.setIcon("🔧"); m.setColor("#f97316"); m.setAvailable(true);
        m.setLessons(List.of(lesson13_1(r), lesson13_2(r)));
        return m;
    }

    static Lesson lesson13_1(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(29); l.setModuleId(13); l.setTitle("Hva er Maven?");
        l.setGuideContent("""
            <h2>Maven – automatiser byggingen</h2>
            <p>Maven er et <strong>byggeverktøy</strong> for Java. Det hjelper deg å kompilere, teste og pakke prosjektet ditt – og laste ned biblioteker automatisk.</p>
            <h3>Hva Maven gjør for deg</h3>
            <table class="data-table">
                <tr><th>Oppgave</th><th>Kommando</th></tr>
                <tr><td>Kompiler kode</td><td><code>mvn compile</code></td></tr>
                <tr><td>Kjør tester</td><td><code>mvn test</code></td></tr>
                <tr><td>Lag JAR-fil</td><td><code>mvn package</code></td></tr>
                <tr><td>Rydd opp</td><td><code>mvn clean</code></td></tr>
            </table>
            <h3>pom.xml – prosjektets konfigurasjon</h3>
            <p><code>pom.xml</code> (Project Object Model) er kjernen i et Maven-prosjekt. Her definerer du hvem prosjektet er, og hvilke biblioteker det trenger.</p>
            <div class="note">
                <strong>Grunnleggende pom.xml:</strong><br>
                <code>&lt;groupId&gt;no.mittnavn&lt;/groupId&gt;</code> – organisasjon<br>
                <code>&lt;artifactId&gt;prosjektnavn&lt;/artifactId&gt;</code> – prosjektets navn<br>
                <code>&lt;version&gt;1.0-SNAPSHOT&lt;/version&gt;</code> – versjon
            </div>
            <div class="tip">
                <strong>💡 IntelliJ og Maven</strong><br>
                Åpne pom.xml i IntelliJ → Høyreklikk → Maven → Reload Project. IntelliJ laster ned alle avhengigheter automatisk!
            </div>
            """);
        l.setCodeExampleTitle("pom.xml grunnstruktur");
        l.setCodeExample("""
            <?xml version="1.0" encoding="UTF-8"?>
            <project xmlns="http://maven.apache.org/POM/4.0.0"
                     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                     xsi:schemaLocation="...">
                <modelVersion>4.0.0</modelVersion>

                <!-- Prosjektidentifikasjon -->
                <groupId>no.minorganisasjon</groupId>
                <artifactId>mitt-prosjekt</artifactId>
                <version>1.0-SNAPSHOT</version>

                <!-- Java versjon -->
                <properties>
                    <maven.compiler.source>21</maven.compiler.source>
                    <maven.compiler.target>21</maven.compiler.target>
                    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                </properties>

                <!-- Avhengigheter (biblioteker) -->
                <dependencies>
                    <!-- Her legger du til biblioteker -->
                </dependencies>
            </project>
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva er pom.xml?",
            List.of(
                "En Java-fil som inneholder main-metoden",
                "Konfigurasjonsfilen for et Maven-prosjekt",
                "En fil som lagrer testresultater",
                "En kompilert Java-fil"
            ),
            1, "pom.xml = Project Object Model. Det er Mavens konfigurasjonsfil.",
            "Riktig! pom.xml er hjerne-filen til et Maven-prosjekt.", "pom.xml = Maven konfigurasjonsfil.");

        Exercise e2 = CourseData.mc(r,
            "Hvilken Maven-kommando kompilerer OG pakker prosjektet til en JAR-fil?",
            List.of("mvn compile", "mvn run", "mvn package", "mvn build"),
            2, "mvn package kjører compile + test + package og lager en JAR-fil i target/-mappen.",
            "Riktig! mvn package = kompiler + test + pak.", "mvn package lager JAR-filen i target/.");

        Exercise e3 = CourseData.mc(r,
            "Hvor henter Maven avhengigheter fra internett?",
            List.of("GitHub", "Maven Central (repo1.maven.org)", "npm registry", "Java.com"),
            1, "Maven Central er standardlageret for Java-biblioteker. Adressen er repo1.maven.org.",
            "Riktig! Maven Central er standard-lageret.", "Maven henter biblioteker fra Maven Central automatisk.");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson13_2(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(30); l.setModuleId(13); l.setTitle("Dependencies i pom.xml");
        l.setGuideContent("""
            <h2>Legge til biblioteker med dependencies</h2>
            <p>En <strong>dependency</strong> (avhengighet) er et eksternt bibliotek du vil bruke i prosjektet ditt. Maven laster det ned automatisk.</p>
            <h3>Struktur for en dependency</h3>
            <div class="note">
                <code>&lt;dependency&gt;</code><br>
                &nbsp;&nbsp;<code>&lt;groupId&gt;io.javalin&lt;/groupId&gt;</code><br>
                &nbsp;&nbsp;<code>&lt;artifactId&gt;javalin&lt;/artifactId&gt;</code><br>
                &nbsp;&nbsp;<code>&lt;version&gt;6.3.0&lt;/version&gt;</code><br>
                <code>&lt;/dependency&gt;</code><br><br>
                <ul>
                    <li><strong>groupId</strong> – organisasjonen bak biblioteket</li>
                    <li><strong>artifactId</strong> – bibliotekets navn</li>
                    <li><strong>version</strong> – hvilken versjon du vil bruke</li>
                </ul>
            </div>
            <div class="tip">
                <strong>💡 Finn dependencies på mvnrepository.com</strong><br>
                Søk etter biblioteket du vil bruke → velg versjon → kopier XML-en inn i &lt;dependencies&gt; i pom.xml.
            </div>
            <div class="note">
                <strong>Scope – når brukes avhengigheten?</strong><br>
                <code>compile</code> (default) – alltid<br>
                <code>test</code> – bare ved testing<br>
                <code>provided</code> – finnes allerede i serveren
            </div>
            """);
        l.setCodeExampleTitle("pom.xml med dependencies");
        l.setCodeExample("""
            <dependencies>
                <!-- Javalin – web-rammeverk -->
                <dependency>
                    <groupId>io.javalin</groupId>
                    <artifactId>javalin</artifactId>
                    <version>6.3.0</version>
                </dependency>

                <!-- Jackson – JSON-håndtering -->
                <dependency>
                    <groupId>com.fasterxml.jackson.core</groupId>
                    <artifactId>jackson-databind</artifactId>
                    <version>2.16.1</version>
                </dependency>

                <!-- SLF4J – logging (kreves av Javalin) -->
                <dependency>
                    <groupId>org.slf4j</groupId>
                    <artifactId>slf4j-simple</artifactId>
                    <version>2.0.9</version>
                </dependency>

                <!-- JUnit – testing (bare i test-scope) -->
                <dependency>
                    <groupId>org.junit.jupiter</groupId>
                    <artifactId>junit-jupiter</artifactId>
                    <version>5.10.0</version>
                    <scope>test</scope>
                </dependency>
            </dependencies>
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva identifiserer en dependency i pom.xml?",
            List.of(
                "Bare artifactId er nok",
                "groupId + artifactId + version",
                "Bare version og artifactId",
                "URL til biblioteket"
            ),
            1, "Alle tre trengs: groupId (organisasjon) + artifactId (navn) + version.",
            "Riktig! groupId + artifactId + version identifiserer en dependency.",
            "Alle tre: groupId + artifactId + version.");

        Exercise e2 = CourseData.mc(r,
            "Hva betyr scope=test på en dependency?",
            List.of(
                "Biblioteket kjøres bare om natten",
                "Biblioteket er bare tilgjengelig når du kjører tester, ikke i produksjon",
                "Biblioteket testes automatisk",
                "Biblioteket er raskere"
            ),
            1, "scope=test betyr at biblioteket kun er tilgjengelig i testkoden (src/test/), ikke i main-koden.",
            "Riktig! scope=test = kun tilgjengelig under testing.", "scope=test: kun for testklasser, ikke produksjonskode.");

        Exercise e3 = CourseData.mc(r,
            "Etter at du legger til en ny dependency i pom.xml i IntelliJ, hva bør du gjøre?",
            List.of(
                "Starte datamaskinen på nytt",
                "Klikke 'Load Maven Changes' eller høyreklikke → Maven → Reload",
                "Slette target/-mappen manuelt",
                "Ingenting – det skjer automatisk alltid"
            ),
            1, "IntelliJ oppdager endringer i pom.xml og viser en knapp for å laste inn endringene.",
            "Riktig! Trykk 'Load Maven Changes' for å laste ned nye biblioteker.",
            "Etter endring i pom.xml: Reload Maven i IntelliJ.");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── MODULE 14: Javalin og Web-API ───────────────────────────────────────

    static CourseModule createModule14(CourseData.ExIdRef r) {
        CourseModule m = new CourseModule();
        m.setId(14); m.setTitle("Javalin og Web-API");
        m.setDescription("HTTP, REST-API med Javalin, Vue og MVC-mønsteret.");
        m.setIcon("🌐"); m.setColor("#06b6d4"); m.setAvailable(true);
        m.setLessons(List.of(lesson14_1(r), lesson14_2(r), lesson14_3(r)));
        return m;
    }

    static Lesson lesson14_1(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(31); l.setModuleId(14); l.setTitle("HTTP og REST");
        l.setGuideContent("""
            <h2>HTTP og REST-API</h2>
            <p><strong>HTTP</strong> (HyperText Transfer Protocol) er protokollen nettet bruker. Når du surfer, sender nettleseren HTTP-forespørsler og får svar tilbake.</p>
            <h3>HTTP-metoder</h3>
            <table class="data-table">
                <tr><th>Metode</th><th>Bruk</th><th>Eksempel</th></tr>
                <tr><td><code>GET</code></td><td>Hent data</td><td>Vis alle brukere</td></tr>
                <tr><td><code>POST</code></td><td>Opprett ny ressurs</td><td>Lag ny bruker</td></tr>
                <tr><td><code>PUT</code></td><td>Oppdater ressurs</td><td>Endre brukerinfo</td></tr>
                <tr><td><code>DELETE</code></td><td>Slett ressurs</td><td>Slett bruker</td></tr>
            </table>
            <h3>REST – et design-prinsipp</h3>
            <p>REST (Representational State Transfer) er regler for hvordan API-er bør designes:</p>
            <div class="note">
                <code>GET    /api/brukere</code>     → hent alle brukere<br>
                <code>GET    /api/brukere/5</code>   → hent bruker med id 5<br>
                <code>POST   /api/brukere</code>     → opprett ny bruker<br>
                <code>PUT    /api/brukere/5</code>   → oppdater bruker 5<br>
                <code>DELETE /api/brukere/5</code>   → slett bruker 5
            </div>
            <div class="tip">
                <strong>💡 JSON er standard svarformat</strong><br>
                REST-API-er returnerer vanligvis JSON:<br>
                <code>{"id": 5, "navn": "Ola", "epost": "ola@test.no"}</code>
            </div>
            """);
        l.setCodeExampleTitle("HTTP-eksempel med fetch (JavaScript)");
        l.setCodeExample("""
            // Frontend (JavaScript) sender forespørsel til Java-API:

            // GET – hent alle brukere
            const svar = await fetch('/api/brukere');
            const brukere = await svar.json();

            // POST – opprett ny bruker
            await fetch('/api/brukere', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ navn: 'Ola', alder: 25 })
            });

            // DELETE – slett bruker med id 5
            await fetch('/api/brukere/5', { method: 'DELETE' });

            // ── Java-siden (Javalin) håndterer forespørslene: ──
            // app.get("/api/brukere", ctx -> ctx.json(brukerliste));
            // app.post("/api/brukere", ctx -> { ... });
            // app.delete("/api/brukere/{id}", ctx -> { ... });
            """);

        Exercise e1 = CourseData.mc(r,
            "Hvilken HTTP-metode brukes for å HENTE data fra et API?",
            List.of("POST", "DELETE", "GET", "PUT"),
            2, "GET = hent/les data. Ingen endringer gjøres på serveren.",
            "Riktig! GET brukes for å hente data.", "GET = hent, POST = opprett, PUT = oppdater, DELETE = slett.");

        Exercise e2 = CourseData.mc(r,
            "Hva er et REST-API?",
            List.of(
                "Et Java-bibliotek for webservere",
                "Et sett med design-prinsipper for HTTP-API-er som bruker URL-er og HTTP-metoder",
                "En type database",
                "Et testverktøy"
            ),
            1, "REST er design-prinsipper for API-er. Ikke et bibliotek eller protokoll i seg selv.",
            "Riktig! REST er prinsipper for API-design.", "REST = design-prinsipper: bruk HTTP-metoder og URL-er logisk.");

        Exercise e3 = CourseData.mc(r,
            "Hvilken URL-struktur følger REST best for å hente bruker med id 42?",
            List.of(
                "/getBruker?id=42",
                "/api/brukere/42",
                "/api/hentBrukerMedId/42",
                "/bruker-liste/42"
            ),
            1, "REST bruker ressursnavn i flertall + id: /api/brukere/42.",
            "Riktig! /api/brukere/42 er riktig REST-URL.", "REST URL: /api/ressursnavn/id");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson14_2(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(32); l.setModuleId(14); l.setTitle("Javalin – lag et REST-API");
        l.setGuideContent("""
            <h2>Javalin – enkelt web-rammeverk for Java</h2>
            <p>Javalin er et lettvekts-rammeverk for å lage web-applikasjoner og REST-API-er i Java.</p>
            <h3>Grunnleggende oppsett</h3>
            <div class="note">
                <code>Javalin app = Javalin.create().start(7070);</code><br><br>
                <code>app.get("/hei", ctx -> {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>ctx.result("Hei verden!");</code><br>
                <code>});</code>
            </div>
            <h3>Context-objektet (ctx)</h3>
            <table class="data-table">
                <tr><th>Metode</th><th>Hva den gjør</th></tr>
                <tr><td><code>ctx.result("tekst")</code></td><td>Send tekstsvar</td></tr>
                <tr><td><code>ctx.json(objekt)</code></td><td>Send JSON-svar</td></tr>
                <tr><td><code>ctx.pathParam("id")</code></td><td>Les URL-parameter (/api/ting/5)</td></tr>
                <tr><td><code>ctx.bodyAsClass(Klasse.class)</code></td><td>Les JSON-kropp som objekt</td></tr>
                <tr><td><code>ctx.status(404)</code></td><td>Sett HTTP-statuskode</td></tr>
            </table>
            <div class="tip">
                <strong>💡 Statiske filer</strong><br>
                Javalin kan servere HTML/CSS/JS fra <code>src/main/resources/public/</code>:<br>
                <code>config.staticFiles.add("/public", Location.CLASSPATH);</code>
            </div>
            """);
        l.setCodeExampleTitle("Komplett Javalin REST-API");
        l.setCodeExample("""
            import io.javalin.Javalin;
            import java.util.ArrayList;
            import java.util.List;

            public class Server {
                static List<String> oppgaver = new ArrayList<>();

                public static void main(String[] args) {
                    Javalin app = Javalin.create().start(7070);

                    // GET – hent alle oppgaver
                    app.get("/api/oppgaver", ctx -> {
                        ctx.json(oppgaver);
                    });

                    // POST – legg til oppgave
                    app.post("/api/oppgaver", ctx -> {
                        String ny = ctx.body();
                        oppgaver.add(ny);
                        ctx.status(201).result("Oppgave lagt til");
                    });

                    // DELETE – slett oppgave på index
                    app.delete("/api/oppgaver/{index}", ctx -> {
                        int idx = Integer.parseInt(ctx.pathParam("index"));
                        if (idx >= 0 && idx < oppgaver.size()) {
                            oppgaver.remove(idx);
                            ctx.result("Slettet");
                        } else {
                            ctx.status(404).result("Ikke funnet");
                        }
                    });
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva gjør ctx.json(objekt) i Javalin?",
            List.of(
                "Lagrer objektet til fil",
                "Konverterer objektet til JSON og sender det som HTTP-svar",
                "Leser JSON fra forespørselen",
                "Validerer JSON-formatet"
            ),
            1, "ctx.json() serialiserer Java-objektet til JSON og sender det som respons.",
            "Riktig! ctx.json() sender objektet som JSON-svar.", "ctx.json(obj) = konverter objekt → JSON og send som svar.");

        Exercise e2 = CourseData.mc(r,
            "Hvordan henter du path-parameter fra URL /api/biler/42 i Javalin?",
            List.of(
                "ctx.getParam(\"42\")",
                "ctx.pathParam(\"id\")",
                "ctx.body(\"id\")",
                "ctx.queryParam(\"id\")"
            ),
            1, "Ruten defineres som /api/biler/{id} og leses med ctx.pathParam(\"id\").",
            "Riktig! ctx.pathParam(\"id\") leser {id} fra URL.", "app.get(\"/api/biler/{id}\", ...) + ctx.pathParam(\"id\")");

        Exercise e3 = CourseData.code(r,
            "Skriv en Javalin GET-rute på /api/ping som returnerer teksten \"pong\".",
            "app.get(\"/api/ping\", ctx -> ctx.result(\"pong\"));",
            "Javalin app", List.of("app.get", "/api/ping", "ctx.result", "pong"),
            "import io.javalin.Javalin;\npublic class PingServer {\n    public static void main(String[] args) {\n        Javalin app = Javalin.create().start(7070);\n        app.get(\"/api/ping\", ctx -> {\n            ctx.result(\"pong\");\n        });\n    }\n}",
            "Flott! Du kan nå lage Javalin-ruter! 🌐",
            "app.get(\"/api/ping\", ctx -> ctx.result(\"pong\"));");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson14_3(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(33); l.setModuleId(14); l.setTitle("Vue og MVC");
        l.setGuideContent("""
            <h2>Vue.js og MVC-arkitektur</h2>
            <h3>MVC – Model-View-Controller</h3>
            <p>MVC er et arkitekturmønster som deler koden inn i tre lag:</p>
            <table class="data-table">
                <tr><th>Lag</th><th>Ansvar</th><th>Eksempel</th></tr>
                <tr><td><strong>Model</strong></td><td>Data og forretningslogikk</td><td>Bruker.java, BrukerDAO.java</td></tr>
                <tr><td><strong>View</strong></td><td>Hva brukeren ser</td><td>HTML/Vue-komponent</td></tr>
                <tr><td><strong>Controller</strong></td><td>Kobler View og Model</td><td>BrukerController.java (Javalin)</td></tr>
            </table>
            <h3>Vue.js – reaktivt frontend-rammeverk</h3>
            <p>Vue.js er et JavaScript-rammeverk for å bygge dynamiske nettsider. Det kobles til Java-backenden via <code>fetch</code>.</p>
            <div class="note">
                <code>&lt;div id="app"&gt;</code><br>
                &nbsp;&nbsp;<code>{{ melding }}</code><br>
                <code>&lt;/div&gt;</code><br><br>
                <code>const app = Vue.createApp({</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>data() { return { melding: 'Hei!' } }</code><br>
                <code}).mount('#app');</code>
            </div>
            <div class="tip">
                <strong>💡 Flyten i en fullstack-app</strong><br>
                1. Bruker klikker knapp i Vue<br>
                2. Vue sender fetch-forespørsel til Javalin (Java)<br>
                3. Javalin henter data fra Model<br>
                4. Javalin returnerer JSON<br>
                5. Vue viser dataene
            </div>
            """);
        l.setCodeExampleTitle("Vue + Javalin fullstack");
        l.setCodeExample("""
            // === Java backend (Javalin) ===
            // BrukerController.java
            app.get("/api/brukere", ctx -> {
                ctx.json(brukerService.getAlleBrukere());
            });

            // === JavaScript frontend (Vue) ===
            // App.vue eller index.html med Vue CDN
            const app = Vue.createApp({
                data() {
                    return {
                        brukere: [],
                        lastError: null
                    };
                },
                async mounted() {
                    // Hent data fra Java-API når siden lastes
                    const res = await fetch('/api/brukere');
                    this.brukere = await res.json();
                },
                methods: {
                    async slettBruker(id) {
                        await fetch('/api/brukere/' + id, { method: 'DELETE' });
                        this.brukere = this.brukere.filter(b => b.id !== id);
                    }
                },
                template: `
                    <ul>
                        <li v-for="bruker in brukere" :key="bruker.id">
                            {{ bruker.navn }}
                            <button @click="slettBruker(bruker.id)">Slett</button>
                        </li>
                    </ul>
                `
            }).mount('#app');
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva er ansvaret til 'Controller' i MVC?",
            List.of(
                "Vise data til brukeren",
                "Lagre data i databasen",
                "Ta imot forespørsler og koordinere mellom Model og View",
                "Definere farger og layout"
            ),
            2, "Controller er mellomlaget som tar imot input, henter data fra Model, og sender til View.",
            "Riktig! Controller koordinerer mellom Model og View.", "Controller = mellomledd. Tar imot forespørsel → hent data → send til View.");

        Exercise e2 = CourseData.mc(r,
            "Hva gjør fetch('/api/brukere') i JavaScript?",
            List.of(
                "Kjører Java-kode direkte",
                "Sender en HTTP GET-forespørsel til Java-backenden",
                "Lagrer data lokalt i nettleseren",
                "Oppdaterer databasen"
            ),
            1, "fetch() sender en HTTP-forespørsel til serveren. Standard er GET.",
            "Riktig! fetch() sender HTTP-forespørsel til Java-API.", "fetch('/api/brukere') = HTTP GET til Java-backenden.");

        Exercise e3 = CourseData.mc(r,
            "Hva hører 'Bruker.java' med felter og getters/setters til i MVC?",
            List.of("View", "Controller", "Model", "Repository"),
            2, "Model inneholder dataklassene. Bruker.java representerer datastrukturen.",
            "Riktig! Bruker.java er en Model-klasse.", "Model = dataklasser. Bruker.java med felter = Model.");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── MODULE 15: Lambda ────────────────────────────────────────────────────

    static CourseModule createModule15(CourseData.ExIdRef r) {
        CourseModule m = new CourseModule();
        m.setId(15); m.setTitle("Lambda");
        m.setDescription("Lambda-uttrykk og Stream API for funksjonell Java-kode.");
        m.setIcon("λ"); m.setColor("#a855f7"); m.setAvailable(true);
        m.setLessons(List.of(lesson15_1(r), lesson15_2(r)));
        return m;
    }

    static Lesson lesson15_1(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(34); l.setModuleId(15); l.setTitle("Lambda-uttrykk");
        l.setGuideContent("""
            <h2>Lambda – anonyme funksjoner</h2>
            <p>Et lambda-uttrykk er en kompakt måte å skrive en liten funksjon på, uten å lage en hel klasse eller metode.</p>
            <h3>Syntaks</h3>
            <div class="note">
                <code>(parametere) -&gt; { kropp }</code><br><br>
                Enkelt uttrykk (trenger ikke klammeparenteser):<br>
                <code>(a, b) -&gt; a + b</code><br><br>
                Flere linjer:<br>
                <code>(a, b) -&gt; {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>int sum = a + b;</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>return sum;</code><br>
                <code>}</code>
            </div>
            <h3>Bruk med Comparator og sortering</h3>
            <div class="note">
                <strong>Gammel måte:</strong><br>
                <code>liste.sort(new Comparator&lt;String&gt;() { ... });</code><br><br>
                <strong>Med lambda:</strong><br>
                <code>liste.sort((a, b) -&gt; a.compareTo(b));</code><br>
                <code>liste.sort(String::compareTo); // enda kortere!</code>
            </div>
            <div class="tip">
                <strong>💡 Funksjonelt grensesnitt</strong><br>
                Lambda kan bare brukes der det forventes et <em>funksjonelt grensesnitt</em> – et interface med bare én metode. Eksempler: <code>Runnable</code>, <code>Comparator</code>, <code>Predicate</code>.
            </div>
            """);
        l.setCodeExampleTitle("Lambda-uttrykk i praksis");
        l.setCodeExample("""
            import java.util.*;

            public class LambdaEksempel {
                public static void main(String[] args) {
                    List<String> navn = new ArrayList<>(List.of("Ola", "Kari", "Per", "Åse"));

                    // Sorter alfabetisk med lambda
                    navn.sort((a, b) -> a.compareTo(b));
                    System.out.println(navn);  // [Kari, Ola, Per, Åse]

                    // Korteste navn først
                    navn.sort((a, b) -> a.length() - b.length());
                    System.out.println(navn);  // [Ola, Per, Kari, Åse]

                    // forEach med lambda
                    navn.forEach(n -> System.out.println("Hei, " + n));

                    // Runnable med lambda
                    Runnable hils = () -> System.out.println("Hei fra tråd!");
                    new Thread(hils).start();
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva er et lambda-uttrykk?",
            List.of(
                "En type variabel",
                "En kompakt anonym funksjon skrevet med ->",
                "Et nøkkelord for løkker",
                "En type klasse"
            ),
            1, "Lambda = kompakt anonym funksjon. Syntaks: (param) -> { kropp }",
            "Riktig! Lambda = anonym funksjon med ->", "Lambda: (params) -> uttrykk eller { kode }");

        Exercise e2 = CourseData.mc(r,
            "Hva er riktig syntaks for et lambda som tar to ints og returnerer summen?",
            List.of(
                "int(a, b) => a + b",
                "(a, b) -> a + b",
                "lambda(a, b) { return a + b; }",
                "(int a, int b) => { return a + b; }"
            ),
            1, "Java lambda: (a, b) -> a + b. Pilen er -> og typer kan utelates.",
            "Riktig! (a, b) -> a + b er riktig Java-lambda.", "Java lambda: (params) -> uttrykk");

        Exercise e3 = CourseData.code(r,
            "Bruk forEach med lambda til å skrive ut alle elementer i listen: List.of(\"Eple\", \"Banan\", \"Pære\").",
            "liste.forEach(element -> System.out.println(element));",
            "public class", List.of("forEach", "->", "System.out.println"),
            "import java.util.List;\npublic class FrukterLambda {\n    public static void main(String[] args) {\n        List<String> frukter = List.of(\"Eple\", \"Banan\", \"Pære\");\n        frukter.forEach(f -> System.out.println(f));\n    }\n}",
            "Flott! Du kan nå bruke lambda med forEach! λ",
            "liste.forEach(element -> System.out.println(element));");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson15_2(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(35); l.setModuleId(15); l.setTitle("Stream API");
        l.setGuideContent("""
            <h2>Stream API – funksjonelle operasjoner på lister</h2>
            <p>Stream API lar deg prosessere samlinger (lister, arrays) på en elegant funksjonell måte ved hjelp av lambda-uttrykk.</p>
            <div class="note">
                <strong>Grunnstruktur:</strong><br>
                <code>liste.stream()  // lag en stream</code><br>
                <code>&nbsp;&nbsp;&nbsp;.filter(...)   // filtrer elementer</code><br>
                <code>&nbsp;&nbsp;&nbsp;.map(...)      // transformer hvert element</code><br>
                <code>&nbsp;&nbsp;&nbsp;.collect(Collectors.toList()); // samle resultatet</code>
            </div>
            <h3>Viktige operasjoner</h3>
            <table class="data-table">
                <tr><th>Metode</th><th>Hva den gjør</th></tr>
                <tr><td><code>.filter(pred)</code></td><td>Beholder bare elementer som oppfyller betingelsen</td></tr>
                <tr><td><code>.map(func)</code></td><td>Transformer hvert element</td></tr>
                <tr><td><code>.sorted()</code></td><td>Sorter</td></tr>
                <tr><td><code>.count()</code></td><td>Tell antall</td></tr>
                <tr><td><code>.findFirst()</code></td><td>Hent første element</td></tr>
                <tr><td><code>.collect(...)</code></td><td>Samle til liste, sett, etc.</td></tr>
            </table>
            <div class="tip">
                <strong>💡 Stream endrer ikke originallisten!</strong><br>
                Du får alltid tilbake et nytt resultat. Originalen er uendret.
            </div>
            """);
        l.setCodeExampleTitle("Stream API eksempler");
        l.setCodeExample("""
            import java.util.*;
            import java.util.stream.*;

            public class StreamEksempel {
                public static void main(String[] args) {
                    List<Integer> tall = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

                    // Filter: bare partall
                    List<Integer> partall = tall.stream()
                        .filter(n -> n % 2 == 0)
                        .collect(Collectors.toList());
                    System.out.println(partall);  // [2, 4, 6, 8, 10]

                    // Map: gang alle med 2
                    List<Integer> doblet = tall.stream()
                        .map(n -> n * 2)
                        .collect(Collectors.toList());
                    System.out.println(doblet);  // [2, 4, 6, 8, ...]

                    // Kombiner filter + map + count
                    long antallStorePartall = tall.stream()
                        .filter(n -> n % 2 == 0)
                        .filter(n -> n > 5)
                        .count();
                    System.out.println(antallStorePartall);  // 3 (6, 8, 10)

                    // Navn som starter med K
                    List<String> navn = List.of("Kari", "Ola", "Kristian", "Per");
                    navn.stream()
                        .filter(n -> n.startsWith("K"))
                        .forEach(System.out::println);  // Kari, Kristian
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva gjør .filter() i Stream API?",
            List.of(
                "Sorterer elementene",
                "Beholder bare elementer som oppfyller en betingelse",
                "Transformer hvert element til noe annet",
                "Teller elementer"
            ),
            1, "filter() tar et Predicate (betingelse) og beholder bare elementene der betingelsen er true.",
            "Riktig! filter() beholder elementer som matcher betingelsen.", "filter(pred) = behold bare der pred er true.");

        Exercise e2 = CourseData.mc(r,
            "Hva skriver dette ut?\nList<Integer> t = List.of(1,2,3,4);\nlong n = t.stream().filter(x -> x > 2).count();\nSystem.out.println(n);",
            List.of("2", "3", "4", "1"),
            0, "filter(x -> x > 2) beholder 3 og 4. count() teller dem = 2.",
            "Riktig! 3 og 4 er > 2, så count = 2.", "x > 2 gir {3, 4} → count = 2.");

        Exercise e3 = CourseData.code(r,
            "Bruk Stream og filter til å beholde bare strenger lengre enn 3 tegn fra listen [\"Ola\", \"Kari\", \"Per\", \"Lise\"].",
            "liste.stream().filter(s -> s.length() > 3).collect(Collectors.toList())",
            "import java.util", List.of("stream()", "filter", "length()", "collect"),
            "import java.util.*;\nimport java.util.stream.*;\npublic class FilterNavn {\n    public static void main(String[] args) {\n        List<String> navn = List.of(\"Ola\", \"Kari\", \"Per\", \"Lise\");\n        List<String> res = navn.stream()\n            .filter(s -> s.length() > 3)\n            .collect(Collectors.toList());\n        System.out.println(res);\n    }\n}",
            "Utmerket! Du behersker Stream API! 🌊",
            "stream().filter(s -> s.length() > 3).collect(Collectors.toList())");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── MODULE 16: Databaser ─────────────────────────────────────────────────

    static CourseModule createModule16(CourseData.ExIdRef r) {
        CourseModule m = new CourseModule();
        m.setId(16); m.setTitle("Databaser");
        m.setDescription("SQL-grunnleggende og JDBC for å koble Java til en database.");
        m.setIcon("🗄️"); m.setColor("#64748b"); m.setAvailable(true);
        m.setLessons(List.of(lesson16_1(r), lesson16_2(r)));
        return m;
    }

    static Lesson lesson16_1(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(36); l.setModuleId(16); l.setTitle("SQL grunnleggende");
        l.setGuideContent("""
            <h2>SQL – språket for databaser</h2>
            <p>SQL (Structured Query Language) er språket du bruker for å kommunisere med relasjonsdatabaser som SQLite, PostgreSQL og MySQL.</p>
            <h3>CRUD – de fire grunnoperasjonene</h3>
            <table class="data-table">
                <tr><th>Operasjon</th><th>SQL-kommando</th><th>Eksempel</th></tr>
                <tr><td>Create</td><td>INSERT</td><td>Legg til ny rad</td></tr>
                <tr><td>Read</td><td>SELECT</td><td>Hent rader</td></tr>
                <tr><td>Update</td><td>UPDATE</td><td>Endre eksisterende rad</td></tr>
                <tr><td>Delete</td><td>DELETE</td><td>Slett rad</td></tr>
            </table>
            <div class="note">
                <strong>Lag en tabell:</strong><br>
                <code>CREATE TABLE studenter (</code><br>
                &nbsp;&nbsp;<code>id INTEGER PRIMARY KEY AUTOINCREMENT,</code><br>
                &nbsp;&nbsp;<code>navn TEXT NOT NULL,</code><br>
                &nbsp;&nbsp;<code>alder INTEGER</code><br>
                <code>);</code>
            </div>
            <div class="note">
                <strong>Grunnleggende SQL:</strong><br>
                <code>SELECT * FROM studenter;</code><br>
                <code>SELECT navn FROM studenter WHERE alder &gt; 20;</code><br>
                <code>INSERT INTO studenter (navn, alder) VALUES ('Ola', 22);</code><br>
                <code>UPDATE studenter SET alder = 23 WHERE navn = 'Ola';</code><br>
                <code>DELETE FROM studenter WHERE id = 1;</code>
            </div>
            """);
        l.setCodeExampleTitle("SQL i praksis");
        l.setCodeExample("""
            -- Lag tabell
            CREATE TABLE produkter (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                navn TEXT NOT NULL,
                pris REAL,
                lager INTEGER DEFAULT 0
            );

            -- Legg til data
            INSERT INTO produkter (navn, pris, lager) VALUES ('Kaffe', 49.90, 100);
            INSERT INTO produkter (navn, pris, lager) VALUES ('Te', 29.90, 50);
            INSERT INTO produkter (navn, pris, lager) VALUES ('Juice', 19.90, 75);

            -- Hent alle produkter
            SELECT * FROM produkter;

            -- Hent bare billige produkter
            SELECT navn, pris FROM produkter WHERE pris < 40;

            -- Sorter etter pris
            SELECT * FROM produkter ORDER BY pris ASC;

            -- Oppdater lager
            UPDATE produkter SET lager = 200 WHERE navn = 'Kaffe';

            -- Slett produkt
            DELETE FROM produkter WHERE id = 2;

            -- Tell antall produkter
            SELECT COUNT(*) FROM produkter;
            """);

        Exercise e1 = CourseData.mc(r,
            "Hvilken SQL-kommando brukes for å HENTE data fra en tabell?",
            List.of("GET", "FETCH", "SELECT", "READ"),
            2, "SELECT er SQL-kommandoen for å hente data. SELECT * FROM tabell henter alle kolonner.",
            "Riktig! SELECT brukes for å hente data.", "SQL: SELECT * FROM tabellnavn;");

        Exercise e2 = CourseData.mc(r,
            "Hva er en PRIMARY KEY i en database?",
            List.of(
                "Den viktigste kolonnen i tabellen",
                "En unik identifikator for hver rad i tabellen",
                "En kolonne som ikke kan ha verdier",
                "En type passord"
            ),
            1, "PRIMARY KEY identifiserer unikt hver rad. Kan aldri ha duplikater eller NULL.",
            "Riktig! PRIMARY KEY = unik identifikator per rad.", "PRIMARY KEY: unik per rad, typisk id INTEGER PRIMARY KEY AUTOINCREMENT.");

        Exercise e3 = CourseData.mc(r,
            "Hva gjør WHERE-klausulen i SQL?",
            List.of(
                "Sorterer resultatet",
                "Filtrerer rader basert på en betingelse",
                "Begrenser antall kolonner",
                "Lager en ny tabell"
            ),
            1, "WHERE filtrerer rader, akkurat som filter() i Stream. WHERE alder > 18.",
            "Riktig! WHERE filtrerer rader basert på betingelse.", "WHERE = filtrer rader. SELECT * FROM t WHERE betingelse;");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson16_2(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(37); l.setModuleId(16); l.setTitle("JDBC – koble Java til database");
        l.setGuideContent("""
            <h2>JDBC – Java Database Connectivity</h2>
            <p>JDBC er Java sitt innebygde API for å kommunisere med databaser. Det fungerer med de fleste relasjonsdatabaser.</p>
            <h3>Steg for databasetilkobling</h3>
            <div class="note">
                <strong>1. Koble til:</strong><br>
                <code>Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db");</code><br><br>
                <strong>2. Lag en spørring:</strong><br>
                <code>PreparedStatement ps = conn.prepareStatement("SELECT * FROM studenter WHERE alder > ?");</code><br>
                <code>ps.setInt(1, 18);</code><br><br>
                <strong>3. Kjør og les resultat:</strong><br>
                <code>ResultSet rs = ps.executeQuery();</code><br>
                <code>while (rs.next()) {</code><br>
                &nbsp;&nbsp;<code>System.out.println(rs.getString("navn"));</code><br>
                <code>}</code>
            </div>
            <div class="tip">
                <strong>⚠️ Bruk alltid PreparedStatement!</strong><br>
                <strong>Aldri:</strong> <code>"SELECT * FROM t WHERE id = " + input</code><br>
                Dette er sårbart for SQL-injeksjon (en sikkerhetsrisiko!).<br>
                <strong>Alltid:</strong> bruk <code>?</code> som placeholder og <code>setString()</code>/<code>setInt()</code>.
            </div>
            """);
        l.setCodeExampleTitle("JDBC med SQLite");
        l.setCodeExample("""
            import java.sql.*;

            public class DatabaseEksempel {
                private static final String URL = "jdbc:sqlite:studenter.db";

                public static void main(String[] args) {
                    // Legg til student
                    leggTil("Ola Nordmann", 22);
                    leggTil("Kari Hansen", 20);

                    // Hent alle
                    hentAlle();
                }

                static void leggTil(String navn, int alder) {
                    String sql = "INSERT INTO studenter (navn, alder) VALUES (?, ?)";
                    try (Connection c = DriverManager.getConnection(URL);
                         PreparedStatement ps = c.prepareStatement(sql)) {
                        ps.setString(1, navn);
                        ps.setInt(2, alder);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("Feil: " + e.getMessage());
                    }
                }

                static void hentAlle() {
                    try (Connection c = DriverManager.getConnection(URL);
                         Statement s = c.createStatement();
                         ResultSet rs = s.executeQuery("SELECT * FROM studenter")) {
                        while (rs.next()) {
                            System.out.println(rs.getInt("id") + ": " +
                                rs.getString("navn") + " (" + rs.getInt("alder") + ")");
                        }
                    } catch (SQLException e) {
                        System.out.println("Feil: " + e.getMessage());
                    }
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva er JDBC?",
            List.of(
                "Et Java-rammeverk for nettsider",
                "Javas innebygde API for å kommunisere med databaser",
                "Et SQL-dialekt for Java",
                "En type database"
            ),
            1, "JDBC = Java Database Connectivity. Standard Java-API for databasekommunikasjon.",
            "Riktig! JDBC er Javas standard database-API.", "JDBC = Java Database Connectivity.");

        Exercise e2 = CourseData.mc(r,
            "Hvorfor skal du bruke PreparedStatement i stedet for å sette inn brukerdata direkte i SQL-strengen?",
            List.of(
                "PreparedStatement er raskere",
                "Det ser penere ut",
                "For å beskytte mot SQL-injeksjon – en kritisk sikkerhetssårbarhet",
                "PreparedStatement virker bare med SQLite"
            ),
            2, "SQL-injeksjon: ondsinnet input kan manipulere SQL-spørringen. PreparedStatement escaper input automatisk.",
            "Riktig! PreparedStatement beskytter mot SQL-injeksjon.", "Bruk alltid PreparedStatement med ? for brukerdata.");

        Exercise e3 = CourseData.mc(r,
            "Hva returnerer ps.executeQuery()?",
            List.of("En int med antall rader", "Et ResultSet med radene som matcher", "En String", "void"),
            1, "executeQuery() returnerer et ResultSet som du itererer med rs.next().",
            "Riktig! executeQuery() returnerer et ResultSet.", "executeQuery() → ResultSet. rs.next() for å gå gjennom rader.");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── MODULE 17: Collection API ────────────────────────────────────────────

    static CourseModule createModule17(CourseData.ExIdRef r) {
        CourseModule m = new CourseModule();
        m.setId(17); m.setTitle("Collection API");
        m.setDescription("List, LinkedList, Queue, Stack, HashMap og Set.");
        m.setIcon("📦"); m.setColor("#0ea5e9"); m.setAvailable(true);
        m.setLessons(List.of(lesson17_1(r), lesson17_2(r)));
        return m;
    }

    static Lesson lesson17_1(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(38); l.setModuleId(17); l.setTitle("List, Queue og Stack");
        l.setGuideContent("""
            <h2>List, Queue og Stack</h2>
            <p>Java har mange datastrukturer for ulike behov. Alle implementerer Collection-grensesnittet.</p>
            <h3>LinkedList vs ArrayList</h3>
            <table class="data-table">
                <tr><th></th><th>ArrayList</th><th>LinkedList</th></tr>
                <tr><td>Raskt oppslag (get)</td><td>✅ O(1)</td><td>❌ O(n)</td></tr>
                <tr><td>Raskt innsetting i midten</td><td>❌ O(n)</td><td>✅ O(1)</td></tr>
                <tr><td>Minne</td><td>Mer effektivt</td><td>Mer overhead</td></tr>
            </table>
            <h3>Queue – kø (FIFO)</h3>
            <div class="note">
                FIFO = First In, First Out. Tenk butikkkø.<br>
                <code>Queue&lt;String&gt; kø = new LinkedList&lt;&gt;();</code><br>
                <code>kø.add("Ola");    // legg bak</code><br>
                <code>kø.poll();        // ta fra fronten</code>
            </div>
            <h3>Stack – stabel (LIFO)</h3>
            <div class="note">
                LIFO = Last In, First Out. Tenk en stabel med tallerkener.<br>
                <code>Stack&lt;Integer&gt; stabel = new Stack&lt;&gt;();</code><br>
                <code>stabel.push(1); stabel.push(2);</code><br>
                <code>stabel.pop();   // returnerer 2 (siste inn)</code><br>
                <code>stabel.peek();  // ser på toppen uten å ta</code>
            </div>
            """);
        l.setCodeExampleTitle("Queue og Stack");
        l.setCodeExample("""
            import java.util.*;

            public class DatastruktEksempel {
                public static void main(String[] args) {
                    // Queue (FIFO) – behandle i rekkefølge
                    Queue<String> printKø = new LinkedList<>();
                    printKø.add("Dokument1");
                    printKø.add("Dokument2");
                    printKø.add("Dokument3");

                    while (!printKø.isEmpty()) {
                        System.out.println("Printer: " + printKø.poll());
                    }

                    // Stack (LIFO) – angre-funksjon
                    Stack<String> angre = new Stack<>();
                    angre.push("Skriv A");
                    angre.push("Slett B");
                    angre.push("Flytt C");

                    System.out.println("Angrer: " + angre.pop()); // Flytt C
                    System.out.println("Angrer: " + angre.pop()); // Slett B
                    System.out.println("Neste angre: " + angre.peek()); // Skriv A (fjerner ikke)
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva betyr FIFO?",
            List.of(
                "Fast In, Fast Out",
                "First In, First Out – første inn er første ut (kø)",
                "First In, Final Out",
                "Free Input, Free Output"
            ),
            1, "FIFO = First In First Out. Som en butikkkø – første i køen er første ut.",
            "Riktig! FIFO = First In, First Out.", "FIFO = kø. Første element inn er første ut.");

        Exercise e2 = CourseData.mc(r,
            "Du bruker en Stack og kaller push(1), push(2), push(3), pop(). Hva returnerer pop()?",
            List.of("1", "2", "3", "Null"),
            2, "Stack er LIFO – siste inn er første ut. push(3) la til 3 sist, så pop() returnerer 3.",
            "Riktig! LIFO: siste inn (3) er første ut.", "Stack LIFO: push(1),push(2),push(3) → pop() = 3.");

        Exercise e3 = CourseData.code(r,
            "Lag en Stack<String>, legg til \"A\", \"B\", \"C\", og skriv ut det som pop() returnerer.",
            "Stack<String> s = new Stack<>(); s.push(\"A\"); s.push(\"B\"); s.push(\"C\"); System.out.println(s.pop());",
            "import java.util", List.of("Stack", "push(", "pop()", "System.out.println"),
            "import java.util.Stack;\npublic class StackTest {\n    public static void main(String[] args) {\n        Stack<String> s = new Stack<>();\n        s.push(\"A\");\n        s.push(\"B\");\n        s.push(\"C\");\n        System.out.println(s.pop());\n    }\n}",
            "Bra! Du kan nå bruke Stack! 📚",
            "Stack<String> s = new Stack<>(); push A, B, C, deretter pop().");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson17_2(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(39); l.setModuleId(17); l.setTitle("HashMap og Set");
        l.setGuideContent("""
            <h2>HashMap og Set</h2>
            <h3>HashMap – nøkkel-verdi par</h3>
            <p>HashMap lagrer data som nøkkel-verdi par, som en ordbok. Oppslag er veldig raskt (O(1)).</p>
            <div class="note">
                <code>HashMap&lt;String, Integer&gt; aldre = new HashMap&lt;&gt;();</code><br>
                <code>aldre.put("Ola", 25);</code><br>
                <code>aldre.put("Kari", 22);</code><br>
                <code>int olaAlder = aldre.get("Ola"); // 25</code><br>
                <code>aldre.containsKey("Per");         // false</code>
            </div>
            <h3>Gå gjennom HashMap</h3>
            <div class="note">
                <code>for (Map.Entry&lt;String, Integer&gt; e : aldre.entrySet()) {</code><br>
                &nbsp;&nbsp;<code>System.out.println(e.getKey() + ": " + e.getValue());</code><br>
                <code>}</code>
            </div>
            <h3>HashSet – unike elementer</h3>
            <div class="note">
                <code>HashSet&lt;String&gt; unike = new HashSet&lt;&gt;();</code><br>
                <code>unike.add("Ola"); unike.add("Kari"); unike.add("Ola"); // duplikat!</code><br>
                <code>System.out.println(unike.size()); // 2, ikke 3!</code>
            </div>
            <div class="tip">
                <strong>💡 Set vs List</strong><br>
                List: tillater duplikater, beholder rekkefølge.<br>
                Set: ingen duplikater, ingen garantert rekkefølge.
            </div>
            """);
        l.setCodeExampleTitle("HashMap og HashSet");
        l.setCodeExample("""
            import java.util.*;

            public class MapOgSet {
                public static void main(String[] args) {
                    // HashMap – ordbok over lande-koder
                    HashMap<String, String> land = new HashMap<>();
                    land.put("NO", "Norge");
                    land.put("SE", "Sverige");
                    land.put("DK", "Danmark");

                    System.out.println(land.get("NO"));          // Norge
                    System.out.println(land.containsKey("UK"));  // false
                    System.out.println(land.size());             // 3

                    // Gå gjennom alle
                    for (Map.Entry<String, String> e : land.entrySet()) {
                        System.out.println(e.getKey() + " = " + e.getValue());
                    }

                    // HashSet – fjern duplikater
                    List<String> medDuplikater = List.of("Eple", "Banan", "Eple", "Pære", "Banan");
                    Set<String> unike = new HashSet<>(medDuplikater);
                    System.out.println(unike);  // [Eple, Banan, Pære] (rekkefølge varierer)
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva skriver dette ut?\nHashMap<String,Integer> m = new HashMap<>();\nm.put(\"a\", 1); m.put(\"b\", 2);\nSystem.out.println(m.get(\"b\"));",
            List.of("1", "\"b\"", "2", "null"),
            2, "get(\"b\") returnerer verdien knyttet til nøkkelen \"b\", som er 2.",
            "Riktig! m.get(\"b\") = 2.", "HashMap.get(nøkkel) = verdien for den nøkkelen.");

        Exercise e2 = CourseData.mc(r,
            "Kan et HashSet inneholde duplikater?",
            List.of(
                "Ja, ubegrenset",
                "Ja, men maks to av hvert",
                "Nei, duplikater ignoreres automatisk",
                "Bare hvis du bruker add() to ganger"
            ),
            2, "HashSet sikrer unikhet. add() returnerer false hvis elementet allerede finnes.",
            "Riktig! HashSet tillater ingen duplikater.", "HashSet: ingen duplikater. add() av eksisterende element endrer ingenting.");

        Exercise e3 = CourseData.code(r,
            "Lag en HashMap<String, Integer> med to navn og aldere, og skriv ut verdien for det første navnet.",
            "HashMap<String,Integer> m = new HashMap<>(); m.put(\"Ola\",25); System.out.println(m.get(\"Ola\"));",
            "import java.util", List.of("HashMap", "put(", "get(", "System.out.println"),
            "import java.util.HashMap;\npublic class MapTest {\n    public static void main(String[] args) {\n        HashMap<String, Integer> aldre = new HashMap<>();\n        aldre.put(\"Ola\", 25);\n        aldre.put(\"Kari\", 22);\n        System.out.println(aldre.get(\"Ola\"));\n    }\n}",
            "Flott! Du kan nå bruke HashMap! 🗺️",
            "HashMap<String,Integer>, put() for å legge til, get() for å hente.");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── MODULE 18: Tråder ────────────────────────────────────────────────────

    static CourseModule createModule18(CourseData.ExIdRef r) {
        CourseModule m = new CourseModule();
        m.setId(18); m.setTitle("Tråder");
        m.setDescription("Parallell kjøring med Thread, Runnable og synkronisering.");
        m.setIcon("🧵"); m.setColor("#d946ef"); m.setAvailable(true);
        m.setLessons(List.of(lesson18_1(r), lesson18_2(r)));
        return m;
    }

    static Lesson lesson18_1(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(40); l.setModuleId(18); l.setTitle("Thread og Runnable");
        l.setGuideContent("""
            <h2>Tråder – gjør flere ting samtidig</h2>
            <p>En <strong>tråd</strong> (thread) er en selvstendig kjøringsenhet. Med multitråding kan programmet gjøre flere ting parallelt.</p>
            <h3>To måter å lage tråder</h3>
            <div class="note">
                <strong>1. Implementer Runnable (anbefalt):</strong><br>
                <code>Runnable oppgave = () -&gt; System.out.println("Hei fra tråd!");</code><br>
                <code>Thread t = new Thread(oppgave);</code><br>
                <code>t.start();</code><br><br>
                <strong>2. Arv fra Thread:</strong><br>
                <code>class MinTråd extends Thread {</code><br>
                &nbsp;&nbsp;<code>public void run() { System.out.println("Kjører!"); }</code><br>
                <code>}</code><br>
                <code>new MinTråd().start();</code>
            </div>
            <div class="tip">
                <strong>💡 start() vs run()</strong><br>
                <code>t.start()</code> – starter en ny tråd ✅<br>
                <code>t.run()</code> – kjører koden i <em>samme</em> tråd ❌ (ingen parallellisme!)
            </div>
            <div class="note">
                <strong>Thread.sleep() – pause:</strong><br>
                <code>Thread.sleep(1000); // pause i 1 sekund</code><br>
                Kaster <code>InterruptedException</code> – husk try-catch!
            </div>
            """);
        l.setCodeExampleTitle("Tråder i praksis");
        l.setCodeExample("""
            public class TrådEksempel {
                public static void main(String[] args) {
                    // Lambda-tråd
                    Thread teller = new Thread(() -> {
                        for (int i = 1; i <= 5; i++) {
                            System.out.println("Tråd 1: " + i);
                            try { Thread.sleep(500); } catch (InterruptedException e) {}
                        }
                    });

                    Thread bakgrunn = new Thread(() -> {
                        for (int i = 1; i <= 5; i++) {
                            System.out.println("  Tråd 2: " + i);
                            try { Thread.sleep(700); } catch (InterruptedException e) {}
                        }
                    });

                    teller.start();    // starter tråd 1
                    bakgrunn.start();  // starter tråd 2 parallelt

                    System.out.println("Begge tråder kjører nå!");

                    try {
                        teller.join();    // vent til tråd 1 er ferdig
                        bakgrunn.join();  // vent til tråd 2 er ferdig
                    } catch (InterruptedException e) {}

                    System.out.println("Ferdig!");
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva er en tråd i Java?",
            List.of(
                "En type variabel",
                "En selvstendig kjøringsenhet som kan kjøre parallelt med andre tråder",
                "En spesiell type løkke",
                "Et grensesnitt for nettverkskommunikasjon"
            ),
            1, "En tråd er en kjøringsenhet. Multitråding gir parallell kjøring i samme program.",
            "Riktig! En tråd er en selvstendig kjøringsenhet.", "Tråd = selvstendig kjøringsenhet. start() for å kjøre parallelt.");

        Exercise e2 = CourseData.mc(r,
            "Hva er forskjellen på t.start() og t.run()?",
            List.of(
                "Det er ingen forskjell",
                "start() oppretter en ny tråd, run() kjøres i samme tråd",
                "run() er raskere enn start()",
                "start() kan bare kalles én gang"
            ),
            1, "start() oppretter en ny OS-tråd. run() kjøres i gjeldende tråd – ingen parallellisme.",
            "Riktig! start() = ny tråd. run() = kjøres i samme tråd.", "Alltid bruk start() for parallell kjøring, aldri run() direkte.");

        Exercise e3 = CourseData.code(r,
            "Lag en tråd med lambda som skriver ut \"Hei fra tråd!\" og start den.",
            "Thread t = new Thread(() -> System.out.println(\"Hei fra tråd!\")); t.start();",
            "public class", List.of("new Thread(", "->", "System.out.println", "start()"),
            "public class TrådHei {\n    public static void main(String[] args) {\n        Thread t = new Thread(() -> {\n            System.out.println(\"Hei fra tråd!\");\n        });\n        t.start();\n    }\n}",
            "Bra! Du kan nå starte tråder! 🧵",
            "new Thread(() -> { ... }).start();");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson18_2(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(41); l.setModuleId(18); l.setTitle("Synkronisering");
        l.setGuideContent("""
            <h2>Synkronisering – trygg deling av ressurser</h2>
            <h3>Race Condition – et problem med tråder</h3>
            <p>Når to tråder leser og skriver til samme variabel <em>uten koordinering</em> kan resultatet bli feil. Dette kalles en <strong>race condition</strong>.</p>
            <div class="note">
                <strong>Problemet:</strong><br>
                Tråd A leser teller = 5<br>
                Tråd B leser teller = 5<br>
                Tråd A skriver teller = 6<br>
                Tråd B skriver teller = 6  ← FEIL! Skulle vært 7
            </div>
            <h3>synchronized – løsningen</h3>
            <div class="note">
                <code>public synchronized void øk() {</code><br>
                &nbsp;&nbsp;<code>teller++;</code><br>
                <code>}</code><br><br>
                <code>synchronized</code> sørger for at bare én tråd kjører metoden om gangen.
            </div>
            <div class="tip">
                <strong>💡 Alternativ: AtomicInteger</strong><br>
                For enkel teller-logikk er <code>AtomicInteger</code> enklere:<br>
                <code>AtomicInteger teller = new AtomicInteger(0);</code><br>
                <code>teller.incrementAndGet(); // atomisk +1</code>
            </div>
            """);
        l.setCodeExampleTitle("synchronized og AtomicInteger");
        l.setCodeExample("""
            import java.util.concurrent.atomic.AtomicInteger;

            public class SynkEksempel {
                // synchronized-versjon
                static int teller = 0;

                public static synchronized void økTeller() {
                    teller++;
                }

                // Alternativ med AtomicInteger
                static AtomicInteger atomTeller = new AtomicInteger(0);

                public static void main(String[] args) throws InterruptedException {
                    Thread t1 = new Thread(() -> {
                        for (int i = 0; i < 1000; i++) økTeller();
                    });
                    Thread t2 = new Thread(() -> {
                        for (int i = 0; i < 1000; i++) økTeller();
                    });

                    t1.start(); t2.start();
                    t1.join();  t2.join();

                    System.out.println("Teller: " + teller);  // alltid 2000

                    // AtomicInteger
                    Thread t3 = new Thread(() -> {
                        for (int i = 0; i < 1000; i++) atomTeller.incrementAndGet();
                    });
                    t3.start(); t3.join();
                    System.out.println("Atom: " + atomTeller.get());
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva er en race condition?",
            List.of(
                "To tråder som er ferdig samtidig",
                "En feil som oppstår når tråder leser/skriver delt data uten synkronisering",
                "En veldig rask tråd",
                "En tråd som løper i løkke"
            ),
            1, "Race condition = uforutsigbart resultat fordi tråder konkurrerer om delt ressurs uten koordinering.",
            "Riktig! Race condition = konkurranse om delt ressurs.", "Race condition: to tråder endrer samme data uten synkronisering → uforutsigbart resultat.");

        Exercise e2 = CourseData.mc(r,
            "Hva gjør synchronized på en metode?",
            List.of(
                "Gjør metoden raskere",
                "Sørger for at bare én tråd kjører metoden om gangen",
                "Stopper alle andre tråder permanent",
                "Gjør metoden static"
            ),
            1, "synchronized = lås. Bare én tråd om gangen kan kjøre metoden – resten venter.",
            "Riktig! synchronized = én tråd om gangen.", "synchronized: én tråd om gangen = ingen race condition.");

        Exercise e3 = CourseData.mc(r,
            "Hva er fordelen med AtomicInteger fremfor synchronized int?",
            List.of(
                "AtomicInteger er større enn vanlig int",
                "AtomicInteger er enklere å bruke for enkle teller-operasjoner og er trådsikker",
                "AtomicInteger støtter desimaltall",
                "AtomicInteger er alltid raskere"
            ),
            1, "AtomicInteger er laget spesifikt for trådsikre heltall-operasjoner uten eksplisitt synchronized.",
            "Riktig! AtomicInteger: enkel og trådsikker.", "AtomicInteger: innebygd trådsikkerhet for heltall uten synchronized.");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── MODULE 19: Enum ──────────────────────────────────────────────────────

    static CourseModule createModule19(CourseData.ExIdRef r) {
        CourseModule m = new CourseModule();
        m.setId(19); m.setTitle("Enum");
        m.setDescription("Oppramsingstyper – navngitte konstanter med logikk.");
        m.setIcon("🎭"); m.setColor("#d946ef"); m.setAvailable(true);
        m.setLessons(List.of(lesson19_1(r), lesson19_2(r)));
        return m;
    }

    static Lesson lesson19_1(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(42); l.setModuleId(19); l.setTitle("Enum grunnleggende");
        l.setGuideContent("""
            <h2>Enum – navngitte konstanter</h2>
            <p>En <code>enum</code> definerer et sett med navngitte konstanter. I stedet for magiske tall eller strenger bruker du meningsfulle navn.</p>
            <div class="note">
                <strong>Uten enum (dårlig):</strong><br>
                <code>int dag = 1; // Hva betyr 1? Mandag?</code><br><br>
                <strong>Med enum (bra):</strong><br>
                <code>enum Dag { MANDAG, TIRSDAG, ONSDAG, TORSDAG, FREDAG, LØRDAG, SØNDAG }</code><br>
                <code>Dag iDag = Dag.MANDAG;</code>
            </div>
            <h3>Enum i switch</h3>
            <div class="note">
                <code>switch (iDag) {</code><br>
                &nbsp;&nbsp;<code>case MANDAG -&gt; System.out.println("Ugh, mandag...");</code><br>
                &nbsp;&nbsp;<code>case FREDAG -&gt; System.out.println("Fredag! 🎉");</code><br>
                &nbsp;&nbsp;<code>default    -&gt; System.out.println("Vanlig ukedag");</code><br>
                <code>}</code>
            </div>
            <h3>Nyttige enum-metoder</h3>
            <table class="data-table">
                <tr><th>Metode</th><th>Returnerer</th></tr>
                <tr><td><code>Dag.values()</code></td><td>Array med alle enum-verdier</td></tr>
                <tr><td><code>Dag.valueOf("MANDAG")</code></td><td>Enum fra String</td></tr>
                <tr><td><code>iDag.ordinal()</code></td><td>Posisjon (0-basert)</td></tr>
                <tr><td><code>iDag.name()</code></td><td>Navn som String</td></tr>
            </table>
            """);
        l.setCodeExampleTitle("Enum i praksis");
        l.setCodeExample("""
            public class EnumEksempel {
                enum Årstid { VÅR, SOMMER, HØST, VINTER }

                public static void main(String[] args) {
                    Årstid nå = Årstid.SOMMER;

                    // Switch med enum
                    switch (nå) {
                        case VÅR    -> System.out.println("Blomster!");
                        case SOMMER -> System.out.println("Ferie! ☀️");
                        case HØST   -> System.out.println("Løv faller");
                        case VINTER -> System.out.println("Snø! ❄️");
                    }

                    // Gå gjennom alle verdier
                    for (Årstid å : Årstid.values()) {
                        System.out.println(å.ordinal() + ": " + å.name());
                    }

                    // Sammenlign
                    if (nå == Årstid.SOMMER) {
                        System.out.println("Det er sommer!");
                    }
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva er fordelen med enum fremfor int-konstanter?",
            List.of(
                "Enum er raskere",
                "Enum gir meningsfulle navn, kompilator-sikkerhet og autocompletions",
                "Enum bruker mindre minne",
                "Det er ingen fordel"
            ),
            1, "Enum gir lesbar kode, kompilatoren sjekker at du bruker gyldige verdier, og IntelliJ gir autocompletions.",
            "Riktig! Enum = lesbar, typesikker kode.", "Enum: navngitte konstanter, kompileringssikker, lesbar kode.");

        Exercise e2 = CourseData.mc(r,
            "Hva returnerer Dag.values()?",
            List.of(
                "Antall enum-verdier",
                "Et array med alle enum-verdiene",
                "En String med alle navn",
                "Den første enum-verdien"
            ),
            1, "values() returnerer et array med alle enum-konstantene i definert rekkefølge.",
            "Riktig! values() = array med alle enum-verdier.", "values() → Dag[] med alle verdier.");

        Exercise e3 = CourseData.code(r,
            "Lag en enum 'Retning' med verdiene NORD, SØR, ØST, VEST og skriv ut alle verdiene med en for-each løkke.",
            "enum Retning { NORD, SØR, ØST, VEST }  for (Retning r : Retning.values()) { System.out.println(r); }",
            "enum Retning", List.of("enum Retning", "NORD", "values()", "System.out.println"),
            "public class RetningTest {\n    enum Retning { NORD, SØR, ØST, VEST }\n    public static void main(String[] args) {\n        for (Retning r : Retning.values()) {\n            System.out.println(r);\n        }\n    }\n}",
            "Utmerket! Du kan nå lage og bruke enum! 🧭",
            "enum Retning { NORD, SØR, ØST, VEST } og Retning.values() i løkken.");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson19_2(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(43); l.setModuleId(19); l.setTitle("Enum med felter og metoder");
        l.setGuideContent("""
            <h2>Enum med data og logikk</h2>
            <p>En enum kan ha <strong>felter</strong>, <strong>konstruktør</strong> og <strong>metoder</strong> – akkurat som en klasse!</p>
            <div class="note">
                <code>enum Planet {</code><br>
                &nbsp;&nbsp;<code>MERKUR(3.7), VENUS(8.87), JORD(9.81), MARS(3.72);</code><br><br>
                &nbsp;&nbsp;<code>private final double gravitet;</code><br><br>
                &nbsp;&nbsp;<code>Planet(double gravitet) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>this.gravitet = gravitet;</code><br>
                &nbsp;&nbsp;<code>}</code><br><br>
                &nbsp;&nbsp;<code>public double getGravitet() { return gravitet; }</code><br>
                <code>}</code><br><br>
                <code>System.out.println(Planet.MARS.getGravitet()); // 3.72</code>
            </div>
            <div class="tip">
                <strong>💡 Bruk enum for statuskoder</strong><br>
                <code>enum Ordre { MOTTATT, BEHANDLES, SENDT, LEVERT, KANSELLERT }</code><br>
                Mye tydeligere enn strenger eller tall!
            </div>
            """);
        l.setCodeExampleTitle("Enum med konstruktør og metoder");
        l.setCodeExample("""
            public class PlanetEksempel {
                enum Planet {
                    MERKUR(4879, 3.7),
                    VENUS(12104, 8.87),
                    JORD(12742, 9.81),
                    MARS(6779, 3.72);

                    private final int diameter;    // km
                    private final double gravitet; // m/s²

                    Planet(int diameter, double gravitet) {
                        this.diameter = diameter;
                        this.gravitet = gravitet;
                    }

                    public int getDiameter() { return diameter; }
                    public double getGravitet() { return gravitet; }

                    public double vektPåPlanet(double massePåJord) {
                        return massePåJord * (gravitet / 9.81);
                    }
                }

                public static void main(String[] args) {
                    double vektPåJord = 70.0;

                    for (Planet p : Planet.values()) {
                        System.out.printf("%s: %.1f kg%n",
                            p.name(),
                            p.vektPåPlanet(vektPåJord));
                    }
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Kan en enum ha en konstruktør i Java?",
            List.of(
                "Nei, aldri",
                "Ja, men konstruktøren er alltid privat",
                "Ja, og konstruktøren kan være public",
                "Bare hvis enumen arver fra en klasse"
            ),
            1, "Enum-konstruktøren er implisitt privat (eller kan erklæres private eksplisitt). Du kan ikke kalle new Planet().",
            "Riktig! Enum-konstruktøren er alltid privat.", "Enum: konstruktøren er alltid privat. Verdiene kalles automatisk.");

        Exercise e2 = CourseData.mc(r,
            "Hva er riktig syntaks for å legge til et felt i en enum?",
            List.of(
                "enum Status { AKTIV(true); boolean erAktiv; }",
                "enum Status { AKTIV(true); private final boolean erAktiv; Status(boolean e) { this.erAktiv = e; } }",
                "enum Status { AKTIV; public boolean erAktiv = true; }",
                "enum Status { AKTIV; static boolean erAktiv;"
            ),
            1, "Felt + konstruktør + verdi i parentes er mønsteret for enum med data.",
            "Riktig! Felt, konstruktør og verdi i parentes etter konstanten.", "enum Navn { VERDI(data); private final Type felt; Navn(Type t) { this.felt=t; } }");

        Exercise e3 = CourseData.code(r,
            "Lag en enum 'Farge' med verdiene RØD, GRØNN, BLÅ og et felt hexKode (String) med verdiene \"#FF0000\", \"#00FF00\", \"#0000FF\".",
            "enum Farge { RØD(\"#FF0000\"), GRØNN(\"#00FF00\"), BLÅ(\"#0000FF\"); private final String hexKode; Farge(String h) { hexKode=h; } }",
            "enum Farge", List.of("enum Farge", "hexKode", "#FF0000", "private final String"),
            "public class FargeTest {\n    enum Farge {\n        RØD(\"#FF0000\"), GRØNN(\"#00FF00\"), BLÅ(\"#0000FF\");\n        private final String hexKode;\n        Farge(String hexKode) { this.hexKode = hexKode; }\n        public String getHexKode() { return hexKode; }\n    }\n    public static void main(String[] args) {\n        System.out.println(Farge.RØD.getHexKode());\n    }\n}",
            "Imponerende! Du kan nå lage avanserte enums! 🌈",
            "enum Farge { RØD(\"#FF0000\"), ...; private final String hexKode; Farge(String h) { this.hexKode=h; } }");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── MODULE 20: Programmeringsparadigmer ──────────────────────────────────

    static CourseModule createModule20(CourseData.ExIdRef r) {
        CourseModule m = new CourseModule();
        m.setId(20); m.setTitle("Programmeringsparadigmer");
        m.setDescription("Imperativ, deklarativ og funksjonell programmering i Java.");
        m.setIcon("🧠"); m.setColor("#84cc16"); m.setAvailable(true);
        m.setLessons(List.of(lesson20_1(r), lesson20_2(r)));
        return m;
    }

    static Lesson lesson20_1(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(44); l.setModuleId(20); l.setTitle("Imperativ og Deklarativ");
        l.setGuideContent("""
            <h2>Imperativ vs Deklarativ programmering</h2>
            <p>Det finnes ulike måter å tenke på og skrive kode på. To viktige stiler er <strong>imperativ</strong> og <strong>deklarativ</strong>.</p>
            <h3>Imperativ – HVORDAN</h3>
            <p>Imperativ kode beskriver <em>steg for steg</em> hvordan noe skal gjøres. Du kontrollerer flyten eksplisitt.</p>
            <div class="note">
                <code>// Imperativt: finn sum av partall</code><br>
                <code>int sum = 0;</code><br>
                <code>for (int n : tall) {</code><br>
                &nbsp;&nbsp;<code>if (n % 2 == 0) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>sum += n;</code><br>
                &nbsp;&nbsp;<code>}</code><br>
                <code>}</code>
            </div>
            <h3>Deklarativ – HVA</h3>
            <p>Deklarativ kode beskriver <em>hva</em> du vil ha, ikke hvordan. Du overlater detaljene til systemet.</p>
            <div class="note">
                <code>// Deklarativt: samme logikk med Stream</code><br>
                <code>int sum = tall.stream()</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>.filter(n -&gt; n % 2 == 0)</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>.mapToInt(Integer::intValue)</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>.sum();</code>
            </div>
            <div class="tip">
                <strong>💡 Andre eksempler på deklarativ stil</strong><br>
                SQL: <code>SELECT * FROM studenter WHERE alder > 18</code><br>
                HTML: <code>&lt;button&gt;Klikk meg&lt;/button&gt;</code><br>
                Du sier <em>hva</em> du vil – ikke <em>hvordan</em> det vises/hentes.
            </div>
            """);
        l.setCodeExampleTitle("Imperativ vs deklarativ");
        l.setCodeExample("""
            import java.util.*;
            import java.util.stream.*;

            public class Paradigmer {
                public static void main(String[] args) {
                    List<Integer> tall = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

                    // ── Imperativ stil ──
                    List<String> imperativResult = new ArrayList<>();
                    for (int t : tall) {
                        if (t % 2 == 0 && t > 4) {
                            imperativResult.add("Tall: " + t);
                        }
                    }
                    System.out.println(imperativResult);

                    // ── Deklarativ stil (Stream API) ──
                    List<String> deklarativResult = tall.stream()
                        .filter(t -> t % 2 == 0 && t > 4)
                        .map(t -> "Tall: " + t)
                        .collect(Collectors.toList());
                    System.out.println(deklarativResult);

                    // Begge gir: [Tall: 6, Tall: 8, Tall: 10]
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva kjennetegner imperativ programmering?",
            List.of(
                "Koden beskriver HVA resultatet skal være",
                "Koden beskriver HVORDAN ting skal gjøres, steg for steg",
                "Koden bruker bare lambda-uttrykk",
                "Koden har ingen løkker"
            ),
            1, "Imperativ = steg-for-steg instruksjoner. Du kontrollerer flyten eksplisitt.",
            "Riktig! Imperativ = HVORDAN, steg for steg.", "Imperativ: du forteller maskinen HVORDAN med løkker og if.");

        Exercise e2 = CourseData.mc(r,
            "Hvilken av disse er et eksempel på DEKLARATIV kode?",
            List.of(
                "En for-løkke som summerer tall",
                "SQL: SELECT * FROM produkter WHERE pris < 100",
                "En if-setning som sjekker alder",
                "En while-løkke som leser input"
            ),
            1, "SQL er deklarativt – du sier hva du vil ha, databasen bestemmer hvordan det hentes.",
            "Riktig! SQL er deklarativt – du beskriver HVA, ikke HVORDAN.", "SQL, Stream API, HTML er deklarative. For/while er imperativt.");

        Exercise e3 = CourseData.mc(r,
            "Hva er fordelen med deklarativ stil?",
            List.of(
                "Det er alltid raskere",
                "Koden er kortere, mer lesbar og lettere å forstå intensjonen",
                "Det kan aldri gå feil",
                "Det bruker mindre minne"
            ),
            1, "Deklarativ kode uttrykker intensjonen tydeligere. Leseren ser HVA, ikke HOW.",
            "Riktig! Deklarativ kode er kortere og mer lesbar.", "Deklarativ: kortere, klarere intensjon, enklere å lese.");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson20_2(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(45); l.setModuleId(20); l.setTitle("Funksjonell programmering");
        l.setGuideContent("""
            <h2>Funksjonell programmering i Java</h2>
            <p>Funksjonell programmering er en stil der du behandler kode som matematiske funksjoner. Java støtter dette via lambda og Stream API.</p>
            <h3>Tre nøkkelkonsepter</h3>
            <table class="data-table">
                <tr><th>Konsept</th><th>Betyr</th><th>Eksempel</th></tr>
                <tr><td><strong>Pure function</strong></td><td>Ingen bivirkninger – gir alltid samme output for samme input</td><td><code>int kvadrat(int n) { return n*n; }</code></td></tr>
                <tr><td><strong>Immutability</strong></td><td>Data endres ikke – du lager nye verdier</td><td><code>List.of()</code>, <code>final</code></td></tr>
                <tr><td><strong>Higher-order functions</strong></td><td>Funksjoner som tar andre funksjoner som argument</td><td><code>stream.filter(fn)</code></td></tr>
            </table>
            <h3>Pure function</h3>
            <div class="note">
                <strong>Pure (ren):</strong><br>
                <code>int leggSammen(int a, int b) { return a + b; }</code><br>
                Ingen side-effekter. Alltid samme svar. ✅<br><br>
                <strong>Ikke pure:</strong><br>
                <code>int leggTilOgLagre(int a) { db.save(a); return a; }</code><br>
                Har side-effekt (lagrer til db). ❌
            </div>
            <div class="tip">
                <strong>💡 Java er multi-paradigme</strong><br>
                Java er primært objektorientert, men støtter funksjonell stil med lambda og Stream. I praksis blander du paradigmene: OOP for struktur, funksjonell for databehandling.
            </div>
            """);
        l.setCodeExampleTitle("Funksjonell stil i Java");
        l.setCodeExample("""
            import java.util.*;
            import java.util.stream.*;
            import java.util.function.*;

            public class FunksjonellJava {
                // Pure function – ingen side-effekter
                static int kvadrat(int n) {
                    return n * n;
                }

                // Higher-order function – tar en funksjon som argument
                static List<Integer> transformer(List<Integer> liste, Function<Integer, Integer> fn) {
                    return liste.stream().map(fn).collect(Collectors.toList());
                }

                public static void main(String[] args) {
                    List<Integer> tall = List.of(1, 2, 3, 4, 5);

                    // Bruk pure function som lambda
                    List<Integer> kvadrater = transformer(tall, n -> n * n);
                    System.out.println(kvadrater);  // [1, 4, 9, 16, 25]

                    // Metode-referanse
                    List<Integer> kvadrater2 = transformer(tall, FunksjonellJava::kvadrat);
                    System.out.println(kvadrater2); // [1, 4, 9, 16, 25]

                    // Funksjonell pipeline
                    String resultat = tall.stream()
                        .filter(n -> n % 2 != 0)   // bare oddetall
                        .map(n -> n * n)             // kvadrer
                        .map(Object::toString)       // til String
                        .collect(Collectors.joining(", "));
                    System.out.println(resultat);  // 1, 9, 25
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva er en 'pure function'?",
            List.of(
                "En funksjon uten parametere",
                "En static metode",
                "En funksjon uten side-effekter som alltid gir samme output for samme input",
                "En metode i et interface"
            ),
            2, "Pure function: deterministisk (samme inn = samme ut) og ingen side-effekter (endrer ikke tilstand).",
            "Riktig! Pure function = deterministisk, ingen side-effekter.", "Pure: f(x) gir alltid samme svar, endrer ingenting utenfor.");

        Exercise e2 = CourseData.mc(r,
            "Hva er immutability?",
            List.of(
                "At variabler alltid er null",
                "At data ikke endres – du lager nye verdier i stedet for å mutere eksisterende",
                "At klasser er final",
                "At metoder ikke kan overrides"
            ),
            1, "Immutability: ikke endre eksisterende data – lag nytt. List.of() returnerer uforanderlig liste.",
            "Riktig! Immutability = ikke muter data, lag nytt.", "Immutable: endres ikke. f.eks. String, List.of() er immutable.");

        Exercise e3 = CourseData.code(r,
            "Skriv en pure function 'erPrime' som tar en int og returnerer true hvis den er et primtall (sjekk om noe fra 2 til n-1 deler n).",
            "static boolean erPrime(int n) { if (n < 2) return false; for (int i=2; i<n; i++) if (n%i==0) return false; return true; }",
            "public class", List.of("static boolean erPrime", "return false", "return true"),
            "public class PrimTall {\n    static boolean erPrime(int n) {\n        if (n < 2) return false;\n        for (int i = 2; i < n; i++) {\n            if (n % i == 0) return false;\n        }\n        return true;\n    }\n    public static void main(String[] args) {\n        System.out.println(erPrime(7));   // true\n        System.out.println(erPrime(10));  // false\n    }\n}",
            "Strålende! Du har fullført hele kurset! 🎓🏆",
            "static boolean erPrime(int n) { if (n<2) return false; for(...) if(n%i==0) return false; return true; }");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }
}
