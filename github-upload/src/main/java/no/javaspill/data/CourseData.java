package no.javaspill.data;

import no.javaspill.model.*;
import java.util.ArrayList;
import java.util.List;

public class CourseData {

    private static int exId = 1;

    public static List<CourseModule> getModules() {
        exId = 1;
        ExIdRef r = new ExIdRef();
        List<CourseModule> modules = new ArrayList<>();
        modules.add(createModule1());
        modules.add(createModule2());
        modules.add(createModule3());
        modules.add(createModule4());
        modules.add(createModule5());
        modules.add(CourseDataModules6to12.createModule6(r));
        modules.add(CourseDataModules6to12.createModule7(r));
        modules.add(CourseDataModules6to12.createModule8(r));
        modules.add(CourseDataModules6to12.createModule9(r));
        modules.add(CourseDataModules6to12.createModule10(r));
        modules.add(CourseDataModules6to12.createModule11(r));
        modules.add(CourseDataModules6to12.createModule12(r));
        modules.add(CourseDataModules13to20.createModule13(r));
        modules.add(CourseDataModules13to20.createModule14(r));
        modules.add(CourseDataModules13to20.createModule15(r));
        modules.add(CourseDataModules13to20.createModule16(r));
        modules.add(CourseDataModules13to20.createModule17(r));
        modules.add(CourseDataModules13to20.createModule18(r));
        modules.add(CourseDataModules13to20.createModule19(r));
        modules.add(CourseDataModules13to20.createModule20(r));
        return modules;
    }

    /** Dummy token so helper-files get a typed reference to the shared counter. */
    public static class ExIdRef {}

    // ─── MODULE 1: Introduksjon ───────────────────────────────────────────────

    private static CourseModule createModule1() {
        CourseModule m = new CourseModule();
        m.setId(1);
        m.setTitle("Introduksjon");
        m.setDescription("Hva er Java, IntelliJ IDEA og din første kode.");
        m.setIcon("☕");
        m.setColor("#f59e0b");
        m.setAvailable(true);
        m.setLessons(List.of(lesson1_1(), lesson1_2()));
        return m;
    }

    private static Lesson lesson1_1() {
        Lesson l = new Lesson();
        l.setId(1); l.setModuleId(1);
        l.setTitle("Hva er Java?");
        l.setGuideContent("""
            <h2>Hva er Java?</h2>
            <p>Java er et av verdens mest populære programmeringsspråk. Det ble skapt av <strong>James Gosling</strong> hos Sun Microsystems i 1995, og eies i dag av Oracle.</p>
            <div class="tip">
                <strong>💡 Write Once, Run Anywhere</strong><br>
                Java-kode kompileres til <em>bytekode</em> som kjøres på en <strong>Java Virtual Machine (JVM)</strong>. Det betyr at samme program kan kjøres på Windows, Mac og Linux uten endringer!
            </div>
            <h3>Java brukes til:</h3>
            <ul>
                <li>🌐 <strong>Webutvikling</strong> – backend-systemer</li>
                <li>📱 <strong>Android-apper</strong> – nesten alle Android-apper er skrevet i Java eller Kotlin</li>
                <li>🏢 <strong>Bedriftsapplikasjoner</strong> – banker, forsikring og store systemer</li>
                <li>🎮 <strong>Spill</strong> – Minecraft er skrevet i Java!</li>
                <li>📊 <strong>Big Data</strong> – Hadoop og Spark bruker Java</li>
            </ul>
            <h3>Slik ser et Java-program ut</h3>
            <p>Hvert Java-program har en <strong>klasse</strong> og en <strong>main-metode</strong> som er startpunktet:</p>
            <div class="note">
                <strong>📝 Tre viktige linjer å huske:</strong>
                <ol>
                    <li><code>public class HelloWorld</code> – definerer en klasse</li>
                    <li><code>public static void main(String[] args)</code> – startpunktet</li>
                    <li><code>System.out.println("...")</code> – skriver tekst til konsollen</li>
                </ol>
            </div>
            """);
        l.setCodeExampleTitle("Ditt første Java-program");
        l.setCodeExample("""
            public class HelloWorld {
                public static void main(String[] args) {
                    System.out.println("Hello, World!");
                }
            }
            """);

        Exercise e1 = mc(
            "Hvem skapte Java?",
            List.of("Linus Torvalds", "James Gosling", "Bill Gates", "Dennis Ritchie"),
            1,
            "Java ble skapt av James Gosling hos Sun Microsystems i 1995.",
            "Riktig! James Gosling skapte Java hos Sun Microsystems.",
            "Ikke helt – Java ble skapt av James Gosling, ikke de andre."
        );
        Exercise e2 = mc(
            "Hva betyr 'Write Once, Run Anywhere'?",
            List.of(
                "Java-kode er gratis å bruke overalt",
                "Samme Java-kode kjøres på alle enheter med JVM",
                "Du trenger bare å installere Java én gang",
                "Java-programmer er alltid raskere enn andre språk"
            ),
            1,
            "JVM (Java Virtual Machine) gjør det mulig å kjøre samme bytekode på alle plattformer.",
            "Riktig! JVM gjør at Java-programmer kjøres på alle enheter uten endringer.",
            "Ikke helt – det handler om at Java-bytekode kjøres av JVM på alle plattformer."
        );
        Exercise e3 = mc(
            "Hva gjør System.out.println() ?",
            List.of(
                "Lagrer tekst til en fil",
                "Åpner et nytt vindu",
                "Skriver tekst til konsollen og lager ny linje",
                "Leser input fra brukeren"
            ),
            2,
            "println = print + linje. Teksten skrives ut og markøren flyttes til neste linje.",
            "Riktig! println skriver tekst og legger til linjeskift på slutten.",
            "Ikke helt – System.out.println() skriver tekst til konsollen med linjeskift."
        );

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    private static Lesson lesson1_2() {
        Lesson l = new Lesson();
        l.setId(2); l.setModuleId(1);
        l.setTitle("Ditt første program");
        l.setGuideContent("""
            <h2>Ditt første Java-program</h2>
            <p>Nå skal du skrive ditt aller første Java-program! La oss gå gjennom strukturen linje for linje.</p>
            <h3>Linje for linje</h3>
            <div class="note">
                <strong>1. Klassedeklarasjon</strong><br>
                <code>public class MinKlasse { ... }</code><br>
                <ul>
                    <li><code>public</code> – klassen er tilgjengelig overalt</li>
                    <li><code>class</code> – nøkkelord for å lage en klasse</li>
                    <li><code>MinKlasse</code> – navn på klassen (begynner alltid med stor bokstav)</li>
                    <li><code>{ }</code> – klammeparenteser som omslutte innholdet</li>
                </ul>
            </div>
            <div class="note">
                <strong>2. Main-metoden</strong><br>
                <code>public static void main(String[] args) { ... }</code><br>
                Dette er <em>alltid</em> startpunktet for programmet. Java leter etter akkurat dette signaturen.
            </div>
            <div class="note">
                <strong>3. Print-setning</strong><br>
                <code>System.out.println("Hello, World!");</code><br>
                <ul>
                    <li><code>System.out</code> – output-strømmen</li>
                    <li><code>println</code> – print + linjeskift</li>
                    <li>Tekst må være i <strong>anførselstegn</strong></li>
                    <li>Linjen avsluttes med <strong>semikolon ;</strong></li>
                </ul>
            </div>
            <div class="tip">
                <strong>🤖 AI i programmering</strong><br>
                Verktøy som GitHub Copilot og ChatGPT kan hjelpe deg å skrive kode raskere. Men det er viktig å <strong>forstå hva koden gjør</strong> – ikke bare kopiere den. Bruk AI som en lærer, ikke en snarvei!
            </div>
            """);
        l.setCodeExampleTitle("Hello, World! – komplett program");
        l.setCodeExample("""
            public class HelloWorld {
                public static void main(String[] args) {
                    // Dette er en kommentar – Java ignorerer denne linjen
                    System.out.println("Hello, World!");
                    System.out.println("Jeg lærer Java!");
                }
            }
            """);

        Exercise e1 = code(
            "Skriv et program som skriver ut: Hello, World!",
            "Husk: public class, main-metode, og System.out.println()",
            "public class",
            List.of("System.out.println", "Hello, World!"),
            """
            public class HelloWorld {
                public static void main(String[] args) {
                    System.out.println("Hello, World!");
                }
            }
            """,
            "Kjempebra! Du har skrevet ditt første Java-program! 🎉",
            "Ikke helt riktig. Husk å bruke System.out.println(\"Hello, World!\") for å skrive ut teksten."
        );
        Exercise e2 = code(
            "Skriv et program som skriver ut ditt eget navn.",
            "Bytt ut teksten i println med ditt navn, f.eks. System.out.println(\"Ola\")",
            "public class",
            List.of("System.out.println"),
            """
            public class MittNavn {
                public static void main(String[] args) {
                    System.out.println("Ola Nordmann");
                }
            }
            """,
            "Flott jobbet! Du vet nå hvordan du skriver ut tekst i Java! 🌟",
            "Husk å bruke System.out.println() med teksten i anførselstegn."
        );

        l.setExercises(List.of(e1, e2));
        return l;
    }

    // ─── MODULE 2: Primitive datatyper ───────────────────────────────────────

    private static CourseModule createModule2() {
        CourseModule m = new CourseModule();
        m.setId(2);
        m.setTitle("Primitive datatyper");
        m.setDescription("int, double, boolean, char og String – byggesteinene i Java.");
        m.setIcon("🔢");
        m.setColor("#3b82f6");
        m.setAvailable(true);
        m.setLessons(List.of(lesson2_1(), lesson2_2(), lesson2_3()));
        return m;
    }

    private static Lesson lesson2_1() {
        Lesson l = new Lesson();
        l.setId(3); l.setModuleId(2);
        l.setTitle("int og double");
        l.setGuideContent("""
            <h2>Heltall og desimaltall</h2>
            <p>Variabler er «bokser» som lagrer data i programmet ditt. Java er et <strong>statisk typet</strong> språk, som betyr at du må si hvilken type data boksen skal inneholde.</p>
            <h3>int – heltall</h3>
            <p><code>int</code> brukes til å lagre heltall (uten desimaler) mellom ca. –2 milliarder og +2 milliarder.</p>
            <div class="note">
                <strong>Slik deklarerer du en int:</strong><br>
                <code>int alder = 25;</code><br><br>
                <ul>
                    <li><code>int</code> – datatypen</li>
                    <li><code>alder</code> – variabelnavnet (camelCase)</li>
                    <li><code>= 25</code> – tilordner verdien 25</li>
                    <li><code>;</code> – avslutter setningen</li>
                </ul>
            </div>
            <h3>double – desimaltall</h3>
            <p><code>double</code> brukes til desimaltall. Det er den vanligste typen for tall med komma.</p>
            <div class="note">
                <strong>Slik deklarerer du en double:</strong><br>
                <code>double pris = 49.90;</code><br>
                Java bruker <strong>punktum</strong> som desimaltegn, ikke komma.
            </div>
            <div class="tip">
                <strong>💡 Husk!</strong><br>
                <code>int tall = 5;</code> – heltall ✅<br>
                <code>int tall = 5.5;</code> – <span style="color:#ef4444">FEIL!</span> Kan ikke lagre desimal i int<br>
                <code>double tall = 5;</code> – fungerer ✅ (5 konverteres til 5.0)
            </div>
            <h3>Aritmetiske operasjoner</h3>
            <p>Du kan regne med variabler akkurat som med tall:</p>
            <table class="data-table">
                <tr><th>Operator</th><th>Betydning</th><th>Eksempel</th></tr>
                <tr><td><code>+</code></td><td>Addisjon</td><td><code>3 + 2 = 5</code></td></tr>
                <tr><td><code>-</code></td><td>Subtraksjon</td><td><code>5 - 3 = 2</code></td></tr>
                <tr><td><code>*</code></td><td>Multiplikasjon</td><td><code>4 * 3 = 12</code></td></tr>
                <tr><td><code>/</code></td><td>Divisjon</td><td><code>10 / 2 = 5</code></td></tr>
                <tr><td><code>%</code></td><td>Modulo (rest)</td><td><code>10 % 3 = 1</code></td></tr>
            </table>
            """);
        l.setCodeExampleTitle("int og double i praksis");
        l.setCodeExample("""
            public class DataTyper {
                public static void main(String[] args) {
                    int alder = 20;
                    int antallStudenter = 30;
                    double karakter = 4.5;
                    double pris = 199.90;

                    int sum = alder + antallStudenter;  // 50
                    double halvpris = pris / 2;         // 99.95

                    System.out.println("Alder: " + alder);
                    System.out.println("Sum: " + sum);
                    System.out.println("Halvpris: " + halvpris);
                }
            }
            """);

        Exercise e1 = mc(
            "Hvilken datatype bruker du for å lagre heltallet 42?",
            List.of("double", "int", "String", "boolean"),
            1,
            "int er for heltall (Integer = heltall på engelsk). double er for desimaltall.",
            "Riktig! int brukes for heltall.",
            "Ikke helt – int brukes for heltall. double brukes for desimaltall."
        );
        Exercise e2 = mc(
            "Hva skriver dette ut?\nint x = 10;\nint y = 3;\nSystem.out.println(x % y);",
            List.of("3", "1", "0", "3.33"),
            1,
            "% er modulo-operatoren som gir resten etter divisjon. 10 / 3 = 3 med rest 1.",
            "Riktig! 10 % 3 = 1 fordi 10 = 3×3 + 1.",
            "Ikke helt – % gir resten etter divisjon. 10 / 3 = 3 rest 1, så svaret er 1."
        );
        Exercise e3 = code(
            "Deklarer en int-variabel kalt 'poeng' med verdien 100, og skriv den ut.",
            "Syntaks: int variabelNavn = verdi;  deretter System.out.println(variabelNavn);",
            "public class",
            List.of("int", "poeng", "100", "System.out.println"),
            """
            public class Poeng {
                public static void main(String[] args) {
                    int poeng = 100;
                    System.out.println(poeng);
                }
            }
            """,
            "Utmerket! Du kan nå deklarere int-variabler! 💪",
            "Husk: int poeng = 100; og System.out.println(poeng);"
        );

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    private static Lesson lesson2_2() {
        Lesson l = new Lesson();
        l.setId(4); l.setModuleId(2);
        l.setTitle("boolean og char");
        l.setGuideContent("""
            <h2>boolean og char</h2>
            <h3>boolean – sant eller usant</h3>
            <p>En <code>boolean</code> kan bare ha to verdier: <code>true</code> eller <code>false</code>. Den brukes mye i if-setninger og løkker.</p>
            <div class="note">
                <strong>Eksempler:</strong><br>
                <code>boolean erStudent = true;</code><br>
                <code>boolean harBestått = false;</code><br>
                <code>boolean erVoksen = alder >= 18;</code> – kan også tilordne et uttrykk!
            </div>
            <h3>Logiske operatorer med boolean</h3>
            <table class="data-table">
                <tr><th>Operator</th><th>Betydning</th><th>Eksempel</th></tr>
                <tr><td><code>!</code></td><td>IKKE (negasjon)</td><td><code>!true = false</code></td></tr>
                <tr><td><code>&&</code></td><td>OG (begge må være true)</td><td><code>true && false = false</code></td></tr>
                <tr><td><code>||</code></td><td>ELLER (én må være true)</td><td><code>true || false = true</code></td></tr>
            </table>
            <h3>char – enkelt tegn</h3>
            <p><code>char</code> lagrer ett enkelt tegn. Legg merke til at char bruker <strong>enkle anførselstegn</strong>!</p>
            <div class="note">
                <strong>Viktig forskjell:</strong><br>
                <code>char bokstav = 'A';</code> ← enkle anførselstegn ✅<br>
                <code>String tekst = "Hei";</code> ← doble anførselstegn ✅<br>
                <code>char bokstav = "A";</code> ← <span style="color:#ef4444">FEIL!</span>
            </div>
            <div class="tip">
                <strong>💡 Visste du?</strong><br>
                Tegn i Java er basert på Unicode, så char kan lagre bokstaver fra alle verdens språk, og til og med emoji! 😄
            </div>
            """);
        l.setCodeExampleTitle("boolean og char eksempel");
        l.setCodeExample("""
            public class BooleanOgChar {
                public static void main(String[] args) {
                    boolean erStudent = true;
                    boolean harBestått = false;

                    // Logiske operatorer
                    boolean beggeTrue = erStudent && harBestått;   // false
                    boolean enAvDem = erStudent || harBestått;     // true
                    boolean ikkeStudent = !erStudent;              // false

                    System.out.println("Er student: " + erStudent);
                    System.out.println("Begge true: " + beggeTrue);
                    System.out.println("En av dem: " + enAvDem);

                    // char
                    char karakter = 'A';
                    char tallTegn = '7';
                    System.out.println("Karakter: " + karakter);
                }
            }
            """);

        Exercise e1 = mc(
            "Hva er verdien av dette uttrykket?\n!true",
            List.of("true", "false", "null", "0"),
            1,
            "! er negasjonsoperatoren. Den snur verdien – true blir false, false blir true.",
            "Riktig! !true = false. Utropstegnet snur verdien.",
            "Ikke helt – ! negerer verdien. !true gir false."
        );
        Exercise e2 = mc(
            "Hva er riktig måte å deklarere en char-variabel?",
            List.of(
                "char bokstav = \"A\";",
                "char bokstav = 'A';",
                "Char bokstav = A;",
                "character bokstav = 'A';"
            ),
            1,
            "char bruker enkle anførselstegn. String bruker doble anførselstegn.",
            "Riktig! char bruker enkle anførselstegn: char bokstav = 'A';",
            "Ikke helt – char bruker enkle anførselstegn ' ', ikke doble \" \"."
        );
        Exercise e3 = code(
            "Deklarer en boolean kalt 'erOnline' med verdien true, og skriv den ut.",
            "Syntaks: boolean variabelNavn = true;",
            "public class",
            List.of("boolean", "erOnline", "true", "System.out.println"),
            """
            public class Online {
                public static void main(String[] args) {
                    boolean erOnline = true;
                    System.out.println(erOnline);
                }
            }
            """,
            "Perfekt! Du forstår nå boolean-variabler! ✅",
            "Husk: boolean erOnline = true; og System.out.println(erOnline);"
        );

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    private static Lesson lesson2_3() {
        Lesson l = new Lesson();
        l.setId(5); l.setModuleId(2);
        l.setTitle("String");
        l.setGuideContent("""
            <h2>String – tekst i Java</h2>
            <p>En <code>String</code> er en sekvens av tegn (tekst). I motsetning til de andre primitive typene, er <code>String</code> en klasse i Java, noe som gir den ekstra funksjonalitet.</p>
            <div class="note">
                <strong>Deklarer en String:</strong><br>
                <code>String navn = "Ola Nordmann";</code><br>
                <code>String hilsen = "God morgen!";</code>
            </div>
            <h3>String-operasjoner</h3>
            <p>Du kan <strong>slå sammen</strong> strenger med <code>+</code>:</p>
            <div class="note">
                <code>String fornavn = "Ola";</code><br>
                <code>String etternavn = "Nordmann";</code><br>
                <code>String fulltNavn = fornavn + " " + etternavn;</code><br>
                Resultat: <code>"Ola Nordmann"</code>
            </div>
            <h3>Nyttige String-metoder</h3>
            <table class="data-table">
                <tr><th>Metode</th><th>Hva den gjør</th><th>Eksempel</th></tr>
                <tr><td><code>.length()</code></td><td>Antall tegn</td><td><code>"Hei".length() = 3</code></td></tr>
                <tr><td><code>.toUpperCase()</code></td><td>Store bokstaver</td><td><code>"hei".toUpperCase() = "HEI"</code></td></tr>
                <tr><td><code>.toLowerCase()</code></td><td>Små bokstaver</td><td><code>"HEI".toLowerCase() = "hei"</code></td></tr>
                <tr><td><code>.contains("x")</code></td><td>Inneholder teksten?</td><td><code>"Hei".contains("e") = true</code></td></tr>
            </table>
            <div class="tip">
                <strong>⚠️ OBS: Sammenlign aldri Strings med ==</strong><br>
                Bruk <code>.equals()</code> i stedet:<br>
                <code>navn.equals("Ola")</code> ✅<br>
                <code>navn == "Ola"</code> ← kan gi feil resultat!
            </div>
            """);
        l.setCodeExampleTitle("String-operasjoner");
        l.setCodeExample("""
            public class StringEksempel {
                public static void main(String[] args) {
                    String fornavn = "Ola";
                    String etternavn = "Nordmann";

                    // Sammenslåing (konkatenering)
                    String fulltNavn = fornavn + " " + etternavn;
                    System.out.println(fulltNavn);              // Ola Nordmann

                    // String-metoder
                    System.out.println(fulltNavn.length());     // 12
                    System.out.println(fulltNavn.toUpperCase()); // OLA NORDMANN
                    System.out.println(fulltNavn.contains("Ola")); // true

                    // Riktig sammenligning
                    boolean erOla = fornavn.equals("Ola");
                    System.out.println(erOla);  // true
                }
            }
            """);

        Exercise e1 = mc(
            "Hva skriver dette ut?\nString tekst = \"Java\";\nSystem.out.println(tekst.length());",
            List.of("5", "4", "3", "\"Java\""),
            1,
            "length() teller antall tegn. \"Java\" har 4 tegn: J-a-v-a.",
            "Riktig! \"Java\" har 4 tegn, så length() returnerer 4.",
            "Ikke helt – length() teller antall tegn. \"Java\" = J, a, v, a = 4 tegn."
        );
        Exercise e2 = mc(
            "Hva er riktig måte å sjekke om to Strings er like?",
            List.of(
                "tekst1 == tekst2",
                "tekst1.equals(tekst2)",
                "tekst1 = tekst2",
                "tekst1.compare(tekst2)"
            ),
            1,
            "== sjekker om de peker til samme objekt i minnet, ikke om innholdet er likt. equals() sjekker innholdet.",
            "Riktig! .equals() er riktig måte å sammenligne String-innhold på.",
            "Bruk alltid .equals() for å sammenligne Strings, ikke ==."
        );
        Exercise e3 = code(
            "Deklarer en String kalt 'hilsen' med verdien \"God dag!\", og skriv den ut.",
            "Syntaks: String variabelNavn = \"tekst\";",
            "public class",
            List.of("String", "hilsen", "God dag!", "System.out.println"),
            """
            public class Hilsen {
                public static void main(String[] args) {
                    String hilsen = "God dag!";
                    System.out.println(hilsen);
                }
            }
            """,
            "Fantastisk! Du kan nå jobbe med Strings! 🎯",
            "Husk: String hilsen = \"God dag!\"; og System.out.println(hilsen);"
        );

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── MODULE 3: Operatorer ────────────────────────────────────────────────

    private static CourseModule createModule3() {
        CourseModule m = new CourseModule();
        m.setId(3);
        m.setTitle("Operatorer");
        m.setDescription("Aritmetiske, sammenlignings- og logiske operatorer.");
        m.setIcon("➕");
        m.setColor("#10b981");
        m.setAvailable(true);
        m.setLessons(List.of(lesson3_1(), lesson3_2()));
        return m;
    }

    private static Lesson lesson3_1() {
        Lesson l = new Lesson();
        l.setId(6); l.setModuleId(3);
        l.setTitle("Aritmetiske operatorer");
        l.setGuideContent("""
            <h2>Aritmetiske operatorer</h2>
            <p>Operatorer er symboler som utfører operasjoner på verdier. La oss starte med de matematiske.</p>
            <h3>Grunnleggende regning</h3>
            <table class="data-table">
                <tr><th>Operator</th><th>Navn</th><th>Eksempel</th><th>Resultat</th></tr>
                <tr><td><code>+</code></td><td>Addisjon</td><td><code>5 + 3</code></td><td>8</td></tr>
                <tr><td><code>-</code></td><td>Subtraksjon</td><td><code>10 - 4</code></td><td>6</td></tr>
                <tr><td><code>*</code></td><td>Multiplikasjon</td><td><code>3 * 7</code></td><td>21</td></tr>
                <tr><td><code>/</code></td><td>Divisjon</td><td><code>15 / 3</code></td><td>5</td></tr>
                <tr><td><code>%</code></td><td>Modulo (rest)</td><td><code>10 % 3</code></td><td>1</td></tr>
            </table>
            <div class="tip">
                <strong>⚠️ Heltallsdivisjon</strong><br>
                Når begge tall er <code>int</code>, gir divisjon et heltall:<br>
                <code>int resultat = 7 / 2;</code> → <strong>3</strong> (ikke 3.5!)<br>
                For å få desimaltall, bruk <code>double</code>:<br>
                <code>double resultat = 7.0 / 2;</code> → <strong>3.5</strong>
            </div>
            <h3>Tilordningsoperatorer</h3>
            <p>Disse er snarveier for å oppdatere en variabel:</p>
            <table class="data-table">
                <tr><th>Operator</th><th>Eksempel</th><th>Tilsvarer</th></tr>
                <tr><td><code>+=</code></td><td><code>x += 5</code></td><td><code>x = x + 5</code></td></tr>
                <tr><td><code>-=</code></td><td><code>x -= 3</code></td><td><code>x = x - 3</code></td></tr>
                <tr><td><code>*=</code></td><td><code>x *= 2</code></td><td><code>x = x * 2</code></td></tr>
                <tr><td><code>++</code></td><td><code>x++</code></td><td><code>x = x + 1</code></td></tr>
                <tr><td><code>--</code></td><td><code>x--</code></td><td><code>x = x - 1</code></td></tr>
            </table>
            """);
        l.setCodeExampleTitle("Operatorer i bruk");
        l.setCodeExample("""
            public class Kalkulator {
                public static void main(String[] args) {
                    int a = 10;
                    int b = 3;

                    System.out.println(a + b);   // 13
                    System.out.println(a - b);   // 7
                    System.out.println(a * b);   // 30
                    System.out.println(a / b);   // 3 (heltallsdivisjon!)
                    System.out.println(a % b);   // 1 (rest)

                    // Tilordningsoperatorer
                    int teller = 0;
                    teller++;          // teller = 1
                    teller += 9;       // teller = 10
                    System.out.println(teller);   // 10

                    // Desimaldivisjon
                    double resultat = (double) a / b;
                    System.out.println(resultat);  // 3.3333...
                }
            }
            """);

        Exercise e1 = mc(
            "Hva er resultatet av 17 % 5?",
            List.of("3", "2", "4", "1"),
            1,
            "Modulo gir resten etter heltallsdivisjon. 17 = 5×3 + 2, så resten er 2.",
            "Riktig! 17 % 5 = 2 fordi 5 går 3 ganger i 17 (=15), og 17 - 15 = 2.",
            "Ikke helt – 17 / 5 = 3 rest 2. Så 17 % 5 = 2."
        );
        Exercise e2 = mc(
            "Hva skriver dette ut?\nint x = 8;\nx += 4;\nx--;\nSystem.out.println(x);",
            List.of("12", "11", "13", "8"),
            1,
            "x starter som 8. x += 4 gjør x = 12. x-- gjør x = 11.",
            "Riktig! 8 + 4 = 12, deretter 12 - 1 = 11.",
            "Gå steg for steg: x = 8 → x += 4 → x = 12 → x-- → x = 11."
        );
        Exercise e3 = code(
            "Beregn summen av to variabler a = 15 og b = 27, og skriv ut svaret.",
            "Lag to int-variabler, legg dem sammen i en tredje variabel, og skriv den ut.",
            "public class",
            List.of("int", "15", "27", "System.out.println"),
            """
            public class Sum {
                public static void main(String[] args) {
                    int a = 15;
                    int b = 27;
                    int sum = a + b;
                    System.out.println(sum);
                }
            }
            """,
            "Bra jobbet! Du kan nå bruke aritmetiske operatorer! 🧮",
            "Husk: int a = 15; int b = 27; int sum = a + b; System.out.println(sum);"
        );

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    private static Lesson lesson3_2() {
        Lesson l = new Lesson();
        l.setId(7); l.setModuleId(3);
        l.setTitle("Sammenligningsoperatorer");
        l.setGuideContent("""
            <h2>Sammenlignings- og logiske operatorer</h2>
            <p>Sammenligningsoperatorer sammenligner to verdier og returnerer alltid en <code>boolean</code> (true/false).</p>
            <h3>Sammenligningsoperatorer</h3>
            <table class="data-table">
                <tr><th>Operator</th><th>Betydning</th><th>Eksempel (a=5, b=3)</th><th>Resultat</th></tr>
                <tr><td><code>==</code></td><td>Er lik</td><td><code>a == b</code></td><td>false</td></tr>
                <tr><td><code>!=</code></td><td>Er ikke lik</td><td><code>a != b</code></td><td>true</td></tr>
                <tr><td><code>&gt;</code></td><td>Større enn</td><td><code>a &gt; b</code></td><td>true</td></tr>
                <tr><td><code>&lt;</code></td><td>Mindre enn</td><td><code>a &lt; b</code></td><td>false</td></tr>
                <tr><td><code>&gt;=</code></td><td>Større enn eller lik</td><td><code>a &gt;= 5</code></td><td>true</td></tr>
                <tr><td><code>&lt;=</code></td><td>Mindre enn eller lik</td><td><code>b &lt;= 3</code></td><td>true</td></tr>
            </table>
            <div class="tip">
                <strong>💡 = vs ==</strong><br>
                <code>=</code> er <strong>tilordning</strong>: <code>int x = 5;</code><br>
                <code>==</code> er <strong>sammenligning</strong>: <code>x == 5</code> gir true<br>
                Dette er en veldig vanlig feil for nybegynnere!
            </div>
            <h3>Logiske operatorer</h3>
            <table class="data-table">
                <tr><th>Operator</th><th>Navn</th><th>Resultat</th></tr>
                <tr><td><code>&&</code></td><td>OG</td><td>true bare hvis <em>begge</em> er true</td></tr>
                <tr><td><code>||</code></td><td>ELLER</td><td>true hvis <em>minst én</em> er true</td></tr>
                <tr><td><code>!</code></td><td>IKKE</td><td>Snur true til false og omvendt</td></tr>
            </table>
            <div class="note">
                <strong>Eksempel:</strong><br>
                <code>int alder = 20;</code><br>
                <code>boolean erVoksen = alder >= 18;</code>  → true<br>
                <code>boolean erStudent = true;</code><br>
                <code>boolean rabatt = erVoksen && erStudent;</code>  → true (begge er true)
            </div>
            """);
        l.setCodeExampleTitle("Sammenligninger i praksis");
        l.setCodeExample("""
            public class Sammenligning {
                public static void main(String[] args) {
                    int alder = 20;
                    int grense = 18;

                    boolean erVoksen = alder >= grense;
                    boolean erEldst = alder > 25;

                    System.out.println("Er voksen: " + erVoksen);   // true
                    System.out.println("Er eldst: " + erEldst);     // false

                    // Kombinere med logiske operatorer
                    boolean erStudent = true;
                    boolean harRabatt = erVoksen && erStudent;
                    System.out.println("Har rabatt: " + harRabatt); // true

                    // Sammenligning gir boolean
                    boolean tallErLike = (5 == 5);
                    System.out.println(tallErLike);                 // true
                }
            }
            """);

        Exercise e1 = mc(
            "Hva er verdien av dette?\nint a = 7;\nboolean resultat = a > 10 && a < 20;",
            List.of("true", "false", "Kompileringsfeil", "0"),
            1,
            "a > 10 er false (7 er ikke større enn 10). false && noe er alltid false.",
            "Riktig! 7 > 10 er false, så hele AND-uttrykket blir false.",
            "7 > 10 er false. Siden && krever at BEGGE er true, er resultatet false."
        );
        Exercise e2 = mc(
            "Hvilken operator brukes for å sjekke om to tall-variabler er like?",
            List.of("=", "===", "==", ".equals()"),
            2,
            "= er tilordning. == er sammenligning for primitive typer (int, double, boolean). .equals() brukes for objekter som String.",
            "Riktig! == brukes for å sammenligne primitive datatyper.",
            "For primitive typer (int, double, boolean) brukes ==. Husk: = er tilordning, == er sammenligning."
        );
        Exercise e3 = code(
            "Sjekk om variabelen tall = 42 er større enn 18, lagre resultatet i en boolean og skriv den ut.",
            "boolean resultat = tall > 18;",
            "public class",
            List.of("boolean", ">", "System.out.println"),
            """
            public class Sjekk {
                public static void main(String[] args) {
                    int tall = 42;
                    boolean erStørre = tall > 18;
                    System.out.println(erStørre);
                }
            }
            """,
            "Perfekt! Du kan nå sammenligne verdier i Java! 🎯",
            "Lag int tall = 42; deretter boolean erStørre = tall > 18; og skriv ut."
        );

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── MODULE 4: Grunnleggende konsepter ───────────────────────────────────

    private static CourseModule createModule4() {
        CourseModule m = new CourseModule();
        m.setId(4);
        m.setTitle("Grunnleggende konsepter");
        m.setDescription("if-tester, for-løkker, while-løkker og metoder.");
        m.setIcon("🔀");
        m.setColor("#8b5cf6");
        m.setAvailable(true);
        m.setLessons(List.of(lesson4_1(), lesson4_2(), lesson4_3(), lesson4_4()));
        return m;
    }

    private static Lesson lesson4_1() {
        Lesson l = new Lesson();
        l.setId(8); l.setModuleId(4);
        l.setTitle("if-setninger");
        l.setGuideContent("""
            <h2>if-setninger – ta beslutninger i kode</h2>
            <p>Med if-setninger kan programmet ditt ta beslutninger basert på betingelser.</p>
            <h3>Grunnleggende if</h3>
            <div class="note">
                <code>if (betingelse) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>// kjøres bare hvis betingelsen er true</code><br>
                <code>}</code>
            </div>
            <h3>if-else</h3>
            <div class="note">
                <code>if (betingelse) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>// kjøres hvis true</code><br>
                <code>} else {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>// kjøres hvis false</code><br>
                <code>}</code>
            </div>
            <h3>if-else if-else</h3>
            <div class="note">
                <code>if (alder < 13) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>System.out.println("Barn");</code><br>
                <code>} else if (alder < 18) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>System.out.println("Tenåring");</code><br>
                <code>} else {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>System.out.println("Voksen");</code><br>
                <code>}</code>
            </div>
            <div class="tip">
                <strong>💡 Huskeregel</strong><br>
                Tenk på det som et veikryss: Er betingelsen oppfylt? Da tar du venstre vei. Hvis ikke, tar du høyre.
            </div>
            """);
        l.setCodeExampleTitle("if-setninger i praksis");
        l.setCodeExample("""
            public class Alder {
                public static void main(String[] args) {
                    int alder = 20;

                    if (alder < 13) {
                        System.out.println("Du er et barn");
                    } else if (alder < 18) {
                        System.out.println("Du er en tenåring");
                    } else if (alder < 67) {
                        System.out.println("Du er en voksen");
                    } else {
                        System.out.println("Du er pensjonist");
                    }

                    // Enkel if
                    int poeng = 75;
                    if (poeng >= 60) {
                        System.out.println("Bestått!");
                    } else {
                        System.out.println("Ikke bestått.");
                    }
                }
            }
            """);

        Exercise e1 = mc(
            "Hva skriver dette ut?\nint x = 15;\nif (x > 10) {\n    System.out.println(\"Stor\");\n} else {\n    System.out.println(\"Liten\");\n}",
            List.of("Liten", "Stor", "15", "Ingenting"),
            1,
            "15 > 10 er true, så if-blokken kjøres og \"Stor\" skrives ut.",
            "Riktig! 15 > 10 er true, så \"Stor\" skrives ut.",
            "x = 15 og 15 > 10 er true, dermed kjøres if-blokken som skriver \"Stor\"."
        );
        Exercise e2 = mc(
            "Hva skrives ut?\nint tall = 5;\nif (tall > 10) {\n    System.out.println(\"A\");\n} else if (tall > 3) {\n    System.out.println(\"B\");\n} else {\n    System.out.println(\"C\");\n}",
            List.of("A", "B", "C", "A og B"),
            1,
            "tall = 5. Første betingelse: 5 > 10 er false. Andre betingelse: 5 > 3 er true → \"B\".",
            "Riktig! 5 > 10 er false, men 5 > 3 er true, så \"B\" skrives ut.",
            "Sjekk steg for steg: 5 > 10? Nei. 5 > 3? Ja → \"B\"."
        );
        Exercise e3 = code(
            "Skriv en if-setning som sjekker om variabelen poeng = 85 er større enn eller lik 60. Skriv ut \"Bestått\" hvis ja, \"Stryk\" hvis nei.",
            "if (poeng >= 60) { ... } else { ... }",
            "public class",
            List.of("if", "poeng", ">=", "Bestått"),
            """
            public class Eksamen {
                public static void main(String[] args) {
                    int poeng = 85;
                    if (poeng >= 60) {
                        System.out.println("Bestått");
                    } else {
                        System.out.println("Stryk");
                    }
                }
            }
            """,
            "Flott! Du kan nå bruke if-setninger til å ta beslutninger! 🎉",
            "Husk strukturen: if (betingelse) { ... } else { ... }"
        );

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    private static Lesson lesson4_2() {
        Lesson l = new Lesson();
        l.setId(9); l.setModuleId(4);
        l.setTitle("for-løkker");
        l.setGuideContent("""
            <h2>for-løkker – gjenta kode</h2>
            <p>En løkke lar deg gjenta kode mange ganger uten å skrive det om og om igjen.</p>
            <h3>Struktur av en for-løkke</h3>
            <div class="note">
                <code>for (start; betingelse; endring) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>// kode som gjentas</code><br>
                <code>}</code><br><br>
                <strong>De tre delene:</strong>
                <ol>
                    <li><strong>start:</strong> <code>int i = 0</code> – tellevariabelen starter på 0</li>
                    <li><strong>betingelse:</strong> <code>i < 5</code> – løkken fortsetter så lenge dette er true</li>
                    <li><strong>endring:</strong> <code>i++</code> – øk i med 1 etter hver runde</li>
                </ol>
            </div>
            <div class="tip">
                <strong>💡 Tellevariabelen i</strong><br>
                Det er vanlig å kalle tellevariabelen <code>i</code> (for index). For nøstede løkker brukes <code>j</code> og <code>k</code>.<br>
                Løkken <code>for (int i = 0; i < 5; i++)</code> kjøres med i = 0, 1, 2, 3, 4.
            </div>
            <h3>for-each løkke</h3>
            <p>Når du vil gå gjennom alle elementer i en liste eller array:</p>
            <div class="note">
                <code>for (String navn : navneliste) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>System.out.println(navn);</code><br>
                <code>}</code>
            </div>
            """);
        l.setCodeExampleTitle("for-løkke eksempler");
        l.setCodeExample("""
            public class ForLoekke {
                public static void main(String[] args) {
                    // Tell fra 1 til 5
                    for (int i = 1; i <= 5; i++) {
                        System.out.println("Tall: " + i);
                    }

                    // Summer tallene 1 til 10
                    int sum = 0;
                    for (int i = 1; i <= 10; i++) {
                        sum += i;
                    }
                    System.out.println("Sum 1-10: " + sum);  // 55

                    // Tell bakover
                    for (int i = 5; i >= 1; i--) {
                        System.out.println(i + "...");
                    }
                    System.out.println("Ferdig!");
                }
            }
            """);

        Exercise e1 = mc(
            "Hvor mange ganger kjøres løkken?\nfor (int i = 0; i < 8; i++) {\n    System.out.println(i);\n}",
            List.of("7", "8", "9", "0"),
            1,
            "i starter på 0 og kjøres mens i < 8. Det gir i = 0, 1, 2, 3, 4, 5, 6, 7 = 8 ganger.",
            "Riktig! i går fra 0 til 7 (8 iterasjoner totalt).",
            "i starter på 0 og stopper når i = 8. Det gir 8 runder (0, 1, 2, 3, 4, 5, 6, 7)."
        );
        Exercise e2 = mc(
            "Hva skrives ut av dette?\nint sum = 0;\nfor (int i = 1; i <= 4; i++) {\n    sum += i;\n}\nSystem.out.println(sum);",
            List.of("4", "6", "10", "16"),
            2,
            "sum = 0 + 1 + 2 + 3 + 4 = 10",
            "Riktig! 1 + 2 + 3 + 4 = 10.",
            "Summer steg for steg: sum: 0 → 1 → 3 → 6 → 10."
        );
        Exercise e3 = code(
            "Skriv en for-løkke som skriver ut tallene 1 til 10.",
            "for (int i = 1; i <= 10; i++) { System.out.println(i); }",
            "public class",
            List.of("for", "int i", "System.out.println"),
            """
            public class TallRekke {
                public static void main(String[] args) {
                    for (int i = 1; i <= 10; i++) {
                        System.out.println(i);
                    }
                }
            }
            """,
            "Eksellent! Du kan nå bruke for-løkker til å gjenta kode! 🔄",
            "Husk: for (int i = 1; i <= 10; i++) { System.out.println(i); }"
        );

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    private static Lesson lesson4_3() {
        Lesson l = new Lesson();
        l.setId(10); l.setModuleId(4);
        l.setTitle("while-løkker");
        l.setGuideContent("""
            <h2>while-løkker</h2>
            <p>En <code>while</code>-løkke gjentar kode <strong>så lenge en betingelse er true</strong>. Den brukes når du ikke vet på forhånd hvor mange ganger koden skal kjøre.</p>
            <h3>Struktur</h3>
            <div class="note">
                <code>while (betingelse) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>// kode som gjentas</code><br>
                <code>}</code>
            </div>
            <h3>while vs for</h3>
            <table class="data-table">
                <tr><th></th><th>for-løkke</th><th>while-løkke</th></tr>
                <tr><td><strong>Bruk når</strong></td><td>Du vet antall ganger</td><td>Du vet ikke antall ganger</td></tr>
                <tr><td><strong>Eksempel</strong></td><td>Gå gjennom en liste</td><td>Les input til bruker skriver "quit"</td></tr>
            </table>
            <div class="tip">
                <strong>⚠️ Unngå uendelige løkker!</strong><br>
                Sørg alltid for at betingelsen til slutt blir false, ellers kjører programmet for alltid:<br>
                <code>int i = 0;</code><br>
                <code>while (i < 5) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>System.out.println(i);</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>i++;  // ← VIKTIG! Glem aldri dette</code><br>
                <code>}</code>
            </div>
            <h3>do-while</h3>
            <p>Kjøres <strong>minst én gang</strong>, deretter sjekkes betingelsen:</p>
            <div class="note">
                <code>do {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>// kjøres alltid minst én gang</code><br>
                <code>} while (betingelse);</code>
            </div>
            """);
        l.setCodeExampleTitle("while-løkke eksempel");
        l.setCodeExample("""
            public class WhileEksempel {
                public static void main(String[] args) {
                    // Tell ned fra 5
                    int teller = 5;
                    while (teller > 0) {
                        System.out.println(teller);
                        teller--;
                    }
                    System.out.println("Klar!");

                    // Finn første tall delelig med 7 over 50
                    int tall = 51;
                    while (tall % 7 != 0) {
                        tall++;
                    }
                    System.out.println("Første tall delelig med 7 over 50: " + tall);

                    // do-while – kjøres alltid minst én gang
                    int x = 10;
                    do {
                        System.out.println("x er: " + x);
                        x++;
                    } while (x < 10);  // Betingelse er false fra start, men blokken kjøres én gang
                }
            }
            """);

        Exercise e1 = mc(
            "Hva skriver dette ut?\nint i = 0;\nwhile (i < 3) {\n    System.out.println(i);\n    i++;\n}",
            List.of("0 1 2 3", "0 1 2", "1 2 3", "Ingenting"),
            1,
            "i starter på 0. Løkken kjøres mens i < 3, dvs. for i = 0, 1, 2.",
            "Riktig! i = 0, 1, 2 skrives ut. Når i = 3 stopper løkken.",
            "i starter på 0, økes med 1 hver gang, og stopper når i = 3. Skriver ut 0, 1, 2."
        );
        Exercise e2 = mc(
            "Hva er forskjellen mellom while og do-while?",
            List.of(
                "Det er ingen forskjell",
                "do-while kjøres alltid minst én gang, while kan hoppe over",
                "while er raskere enn do-while",
                "do-while er for tall, while er for tekst"
            ),
            1,
            "do-while sjekker betingelsen ETTER første kjøring, så blokken kjøres alltid minst én gang.",
            "Riktig! do-while garanterer minst én kjøring, uansett betingelsen.",
            "do-while sjekker betingelsen etter første kjøring, while sjekker den FØR."
        );
        Exercise e3 = code(
            "Skriv en while-løkke som summerer tallene fra 1 til 5 og skriver ut summen.",
            "int sum = 0; int i = 1; while (i <= 5) { sum += i; i++; } System.out.println(sum);",
            "public class",
            List.of("while", "sum", "System.out.println"),
            """
            public class WhileSum {
                public static void main(String[] args) {
                    int sum = 0;
                    int i = 1;
                    while (i <= 5) {
                        sum += i;
                        i++;
                    }
                    System.out.println(sum);
                }
            }
            """,
            "Meget bra! Du kan nå bruke while-løkker! ⚙️",
            "Husk: int sum = 0; int i = 1; while (i <= 5) { sum += i; i++; }"
        );

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    private static Lesson lesson4_4() {
        Lesson l = new Lesson();
        l.setId(11); l.setModuleId(4);
        l.setTitle("Metoder");
        l.setGuideContent("""
            <h2>Metoder – gjenbrukbar kode</h2>
            <p>En metode er en navngitt blokk med kode som kan kalles (kjøres) mange ganger. Metoder gjør koden mer organisert og gjenbrukbar.</p>
            <h3>Struktur</h3>
            <div class="note">
                <code>[tilgang] [returtype] metodenavn([parametere]) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>// kode</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>return verdi;  // hvis ikke void</code><br>
                <code>}</code>
            </div>
            <h3>De fire delene forklart</h3>
            <table class="data-table">
                <tr><th>Del</th><th>Eksempel</th><th>Forklaring</th></tr>
                <tr><td>Tilgang</td><td><code>public</code></td><td>Hvem kan kalle metoden</td></tr>
                <tr><td>Returtype</td><td><code>int</code>, <code>String</code>, <code>void</code></td><td>Hva metoden returnerer (<code>void</code> = ingenting)</td></tr>
                <tr><td>Navn</td><td><code>beregnSum</code></td><td>Hva metoden gjør (camelCase verb)</td></tr>
                <tr><td>Parametere</td><td><code>(int a, int b)</code></td><td>Input til metoden</td></tr>
            </table>
            <div class="note">
                <strong>Eksempel: metode med retur</strong><br>
                <code>public static int leggSammen(int a, int b) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>return a + b;</code><br>
                <code>}</code><br><br>
                <strong>Kalle metoden:</strong><br>
                <code>int resultat = leggSammen(3, 5);  // resultat = 8</code>
            </div>
            <div class="tip">
                <strong>💡 void vs returtype</strong><br>
                <code>void</code> → metoden returnerer ingenting (gjør bare noe)<br>
                <code>int</code> → metoden returnerer et heltall<br>
                <code>String</code> → metoden returnerer tekst
            </div>
            """);
        l.setCodeExampleTitle("Metoder i praksis");
        l.setCodeExample("""
            public class MetodeEksempel {

                // Metode uten retur (void)
                public static void hils(String navn) {
                    System.out.println("Hei, " + navn + "!");
                }

                // Metode med int-retur
                public static int leggSammen(int a, int b) {
                    return a + b;
                }

                // Metode med boolean-retur
                public static boolean erPositiv(int tall) {
                    return tall > 0;
                }

                public static void main(String[] args) {
                    hils("Ola");                          // Hei, Ola!
                    hils("Kari");                         // Hei, Kari!

                    int sum = leggSammen(10, 25);
                    System.out.println("Sum: " + sum);    // Sum: 35

                    boolean positiv = erPositiv(-5);
                    System.out.println(positiv);          // false
                }
            }
            """);

        Exercise e1 = mc(
            "Hva betyr 'void' som returtype?",
            List.of(
                "Metoden returnerer et tomt tall (0)",
                "Metoden returnerer ingenting",
                "Metoden kan ikke kalles",
                "Metoden returnerer null alltid"
            ),
            1,
            "void betyr \"tom\" – metoden gjør noe, men gir ikke tilbake noen verdi.",
            "Riktig! void betyr at metoden ikke returnerer noen verdi.",
            "void betyr at metoden ikke returnerer noe. Den bare utfører kode."
        );
        Exercise e2 = mc(
            "Hva skriver dette ut?\npublic static int dobbel(int x) {\n    return x * 2;\n}\n// I main:\nSystem.out.println(dobbel(7));",
            List.of("7", "2", "14", "Kompileringsfeil"),
            2,
            "dobbel(7) returnerer 7 * 2 = 14, som skrives ut.",
            "Riktig! dobbel(7) beregner 7 * 2 = 14 og returnerer det.",
            "dobbel(7) → x = 7 → return 7 * 2 → return 14 → println(14)."
        );
        Exercise e3 = code(
            "Skriv en metode kalt 'kvadrat' som tar en int og returnerer tallet ganget med seg selv. Kall den med verdien 5 og skriv ut resultatet.",
            "public static int kvadrat(int n) { return n * n; }",
            "public class",
            List.of("static int kvadrat", "return", "System.out.println"),
            """
            public class Kvadrat {
                public static int kvadrat(int n) {
                    return n * n;
                }

                public static void main(String[] args) {
                    int resultat = kvadrat(5);
                    System.out.println(resultat);
                }
            }
            """,
            "Bravo! Du kan nå skrive dine egne metoder! 🏆",
            "Husk: public static int kvadrat(int n) { return n * n; } og kall den i main."
        );

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── MODULE 5: Klasser og Objekter ───────────────────────────────────────

    private static CourseModule createModule5() {
        CourseModule m = new CourseModule();
        m.setId(5);
        m.setTitle("Klasser og Objekter");
        m.setDescription("Lær grunnleggende OOP: klasser, objekter, innkapsling og konstruktører.");
        m.setIcon("🏗️");
        m.setColor("#6366f1");
        m.setAvailable(true);
        m.setLessons(List.of(lesson5_1(), lesson5_2()));
        return m;
    }

    private static Lesson lesson5_1() {
        Lesson l = new Lesson();
        l.setId(12); l.setModuleId(5);
        l.setTitle("Klasser og objekter");
        l.setGuideContent("""
            <h2>Klasser og objekter</h2>
            <p>Java er et <strong>objektorientert</strong> programmeringsspråk (OOP). Det betyr at vi organiserer koden i <em>klasser</em> og <em>objekter</em>.</p>
            <h3>Hva er en klasse?</h3>
            <p>En klasse er en <strong>mal</strong> eller <strong>oppskrift</strong>. Den beskriver hva noe ER og KAN GJØRE.</p>
            <div class="note">
                Tenk på en klasse som en tegning av et hus – ikke selve huset, men oppskriften for å bygge det.
            </div>
            <h3>Hva er et objekt?</h3>
            <p>Et objekt er en <strong>konkret instans</strong> av en klasse – det faktiske huset bygget fra tegningen.</p>
            <div class="note">
                <strong>Klasse vs Objekt:</strong><br>
                Klasse <code>Bil</code> → beskriver hva en bil er (farge, fart, modell)<br>
                Objekt <code>minBil</code> → <em>én bestemt</em> bil med konkrete verdier
            </div>
            <h3>Struktur av en klasse</h3>
            <div class="note">
                <code>public class Bil {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>// Felter (variabler som tilhører klassen)</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>String modell;</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>int hastighet;</code><br><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>// Metoder (hva bilen kan gjøre)</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>void akselerere() {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>hastighet += 10;</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>}</code><br>
                <code>}</code>
            </div>
            <h3>Opprette et objekt</h3>
            <div class="note">
                <code>Bil minBil = new Bil();</code><br>
                <code>minBil.modell = "Tesla";</code><br>
                <code>minBil.akselerere();</code>
            </div>
            <div class="tip">
                <strong>💡 new-nøkkelordet</strong><br>
                <code>new</code> oppretter et nytt objekt i minnet. Hvert objekt er uavhengig – to Bil-objekter kan ha forskjellige verdier.
            </div>
            """);
        l.setCodeExampleTitle("Klasse og objekt eksempel");
        l.setCodeExample("""
            public class Bil {
                // Felter
                String modell;
                String farge;
                int hastighet;

                // Metode
                void akselerere(int økning) {
                    hastighet += økning;
                    System.out.println(modell + " kjører nå " + hastighet + " km/t");
                }

                void beskriv() {
                    System.out.println("Bil: " + modell + ", farge: " + farge);
                }
            }

            // I en annen klasse (f.eks. Main):
            public class Main {
                public static void main(String[] args) {
                    Bil bil1 = new Bil();
                    bil1.modell = "Tesla";
                    bil1.farge = "Rød";
                    bil1.beskriv();
                    bil1.akselerere(50);

                    Bil bil2 = new Bil();
                    bil2.modell = "Volvo";
                    bil2.farge = "Hvit";
                    bil2.beskriv();
                }
            }
            """);

        Exercise e1 = mc(
            "Hva er forskjellen på en klasse og et objekt?",
            List.of(
                "Det er ingen forskjell, de er det samme",
                "Klassen er malen, objektet er en konkret instans av malen",
                "Objekter kan bare ha tall, klasser kan ha tekst",
                "Klassen er en metode, objektet er en variabel"
            ),
            1,
            "En klasse er blueprinten/oppskriften. Et objekt er noe konkret laget fra den oppskriften.",
            "Riktig! Klassen er malen, og objektet er det konkrete eksemplaret.",
            "En klasse = mal/oppskrift. Et objekt = konkret instans laget med 'new'."
        );
        Exercise e2 = mc(
            "Hva gjør new-nøkkelordet?",
            List.of(
                "Sletter et objekt fra minnet",
                "Oppretter et nytt objekt i minnet",
                "Deklarerer en ny klasse",
                "Lager en ny variabel"
            ),
            1,
            "new allokerer minne og oppretter en ny instans av klassen.",
            "Riktig! new oppretter et nytt objekt i minnet.",
            "new brukes for å opprette (instantiere) et nytt objekt: Klasse obj = new Klasse();"
        );
        Exercise e3 = code(
            "Lag en klasse 'Hund' med feltene navn (String) og alder (int). Opprett et objekt og gi det verdier.",
            "public class Hund { String navn; int alder; }  // Deretter: Hund h = new Hund();",
            "public class Hund",
            List.of("String navn", "int alder"),
            """
            public class Hund {
                String navn;
                int alder;

                public static void main(String[] args) {
                    Hund minHund = new Hund();
                    minHund.navn = "Rex";
                    minHund.alder = 3;
                    System.out.println(minHund.navn + " er " + minHund.alder + " år");
                }
            }
            """,
            "Strålende! Du kan nå lage egne klasser og objekter! 🐶",
            "Husk: class Hund { String navn; int alder; } og Hund h = new Hund();"
        );

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    private static Lesson lesson5_2() {
        Lesson l = new Lesson();
        l.setId(13); l.setModuleId(5);
        l.setTitle("Innkapsling og konstruktører");
        l.setGuideContent("""
            <h2>Innkapsling og konstruktører</h2>
            <h3>Innkapsling (Encapsulation)</h3>
            <p>Innkapsling betyr å <strong>beskytte feltene</strong> i en klasse ved å gjøre dem private, og bare tillate tilgang via metoder (getters og setters).</p>
            <div class="note">
                <strong>Uten innkapsling (dårlig praksis):</strong><br>
                <code>bil.hastighet = -100;  // Ugyldig verdi!</code><br><br>
                <strong>Med innkapsling (god praksis):</strong><br>
                <code>private int hastighet;</code><br>
                <code>public void setHastighet(int h) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>if (h >= 0) this.hastighet = h;</code><br>
                <code>}</code>
            </div>
            <h3>Konstruktører</h3>
            <p>En konstruktør er en spesiell metode som kjøres automatisk når et objekt opprettes med <code>new</code>.</p>
            <div class="note">
                <strong>Regler for konstruktører:</strong>
                <ul>
                    <li>Samme navn som klassen</li>
                    <li>Ingen returtype (ikke engang void)</li>
                    <li>Kalles automatisk ved new</li>
                </ul>
            </div>
            <div class="tip">
                <strong>💡 this-nøkkelordet</strong><br>
                <code>this</code> refererer til <em>dette objektet</em>. Brukes når parameternavnet er likt feltnavnet:<br>
                <code>public void setNavn(String navn) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>this.navn = navn;  // this.navn = feltet, navn = parameteren</code><br>
                <code>}</code>
            </div>
            """);
        l.setCodeExampleTitle("Innkapsling og konstruktør");
        l.setCodeExample("""
            public class Person {
                // Private felter – kan ikke nås direkte utenfra
                private String navn;
                private int alder;

                // Konstruktør – kjøres ved new Person(...)
                public Person(String navn, int alder) {
                    this.navn = navn;
                    this.alder = alder;
                }

                // Getter – henter verdien
                public String getNavn() {
                    return navn;
                }

                // Setter – setter verdien med validering
                public void setAlder(int alder) {
                    if (alder >= 0 && alder <= 150) {
                        this.alder = alder;
                    }
                }

                public int getAlder() {
                    return alder;
                }

                @Override
                public String toString() {
                    return navn + " (" + alder + " år)";
                }
            }

            // I main:
            Person p = new Person("Ola", 25);
            System.out.println(p.getNavn());   // Ola
            System.out.println(p);             // Ola (25 år)
            """);

        Exercise e1 = mc(
            "Hva er hensikten med private felter og getters/setters?",
            List.of(
                "Det gjør programmet raskere",
                "Det beskytter feltene og gir kontroll over hvilke verdier som er gyldige",
                "Det er bare en konvensjon uten praktisk formål",
                "Det sparer minne"
            ),
            1,
            "Innkapsling gir kontroll. Du kan validere data i setteren og hindre ugyldig tilstand.",
            "Riktig! Innkapsling beskytter data og gir mulighet for validering.",
            "Innkapsling = beskytte felter med private + kontrollere tilgang via metoder."
        );
        Exercise e2 = mc(
            "Hva er riktig for en konstruktør?",
            List.of(
                "Den må hete 'konstruktør'",
                "Den må ha 'void' som returtype",
                "Den har samme navn som klassen og ingen returtype",
                "Den kalles manuelt etter new"
            ),
            2,
            "Konstruktøren har eksakt samme navn som klassen og ingen returtype (ikke engang void).",
            "Riktig! Konstruktøren har samme navn som klassen og ingen returtype.",
            "Konstruktør: samme navn som klassen, ingen returtype, kalles automatisk av new."
        );
        Exercise e3 = code(
            "Lag en klasse 'Katt' med private felt navn (String) og en konstruktør som setter navnet. Legg til en getter for navn.",
            "private String navn;  public Katt(String navn) { this.navn = navn; }  public String getNavn() { return navn; }",
            "public class Katt",
            List.of("private String navn", "public Katt(", "this.navn", "getNavn"),
            """
            public class Katt {
                private String navn;

                public Katt(String navn) {
                    this.navn = navn;
                }

                public String getNavn() {
                    return navn;
                }

                public static void main(String[] args) {
                    Katt k = new Katt("Pus");
                    System.out.println(k.getNavn());
                }
            }
            """,
            "Imponerende! Du behersker nå innkapsling og konstruktører! 🔐",
            "Husk: private felt, konstruktør med this.navn = navn, og getter-metode."
        );

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── Helper: Coming Soon ─────────────────────────────────────────────────

    private static CourseModule createComingSoon(int id, String title, String desc, String icon, String color) {
        CourseModule m = new CourseModule();
        m.setId(id);
        m.setTitle(title);
        m.setDescription(desc);
        m.setIcon(icon);
        m.setColor(color);
        m.setAvailable(false);
        m.setLessons(List.of());
        return m;
    }

    // ─── Factory helpers (public – used by helper files) ─────────────────────

    public static Exercise mc(ExIdRef r, String question, List<String> options, int correctIndex,
                               String explanation, String correctFeedback, String incorrectFeedback) {
        return mc(question, options, correctIndex, explanation, correctFeedback, incorrectFeedback);
    }

    public static Exercise code(ExIdRef r, String question, String hint, String template,
                                 List<String> patterns, String solution,
                                 String correctFeedback, String incorrectFeedback) {
        return code(question, hint, template, patterns, solution, correctFeedback, incorrectFeedback);
    }

    // ─── Factory helpers (private) ────────────────────────────────────────────

    private static Exercise mc(String question, List<String> options, int correctIndex,
                                String explanation, String correctFeedback, String incorrectFeedback) {
        Exercise e = new Exercise();
        e.setId(exId++);
        e.setType(ExerciseType.MULTIPLE_CHOICE);
        e.setQuestion(question);
        e.setOptions(options);
        e.setCorrectOptionIndex(correctIndex);
        e.setExplanation(explanation);
        e.setCorrectFeedback(correctFeedback);
        e.setIncorrectFeedback(incorrectFeedback);
        return e;
    }

    private static Exercise code(String question, String hint, String template,
                                  List<String> patterns, String solution,
                                  String correctFeedback, String incorrectFeedback) {
        Exercise e = new Exercise();
        e.setId(exId++);
        e.setType(ExerciseType.CODE_WRITE);
        e.setQuestion(question);
        e.setHint(hint);
        e.setCodeTemplate(template);
        e.setRequiredPatterns(patterns);
        e.setSolutionCode(solution);
        e.setCorrectFeedback(correctFeedback);
        e.setIncorrectFeedback(incorrectFeedback);
        return e;
    }
}
