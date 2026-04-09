package no.javaspill.data;

import no.javaspill.model.*;
import java.util.List;

// Helper file – methods called from CourseData
public class CourseDataModules6to12 {

    // ─── MODULE 6: ArrayList og Array ────────────────────────────────────────

    static CourseModule createModule6(CourseData.ExIdRef r) {
        CourseModule m = new CourseModule();
        m.setId(6); m.setTitle("Array og ArrayList");
        m.setDescription("Faste og dynamiske lister – de viktigste datastrukturene i Java.");
        m.setIcon("📋"); m.setColor("#8b5cf6"); m.setAvailable(true);
        m.setLessons(List.of(lesson6_1(r), lesson6_2(r)));
        return m;
    }

    static Lesson lesson6_1(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(14); l.setModuleId(6); l.setTitle("Array – fast størrelse");
        l.setGuideContent("""
            <h2>Array – en samling av like elementer</h2>
            <p>Et array lagrer flere verdier av <strong>samme type</strong> i rekkefølge. Størrelsen settes én gang og kan ikke endres.</p>
            <div class="note">
                <strong>To måter å opprette:</strong><br>
                <code>int[] tall = new int[5];</code> – 5 plasser, alle 0<br>
                <code>int[] tall = {10, 20, 30, 40, 50};</code> – med verdier direkte
            </div>
            <h3>Index starter på 0</h3>
            <div class="note">
                <code>tall[0]</code> → første element<br>
                <code>tall[tall.length - 1]</code> → siste element
            </div>
            <div class="tip">
                <strong>⚠️ IndexOutOfBoundsException</strong><br>
                Prøver du å bruke en index utenfor arrayet krasjer programmet. Med <code>new int[5]</code> er gyldig index 0–4.
            </div>
            <h3>Gå gjennom med for-løkke</h3>
            <div class="note">
                <code>for (int i = 0; i &lt; tall.length; i++) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>System.out.println(tall[i]);</code><br>
                <code>}</code><br><br>
                Eller enklere med for-each:<br>
                <code>for (int t : tall) { System.out.println(t); }</code>
            </div>
            """);
        l.setCodeExampleTitle("Array i praksis");
        l.setCodeExample("""
            public class ArrayEksempel {
                public static void main(String[] args) {
                    String[] navn = {"Ola", "Kari", "Per"};

                    System.out.println(navn[0]);               // Ola
                    System.out.println(navn[navn.length - 1]); // Per

                    // Endre element
                    navn[1] = "Lise";

                    // Gå gjennom alle
                    for (String n : navn) {
                        System.out.println(n);
                    }

                    // Tallarray
                    int[] poeng = {85, 92, 78};
                    int sum = 0;
                    for (int p : poeng) sum += p;
                    System.out.println("Snitt: " + sum / poeng.length);
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva er index til det første elementet i et array?",
            List.of("1", "0", "-1", "Avhenger av typen"),
            1, "Arrays er 0-indekserte. Første element er alltid index 0.",
            "Riktig! Første element er alltid index 0.", "Arrays starter alltid på 0.");

        Exercise e2 = CourseData.mc(r,
            "Hva skriver dette ut?\nint[] tall = {5, 10, 15};\nSystem.out.println(tall.length);",
            List.of("2", "3", "15", "0"),
            1, "length gir antall elementer. {5,10,15} har 3 elementer.",
            "Riktig! length = 3.", "tall.length = antall elementer = 3.");

        Exercise e3 = CourseData.code(r,
            "Lag et int-array med tallene 1, 2, 3 og skriv ut det midterste elementet.",
            "int[] tall = {1, 2, 3}; System.out.println(tall[1]);",
            "public class", List.of("int[]", "tall[1]", "System.out.println"),
            "public class MidtElement {\n    public static void main(String[] args) {\n        int[] tall = {1, 2, 3};\n        System.out.println(tall[1]);\n    }\n}",
            "Bra! Du kan nå jobbe med arrays! 📦",
            "Husk: int[] tall = {1, 2, 3}; og tall[1] for midterste.");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson6_2(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(15); l.setModuleId(6); l.setTitle("ArrayList – dynamisk liste");
        l.setGuideContent("""
            <h2>ArrayList – listen som vokser</h2>
            <p>Til forskjell fra et vanlig array kan en <code>ArrayList</code> endre størrelse automatisk. Du kan legge til og fjerne elementer når du vil.</p>
            <div class="note">
                <strong>Import og opprett:</strong><br>
                <code>import java.util.ArrayList;</code><br>
                <code>ArrayList&lt;String&gt; liste = new ArrayList&lt;&gt;();</code><br>
                Typen i &lt; &gt; sier hva listen skal inneholde.
            </div>
            <h3>Viktige metoder</h3>
            <table class="data-table">
                <tr><th>Metode</th><th>Hva den gjør</th></tr>
                <tr><td><code>.add(element)</code></td><td>Legger til element på slutten</td></tr>
                <tr><td><code>.get(index)</code></td><td>Henter element på gitt index</td></tr>
                <tr><td><code>.remove(index)</code></td><td>Fjerner element på gitt index</td></tr>
                <tr><td><code>.size()</code></td><td>Antall elementer</td></tr>
                <tr><td><code>.contains(x)</code></td><td>Sjekker om x finnes</td></tr>
                <tr><td><code>.clear()</code></td><td>Tømmer listen</td></tr>
            </table>
            <div class="tip">
                <strong>💡 Array vs ArrayList</strong><br>
                Bruk <code>int[]</code> når du vet størrelsen på forhånd.<br>
                Bruk <code>ArrayList&lt;Integer&gt;</code> når antall elementer varierer.
            </div>
            """);
        l.setCodeExampleTitle("ArrayList i praksis");
        l.setCodeExample("""
            import java.util.ArrayList;

            public class ListeEksempel {
                public static void main(String[] args) {
                    ArrayList<String> navn = new ArrayList<>();

                    navn.add("Ola");
                    navn.add("Kari");
                    navn.add("Per");

                    System.out.println(navn.size());      // 3
                    System.out.println(navn.get(0));      // Ola
                    System.out.println(navn.contains("Kari")); // true

                    navn.remove(1);  // fjerner "Kari"
                    System.out.println(navn);  // [Ola, Per]

                    // Gå gjennom
                    for (String n : navn) {
                        System.out.println("Hei, " + n);
                    }
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hvilken metode bruker du for å legge til et element i en ArrayList?",
            List.of(".put()", ".add()", ".insert()", ".append()"),
            1, "add() legger til et element på slutten av listen.",
            "Riktig! .add() legger til elementet.", "Det er .add() som legger til elementer i ArrayList.");

        Exercise e2 = CourseData.mc(r,
            "Hva skriver dette ut?\nArrayList<Integer> l = new ArrayList<>();\nl.add(10); l.add(20); l.add(30);\nSystem.out.println(l.size());",
            List.of("2", "30", "3", "0"),
            2, "size() returnerer antall elementer. Vi la til 3 elementer.",
            "Riktig! size() = 3.", "Vi la til 3 elementer, så size() = 3.");

        Exercise e3 = CourseData.code(r,
            "Lag en ArrayList med tre byer (Strings), legg til Bergen, Oslo og Trondheim, og skriv ut listen.",
            "ArrayList<String> byer = new ArrayList<>();  byer.add(\"Bergen\"); ...",
            "import java.util.ArrayList", List.of("ArrayList", "add(", "System.out.println"),
            "import java.util.ArrayList;\npublic class Byer {\n    public static void main(String[] args) {\n        ArrayList<String> byer = new ArrayList<>();\n        byer.add(\"Bergen\");\n        byer.add(\"Oslo\");\n        byer.add(\"Trondheim\");\n        System.out.println(byer);\n    }\n}",
            "Utmerket! Du kan nå bruke ArrayList! 🗒️",
            "Husk: import java.util.ArrayList; og bruk .add() for å legge til.");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── MODULE 7: Arv og Polymorfisme ───────────────────────────────────────

    static CourseModule createModule7(CourseData.ExIdRef r) {
        CourseModule m = new CourseModule();
        m.setId(7); m.setTitle("Arv og Polymorfisme");
        m.setDescription("Arv, overriding, polymorfisme, toString og instanceof.");
        m.setIcon("🧬"); m.setColor("#ec4899"); m.setAvailable(true);
        m.setLessons(List.of(lesson7_1(r), lesson7_2(r), lesson7_3(r)));
        return m;
    }

    static Lesson lesson7_1(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(16); l.setModuleId(7); l.setTitle("Arv – extends");
        l.setGuideContent("""
            <h2>Arv – gjenbruk av kode</h2>
            <p>Med arv kan en klasse <strong>arve</strong> felter og metoder fra en annen klasse. Klassen som arver kalles <em>subklasse</em>, og den den arver fra er <em>superklassen</em>.</p>
            <div class="note">
                <strong>Nøkkelordet extends:</strong><br>
                <code>public class Hund extends Dyr { ... }</code><br>
                Hund arver alt public/protected fra Dyr.
            </div>
            <h3>super() – kall superklassens konstruktør</h3>
            <div class="note">
                <code>public class Hund extends Dyr {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>public Hund(String navn) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>super(navn); // kaller Dyr sin konstruktør</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>}</code><br>
                <code>}</code>
            </div>
            <div class="tip">
                <strong>💡 Huskeregel</strong><br>
                Arv brukes for «er-et»-relasjoner: En Hund <em>er et</em> Dyr ✅<br>
                Ikke for «har-et»: En Bil <em>har en</em> Motor (bruk da et felt, ikke arv) ✅
            </div>
            """);
        l.setCodeExampleTitle("Arv med extends");
        l.setCodeExample("""
            public class Dyr {
                private String navn;

                public Dyr(String navn) {
                    this.navn = navn;
                }

                public String getNavn() { return navn; }

                public void spis() {
                    System.out.println(navn + " spiser.");
                }
            }

            public class Hund extends Dyr {
                private String rase;

                public Hund(String navn, String rase) {
                    super(navn); // kaller Dyr sin konstruktør
                    this.rase = rase;
                }

                public void bjeff() {
                    System.out.println(getNavn() + " sier: Voff!");
                }
            }

            // I main:
            Hund h = new Hund("Rex", "Labrador");
            h.spis();   // arvet fra Dyr
            h.bjeff();  // Hundens egen
            """);

        Exercise e1 = CourseData.mc(r,
            "Hvilket nøkkelord brukes for arv i Java?",
            List.of("implements", "inherits", "extends", "super"),
            2, "extends brukes for å arve fra en klasse.",
            "Riktig! extends er nøkkelordet for arv.", "Det er extends som brukes: class Hund extends Dyr");

        Exercise e2 = CourseData.mc(r,
            "Hva gjør super() i en subklasses konstruktør?",
            List.of(
                "Sletter superklassens objekt",
                "Kaller superklassens konstruktør",
                "Lager et nytt superklasse-objekt",
                "Overstyrer superklassens metode"
            ),
            1, "super() kaller konstruktøren til superklassen – nødvendig for å initialisere arvede felter.",
            "Riktig! super() kaller superklassens konstruktør.", "super() = kall til superklassens konstruktør.");

        Exercise e3 = CourseData.code(r,
            "Lag en klasse 'Katt' som arver fra 'Dyr'. Katt skal ha en metode mjau() som skriver ut \"Mjau!\".",
            "public class Katt extends Dyr { public void mjau() { System.out.println(\"Mjau!\"); } }",
            "class Katt extends Dyr", List.of("extends Dyr", "mjau", "System.out.println"),
            "public class Katt extends Dyr {\n    public Katt(String navn) {\n        super(navn);\n    }\n    public void mjau() {\n        System.out.println(\"Mjau!\");\n    }\n}",
            "Fint! Du kan nå bruke arv med extends! 🐾",
            "Husk: class Katt extends Dyr og en mjau()-metode.");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson7_2(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(17); l.setModuleId(7); l.setTitle("Polymorfisme og Overriding");
        l.setGuideContent("""
            <h2>Polymorfisme og @Override</h2>
            <h3>Overriding – overstyre en metode</h3>
            <p>En subklasse kan gi sin <strong>egen versjon</strong> av en metode fra superklassen. Dette kalles overriding.</p>
            <div class="note">
                <code>@Override</code> – annotasjon som sier at metoden overstyrer en arvet metode.<br>
                Bruk alltid @Override – det fanger feil tidlig!
            </div>
            <h3>Polymorfisme</h3>
            <p>Polymorfisme betyr at du kan behandle objekter av ulike typer likt, gjennom en felles supertype.</p>
            <div class="note">
                <code>Dyr d = new Hund("Rex", "Lab");</code><br>
                <code>d.lagLyd(); // kaller Hund sin versjon</code><br><br>
                Java bestemmer hvilken versjon av metoden som kjøres basert på <em>objektets faktiske type</em>, ikke variabeltypen.
            </div>
            <div class="tip">
                <strong>💡 Fordelen med polymorfisme</strong><br>
                Du kan ha en liste: <code>ArrayList&lt;Dyr&gt;</code> med både Hund og Katt,<br>
                og kalle <code>lagLyd()</code> på alle – riktig lyd spilles automatisk!
            </div>
            """);
        l.setCodeExampleTitle("Override og polymorfisme");
        l.setCodeExample("""
            public class Dyr {
                public void lagLyd() {
                    System.out.println("...");
                }
            }

            public class Hund extends Dyr {
                @Override
                public void lagLyd() {
                    System.out.println("Voff!");
                }
            }

            public class Katt extends Dyr {
                @Override
                public void lagLyd() {
                    System.out.println("Mjau!");
                }
            }

            // Polymorfisme i praksis:
            import java.util.ArrayList;
            ArrayList<Dyr> dyr = new ArrayList<>();
            dyr.add(new Hund());
            dyr.add(new Katt());
            dyr.add(new Hund());

            for (Dyr d : dyr) {
                d.lagLyd(); // Voff!, Mjau!, Voff!
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva betyr @Override-annotasjonen?",
            List.of(
                "Metoden er private",
                "Metoden overstyrer en arvet metode fra superklassen",
                "Metoden kan ikke kalles fra subklasser",
                "Metoden er statisk"
            ),
            1, "@Override signalerer at du overstyrer en metode fra superklassen og hjelper kompilatoren å oppdage feil.",
            "Riktig! @Override = overstyr en metode fra superklassen.", "@Override markerer at du overstyrer en metode fra superklassen.");

        Exercise e2 = CourseData.mc(r,
            "Hva er polymorfisme?",
            List.of(
                "En klasse som arver fra to superklasser",
                "Evnen til å behandle objekter av ulike typer via en felles supertype",
                "At alle metoder heter det samme",
                "Kopiering av en klasse"
            ),
            1, "Polymorfisme = mange former. Samme kode kan fungere på objekter av ulike typer.",
            "Riktig! Polymorfisme lar deg behandle ulike objekter via felles supertype.",
            "Polymorfisme = behandle Hund og Katt som Dyr, og la Java kalle riktig metode.");

        Exercise e3 = CourseData.code(r,
            "Lag en klasse 'Fugl' som arver fra 'Dyr' og overstyrer lagLyd() til å skrive \"Kvitter!\".",
            "@Override public void lagLyd() { System.out.println(\"Kvitter!\"); }",
            "class Fugl extends Dyr", List.of("extends Dyr", "@Override", "lagLyd", "Kvitter"),
            "public class Fugl extends Dyr {\n    @Override\n    public void lagLyd() {\n        System.out.println(\"Kvitter!\");\n    }\n}",
            "Flott! Du behersker nå overriding og polymorfisme! 🐦",
            "Husk: extends Dyr, @Override, og lagLyd() som skriver Kvitter!");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson7_3(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(18); l.setModuleId(7); l.setTitle("toString, instanceof og Overloading");
        l.setGuideContent("""
            <h2>toString(), instanceof og Overloading</h2>
            <h3>toString() – tekstrepresentasjon av objektet</h3>
            <p>Alle klasser arver <code>toString()</code> fra <code>Object</code>. Override den for å gi objektet en lesbar tekst.</p>
            <div class="note">
                <code>@Override</code><br>
                <code>public String toString() {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>return "Hund{navn=" + navn + "}";</code><br>
                <code>}</code><br><br>
                Nå skrives objektet pent ut: <code>System.out.println(hund);</code>
            </div>
            <h3>instanceof – sjekk type</h3>
            <div class="note">
                <code>if (dyr instanceof Hund h) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>h.bjeff(); // trygt å kalle Hund-metoder</code><br>
                <code>}</code>
            </div>
            <h3>Overloading – samme navn, ulike parametere</h3>
            <p>Du kan ha flere metoder med <strong>samme navn</strong> men ulike parameterlister.</p>
            <div class="note">
                <code>public int leggSammen(int a, int b) { return a+b; }</code><br>
                <code>public double leggSammen(double a, double b) { return a+b; }</code><br>
                Java velger riktig versjon basert på argumentene du sender inn.
            </div>
            """);
        l.setCodeExampleTitle("toString, instanceof og overloading");
        l.setCodeExample("""
            public class Bil {
                private String merke;
                private int år;

                public Bil(String merke, int år) {
                    this.merke = merke;
                    this.år = år;
                }

                @Override
                public String toString() {
                    return merke + " (" + år + ")";
                }

                // Overloading – to versjoner av same metode
                public double pris(int km) {
                    return km * 2.5;
                }

                public double pris(int km, boolean helg) {
                    return km * (helg ? 3.5 : 2.5);
                }
            }

            // i main:
            Bil b = new Bil("Tesla", 2023);
            System.out.println(b);              // Tesla (2023)

            Object obj = new Bil("Volvo", 2020);
            if (obj instanceof Bil bil) {
                System.out.println(bil.pris(100));        // 250.0
                System.out.println(bil.pris(100, true));  // 350.0
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva skjer hvis du IKKE overstyrer toString() og skriver System.out.println(objekt)?",
            List.of(
                "Ingenting skrives ut",
                "Kompilatoren gir feil",
                "En uleselig tekst som Bil@3f99bd52 skrives ut",
                "Feltene skrives automatisk ut"
            ),
            2, "Standard toString() viser klassenavn + hashkode i hex, f.eks. Bil@3f99bd52.",
            "Riktig! Uten override vises en uleselig hashkode.", "Standard toString() returnerer klassenavn@hashkode.");

        Exercise e2 = CourseData.mc(r,
            "Hva er metode-overloading?",
            List.of(
                "Å ha to klasser med samme navn",
                "Å overstyre en arvet metode",
                "Å ha flere metoder med samme navn men ulike parametere i samme klasse",
                "Å arve metoder fra to klasser"
            ),
            2, "Overloading = samme metodenavn, men kompilatoren velger riktig versjon basert på argumenttyper/-antall.",
            "Riktig! Overloading = samme navn, ulike parametere.", "Overloading: to metoder med samme navn men ulike parametere i én klasse.");

        Exercise e3 = CourseData.code(r,
            "Legg til en toString()-metode i klassen 'Person' som returnerer navn og alder, f.eks. \"Ola (25 år)\".",
            "@Override public String toString() { return navn + \" (\" + alder + \" år)\"; }",
            "public class Person", List.of("@Override", "toString()", "return"),
            "public class Person {\n    private String navn;\n    private int alder;\n    public Person(String navn, int alder) { this.navn=navn; this.alder=alder; }\n    @Override\n    public String toString() {\n        return navn + \" (\" + alder + \" år)\";\n    }\n}",
            "Perfekt! toString() gjør objektene dine lesbare! 📝",
            "Husk: @Override over metoden og return med den ønskede teksten.");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── MODULE 8: Static og Final ────────────────────────────────────────────

    static CourseModule createModule8(CourseData.ExIdRef r) {
        CourseModule m = new CourseModule();
        m.setId(8); m.setTitle("Static og Final");
        m.setDescription("Statiske metoder, variabler, konstanter og Utility-klasser.");
        m.setIcon("⚡"); m.setColor("#f59e0b"); m.setAvailable(true);
        m.setLessons(List.of(lesson8_1(r), lesson8_2(r)));
        return m;
    }

    static Lesson lesson8_1(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(19); l.setModuleId(8); l.setTitle("static – tilhører klassen");
        l.setGuideContent("""
            <h2>static – delt mellom alle objekter</h2>
            <p>Vanlige felter og metoder tilhører <em>instansen</em> (et spesifikt objekt). <code>static</code> felter og metoder tilhører <em>klassen</em> – delt av alle instanser.</p>
            <h3>static variabel</h3>
            <div class="note">
                <code>public class Teller {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>static int antall = 0;</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>public Teller() { antall++; }</code><br>
                <code>}</code><br><br>
                Hver gang du lager en ny Teller økes antall. Alle instanser deler den samme variabelen.
            </div>
            <h3>static metode</h3>
            <div class="note">
                Kalles på <strong>klassen</strong>, ikke et objekt:<br>
                <code>Math.sqrt(16)</code> – du trenger ikke <code>new Math()</code><br>
                <code>Integer.parseInt("42")</code> – konverterer String til int
            </div>
            <div class="tip">
                <strong>⚠️ Begrensning</strong><br>
                En static metode kan <strong>ikke</strong> bruke vanlige (ikke-statiske) felter eller kalle ikke-statiske metoder direkte. Den kjenner ikke til noe konkret objekt.
            </div>
            """);
        l.setCodeExampleTitle("static i praksis");
        l.setCodeExample("""
            public class Teller {
                private static int antall = 0;  // delt av alle
                private String navn;

                public Teller(String navn) {
                    this.navn = navn;
                    antall++;
                }

                public static int getAntall() {
                    return antall;
                }

                // Static hjelpemetode
                public static int kvadrat(int n) {
                    return n * n;
                }
            }

            // I main:
            System.out.println(Teller.getAntall()); // 0
            Teller t1 = new Teller("A");
            Teller t2 = new Teller("B");
            System.out.println(Teller.getAntall()); // 2

            System.out.println(Teller.kvadrat(5));  // 25
            System.out.println(Math.sqrt(16));       // 4.0
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva er forskjellen mellom en static og en vanlig metode?",
            List.of(
                "static metoder er raskere",
                "En static metode tilhører klassen, ikke et objekt, og kan kalles uten new",
                "static metoder kan ikke returnere verdier",
                "Det er ingen forskjell"
            ),
            1, "static tilhører klassen. Kall: KlasseNavn.metodenavn() uten å lage objekt.",
            "Riktig! static-metoder kalles på klassen, ikke på et objekt.",
            "static = tilhører klassen. Kall direkte: Klasse.metode().");

        Exercise e2 = CourseData.mc(r,
            "Hva skriver dette ut?\nSystem.out.println(Math.max(8, 15));",
            List.of("8", "15", "23", "Kompileringsfeil"),
            1, "Math.max() er en static metode som returnerer det største av to tall.",
            "Riktig! Math.max(8, 15) = 15.", "Math.max returnerer det største tallet: 15.");

        Exercise e3 = CourseData.code(r,
            "Lag en static metode kalt 'erPartall' som tar en int og returnerer true hvis tallet er partall.",
            "public static boolean erPartall(int n) { return n % 2 == 0; }",
            "public class", List.of("static boolean erPartall", "return", "% 2"),
            "public class TallSjekk {\n    public static boolean erPartall(int n) {\n        return n % 2 == 0;\n    }\n    public static void main(String[] args) {\n        System.out.println(erPartall(4));  // true\n        System.out.println(erPartall(7));  // false\n    }\n}",
            "Flott! Du kan nå lage static metoder! ⚡",
            "Husk: public static boolean erPartall(int n) { return n % 2 == 0; }");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson8_2(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(20); l.setModuleId(8); l.setTitle("final og Utility-klasser");
        l.setGuideContent("""
            <h2>final – uforanderlig</h2>
            <p><code>final</code> kan brukes på tre ting i Java:</p>
            <table class="data-table">
                <tr><th>Brukt på</th><th>Betyr</th><th>Eksempel</th></tr>
                <tr><td><strong>Variabel</strong></td><td>Kan ikke endres (konstant)</td><td><code>final int MAX = 100;</code></td></tr>
                <tr><td><strong>Metode</strong></td><td>Kan ikke overrides av subklasse</td><td><code>public final void beregn()</code></td></tr>
                <tr><td><strong>Klasse</strong></td><td>Kan ikke arves fra</td><td><code>public final class String</code></td></tr>
            </table>
            <div class="note">
                <strong>Konstanter:</strong> Bruk <code>static final</code> for klassekonstanter<br>
                <code>public static final double PI = 3.14159;</code><br>
                Navn skrives alltid med STORE_BOKSTAVER.
            </div>
            <h3>Utility-klasse</h3>
            <p>En Utility-klasse (hjelpeklasse) har <strong>bare static metoder</strong> og ingen konstruktør. Eksempler: <code>Math</code>, <code>Collections</code>, <code>Arrays</code>.</p>
            <div class="note">
                <code>public final class MathHjelper {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>private MathHjelper() {} // skjuler konstruktøren</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>public static int kvadrat(int n) { return n * n; }</code><br>
                <code>}</code>
            </div>
            """);
        l.setCodeExampleTitle("final og Utility-klasse");
        l.setCodeExample("""
            // Utility-klasse – ingen instansiering
            public final class Konverter {
                private Konverter() {}  // privat konstruktør

                public static final double KM_PER_MIL = 10.0;

                public static double milTilKm(double mil) {
                    return mil * KM_PER_MIL;
                }

                public static double kmTilMil(double km) {
                    return km / KM_PER_MIL;
                }

                public static double celsiusTilFahrenheit(double c) {
                    return c * 9.0 / 5.0 + 32;
                }
            }

            // I main:
            System.out.println(Konverter.milTilKm(5));        // 50.0
            System.out.println(Konverter.celsiusTilFahrenheit(100)); // 212.0
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva skjer hvis du prøver å endre en final variabel etter den er satt?",
            List.of(
                "Variabelen ignoreres",
                "Programmet krasjer ved kjøretid",
                "Kompilatoren gir feil – koden kompilerer ikke",
                "Variabelen får en tilfeldig verdi"
            ),
            2, "final = konstant. Prøver du å endre den, klager kompilatoren med en gang.",
            "Riktig! Kompilatoren stopper deg hvis du prøver å endre en final variabel.",
            "final er en kompileringsfeil – du oppdager det med en gang i IntelliJ.");

        Exercise e2 = CourseData.mc(r,
            "Hva er riktig navnekonvensjon for konstanter i Java?",
            List.of("camelCase", "PascalCase", "STORE_BOKSTAVER_MED_UNDERSTREK", "kebab-case"),
            2, "Konstanter (static final) skrives alltid med STORE_BOKSTAVER og understrek.",
            "Riktig! f.eks. MAX_HASTIGHET, MOMS_SATS.", "Konstanter: STORE_BOKSTAVER_MED_UNDERSTREK.");

        Exercise e3 = CourseData.code(r,
            "Lag en konstant MOMS = 0.25 (static final double) i en klasse og skriv den ut.",
            "public static final double MOMS = 0.25;",
            "public class", List.of("static final", "MOMS", "0.25", "System.out.println"),
            "public class Avgift {\n    public static final double MOMS = 0.25;\n    public static void main(String[] args) {\n        System.out.println(MOMS);\n    }\n}",
            "Korrekt! Du forstår nå final og konstanter! 🔒",
            "Husk: public static final double MOMS = 0.25; – store bokstaver!");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── MODULE 9: UML ───────────────────────────────────────────────────────

    static CourseModule createModule9(CourseData.ExIdRef r) {
        CourseModule m = new CourseModule();
        m.setId(9); m.setTitle("UML");
        m.setDescription("Klassediagrammer – visualiser kodestrukturen din.");
        m.setIcon("📐"); m.setColor("#10b981"); m.setAvailable(true);
        m.setLessons(List.of(lesson9_1(r)));
        return m;
    }

    static Lesson lesson9_1(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(21); l.setModuleId(9); l.setTitle("Klassediagrammer");
        l.setGuideContent("""
            <h2>UML-klassediagrammer</h2>
            <p>UML (Unified Modeling Language) er et visuelt språk for å beskrive kode. Et <strong>klassediagram</strong> viser klasser, felter, metoder og relasjoner mellom klasser.</p>
            <h3>En klasse i UML</h3>
            <div class="note">
                En klasse tegnes som en boks med tre seksjoner:<br>
                <code>┌─────────────┐</code><br>
                <code>│   Person    │</code> ← klassenavn<br>
                <code>├─────────────┤</code><br>
                <code>│ - navn: String │</code> ← felter<br>
                <code>│ - alder: int   │</code><br>
                <code>├─────────────┤</code><br>
                <code>│ + getNavn(): String │</code> ← metoder<br>
                <code>└─────────────┘</code>
            </div>
            <h3>Tilgangsnivå</h3>
            <table class="data-table">
                <tr><th>Symbol</th><th>Tilgang</th><th>Java</th></tr>
                <tr><td><code>+</code></td><td>Public</td><td>Alle kan se</td></tr>
                <tr><td><code>-</code></td><td>Private</td><td>Bare klassen selv</td></tr>
                <tr><td><code>#</code></td><td>Protected</td><td>Klassen og subklasser</td></tr>
            </table>
            <h3>Relasjoner</h3>
            <table class="data-table">
                <tr><th>Relasjon</th><th>Symbol</th><th>Eksempel</th></tr>
                <tr><td>Arv</td><td>Pil med hul trekant →△</td><td>Hund arver fra Dyr</td></tr>
                <tr><td>Assosiasjon</td><td>Vanlig pil →</td><td>Person har en Adresse</td></tr>
                <tr><td>Komposisjon</td><td>Fylt diamant ◆→</td><td>Hus «eier» Rom</td></tr>
            </table>
            <div class="tip">
                <strong>💡 Bruk IntelliJ til å generere UML</strong><br>
                Høyreklikk på klassen → Diagrams → Show Diagram. IntelliJ tegner klassediagrammet automatisk!
            </div>
            """);
        l.setCodeExampleTitle("Fra UML til Java-kode");
        l.setCodeExample("""
            // Dette UML-diagrammet:
            // ┌─────────────────────┐
            // │       Person        │
            // ├─────────────────────┤
            // │ - navn: String      │
            // │ - alder: int        │
            // ├─────────────────────┤
            // │ + getNavn(): String │
            // │ + getAlder(): int   │
            // │ + toString(): String│
            // └─────────────────────┘
            //          △
            //          │ (arv)
            // ┌─────────────────────┐
            // │       Student       │
            // ├─────────────────────┤
            // │ - studie: String    │
            // ├─────────────────────┤
            // │ + getStudie(): String│
            // └─────────────────────┘

            // ...gir følgende Java-kode:
            public class Person {
                private String navn;
                private int alder;

                public String getNavn() { return navn; }
                public int getAlder() { return alder; }

                @Override
                public String toString() {
                    return navn + " (" + alder + ")";
                }
            }

            public class Student extends Person {
                private String studie;
                public String getStudie() { return studie; }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva betyr symbolet '-' foran et felt i et UML-klassediagram?",
            List.of("public", "static", "private", "protected"),
            2, "- betyr private i UML. + betyr public, # betyr protected.",
            "Riktig! - = private i UML.", "I UML: - = private, + = public, # = protected.");

        Exercise e2 = CourseData.mc(r,
            "Hvilken relasjon i UML representerer arv (extends)?",
            List.of(
                "En vanlig pil (→)",
                "En stiplet pil (---→)",
                "En pil med hul trekant (→△)",
                "En fylt diamant (◆→)"
            ),
            2, "Arv vises med en pil med hul trekant som peker mot superklassen.",
            "Riktig! Arv = pil med hul trekant.", "Arv i UML = pil med hul trekant mot superklassen.");

        Exercise e3 = CourseData.mc(r,
            "Du skal tegne et UML-diagram for en klasse 'Bil' med private felt 'merke' (String) og public metode 'kjør()'. Hvilket av disse er riktig UML-notasjon for feltet?",
            List.of("+ merke: String", "- merke: String", "# merke: String", "merke: private String"),
            1, "Private felt skrives med - foran: - merke: String",
            "Riktig! - merke: String er riktig UML-notasjon.", "Private felt i UML: - feltNavn: Type");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── MODULE 10: Abstract og Interface ────────────────────────────────────

    static CourseModule createModule10(CourseData.ExIdRef r) {
        CourseModule m = new CourseModule();
        m.setId(10); m.setTitle("Abstract og Interface");
        m.setDescription("Abstrakte klasser og grensesnitt – kontrakter i Java.");
        m.setIcon("🔷"); m.setColor("#3b82f6"); m.setAvailable(true);
        m.setLessons(List.of(lesson10_1(r), lesson10_2(r)));
        return m;
    }

    static Lesson lesson10_1(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(22); l.setModuleId(10); l.setTitle("Abstract klasser og metoder");
        l.setGuideContent("""
            <h2>Abstract – uferdige klasser</h2>
            <p>En <code>abstract</code> klasse er en klasse du <strong>ikke kan instansiere direkte</strong>. Den er ment å arves fra. Den kan ha både vanlige og abstrakte metoder.</p>
            <h3>Abstract metode</h3>
            <p>En abstrakt metode har <strong>ingen kropp</strong> – bare signaturen. Subklassen <em>må</em> implementere den.</p>
            <div class="note">
                <code>public abstract class Form {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>public abstract double areal(); // ingen kropp!</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>public void beskriv() {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>System.out.println("Areal: " + areal());</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>}</code><br>
                <code>}</code>
            </div>
            <div class="tip">
                <strong>💡 Når brukes abstract?</strong><br>
                Bruk abstract når du vil lage en <em>felles base</em> for relaterte klasser, men der den abstrakte klassen alene ikke gir mening. F.eks. <code>Form</code> – du vet ikke om det er en sirkel eller firkant.
            </div>
            """);
        l.setCodeExampleTitle("Abstract klasse og subklasser");
        l.setCodeExample("""
            public abstract class Form {
                public abstract double areal();
                public abstract double omkrets();

                // Vanlig metode som alle former arver
                public void printInfo() {
                    System.out.println("Areal: " + areal());
                    System.out.println("Omkrets: " + omkrets());
                }
            }

            public class Sirkel extends Form {
                private double radius;

                public Sirkel(double radius) {
                    this.radius = radius;
                }

                @Override
                public double areal() {
                    return Math.PI * radius * radius;
                }

                @Override
                public double omkrets() {
                    return 2 * Math.PI * radius;
                }
            }

            public class Rektangel extends Form {
                private double bredde, høyde;

                public Rektangel(double bredde, double høyde) {
                    this.bredde = bredde;
                    this.høyde = høyde;
                }

                @Override
                public double areal() { return bredde * høyde; }

                @Override
                public double omkrets() { return 2 * (bredde + høyde); }
            }

            // I main:
            Form s = new Sirkel(5);
            s.printInfo();

            Form r = new Rektangel(4, 6);
            r.printInfo();
            """);

        Exercise e1 = CourseData.mc(r,
            "Kan du opprette et objekt av en abstrakt klasse med 'new'?",
            List.of(
                "Ja, alltid",
                "Ja, men bare hvis den har en konstruktør",
                "Nei, abstrakte klasser kan ikke instansieres",
                "Ja, men bare hvis alle metoder er implementert"
            ),
            2, "En abstrakt klasse er en ufullstendig \"mal\" – du kan ikke lage et objekt av den direkte.",
            "Riktig! new Form() gir kompileringsfeil.", "Abstrakte klasser kan IKKE instansieres direkte med new.");

        Exercise e2 = CourseData.mc(r,
            "Hva MÅ en subklasse gjøre med abstrakte metoder fra superklassen?",
            List.of(
                "Ingenting – de er valgfrie",
                "Implementere alle abstrakte metoder",
                "Slette dem",
                "Gjøre dem static"
            ),
            1, "En subklasse MUST implementere alle abstrakte metoder, ellers er subklassen selv abstrakt.",
            "Riktig! Alle abstrakte metoder MÅ implementeres.", "Subklassen må @Override og implementere alle abstract metoder.");

        Exercise e3 = CourseData.code(r,
            "Lag en klasse 'Trekant' som arver fra 'Form' og implementerer areal() (returner 0.5 * base * høyde).",
            "public class Trekant extends Form { @Override public double areal() { return 0.5 * base * høyde; } }",
            "class Trekant extends Form", List.of("extends Form", "@Override", "areal()", "return"),
            "public class Trekant extends Form {\n    private double base, høyde;\n    public Trekant(double base, double høyde) {\n        this.base = base; this.høyde = høyde;\n    }\n    @Override\n    public double areal() { return 0.5 * base * høyde; }\n    @Override\n    public double omkrets() { return base + høyde + Math.sqrt(base*base + høyde*høyde); }\n}",
            "Bra! Du kan nå implementere abstrakte klasser! 🔷",
            "Husk: extends Form, @Override på begge abstrakte metoder.");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson10_2(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(23); l.setModuleId(10); l.setTitle("Interface");
        l.setGuideContent("""
            <h2>Interface – en kontrakt</h2>
            <p>Et <code>interface</code> definerer hva en klasse <em>skal kunne gjøre</em>, uten å si <em>hvordan</em>. Det er som en kontrakt: klassen lover å implementere alle metodene.</p>
            <div class="note">
                <strong>Definer et interface:</strong><br>
                <code>public interface Kjørbar {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>void start();</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>void stopp();</code><br>
                <code>}</code><br><br>
                <strong>Implementer det:</strong><br>
                <code>public class Bil implements Kjørbar {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>@Override public void start() { ... }</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>@Override public void stopp() { ... }</code><br>
                <code>}</code>
            </div>
            <h3>Abstract klasse vs Interface</h3>
            <table class="data-table">
                <tr><th></th><th>Abstract klasse</th><th>Interface</th></tr>
                <tr><td>Kan ha felter</td><td>✅</td><td>Bare konstanter</td></tr>
                <tr><td>Kan ha konstruktør</td><td>✅</td><td>❌</td></tr>
                <tr><td>En klasse kan arve/implementere</td><td>Én</td><td>Mange</td></tr>
                <tr><td>Nøkkelord</td><td>extends</td><td>implements</td></tr>
            </table>
            <div class="tip">
                <strong>💡 En klasse kan implementere flere interfaces!</strong><br>
                <code>class Robot implements Kjørbar, Ladbar, Programmerbar { ... }</code>
            </div>
            """);
        l.setCodeExampleTitle("Interface eksempel");
        l.setCodeExample("""
            public interface Ladbar {
                void lad();
                int getBatteriProsent();
            }

            public interface Kjørbar {
                void start();
                void stopp();
            }

            // Klassen implementerer to interfaces
            public class ElBil implements Kjørbar, Ladbar {
                private int batteri = 100;
                private boolean kjører = false;

                @Override
                public void start() {
                    kjører = true;
                    System.out.println("Elbil starter stille...");
                }

                @Override
                public void stopp() {
                    kjører = false;
                    System.out.println("Elbil stopper.");
                }

                @Override
                public void lad() {
                    batteri = 100;
                    System.out.println("Lader til 100%");
                }

                @Override
                public int getBatteriProsent() {
                    return batteri;
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Kan en klasse implementere mer enn ett interface?",
            List.of(
                "Nei, bare ett interface per klasse",
                "Ja, ubegrenset antall interfaces",
                "Ja, men maks to",
                "Bare hvis de er i samme pakke"
            ),
            1, "En klasse kan implementere så mange interfaces den vil: class X implements A, B, C { }",
            "Riktig! En klasse kan implementere ubegrenset antall interfaces.",
            "En klasse kan implementere mange interfaces: class X implements A, B, C {}");

        Exercise e2 = CourseData.mc(r,
            "Hva er den viktigste forskjellen mellom abstract klasse og interface?",
            List.of(
                "Interface er raskere enn abstract klasse",
                "En klasse kan arve fra mange interfaces men bare én abstract klasse",
                "Abstract klassen kan ikke ha metoder",
                "Interface er bare for testing"
            ),
            1, "Java tillater arv fra én klasse (inkl. abstract), men implements av mange interfaces.",
            "Riktig! Interface: mange. Abstract klasse: én.", "implements = mange mulig. extends = bare én.");

        Exercise e3 = CourseData.code(r,
            "Lag et interface 'Printbar' med metoden print(). Lag en klasse 'Dokument' som implementerer Printbar.",
            "interface Printbar { void print(); }  class Dokument implements Printbar { @Override public void print() {...} }",
            "interface Printbar", List.of("interface Printbar", "void print", "implements Printbar", "@Override"),
            "public interface Printbar {\n    void print();\n}\n\npublic class Dokument implements Printbar {\n    @Override\n    public void print() {\n        System.out.println(\"Skriver ut dokument...\");\n    }\n}",
            "Utmerket! Du forstår nå interfaces! 📄",
            "Husk: interface med metode, og implements + @Override i klassen.");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── MODULE 11: Exceptions ───────────────────────────────────────────────

    static CourseModule createModule11(CourseData.ExIdRef r) {
        CourseModule m = new CourseModule();
        m.setId(11); m.setTitle("Exceptions");
        m.setDescription("Feilhåndtering med try-catch, throws og egne exceptions.");
        m.setIcon("⚠️"); m.setColor("#ef4444"); m.setAvailable(true);
        m.setLessons(List.of(lesson11_1(r), lesson11_2(r)));
        return m;
    }

    static Lesson lesson11_1(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(24); l.setModuleId(11); l.setTitle("try-catch – håndter feil");
        l.setGuideContent("""
            <h2>Exceptions – feil som kan håndteres</h2>
            <p>En <strong>exception</strong> er et objekt som representerer en feil som oppstod under kjøring. I stedet for å krasje kan du <em>fange</em> feilen med try-catch.</p>
            <div class="note">
                <code>try {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>// kode som kan feile</code><br>
                <code>} catch (ExceptionType e) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>System.out.println("Feil: " + e.getMessage());</code><br>
                <code>} finally {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>// kjøres alltid, uansett om det feiler</code><br>
                <code>}</code>
            </div>
            <h3>Vanlige exceptions</h3>
            <table class="data-table">
                <tr><th>Exception</th><th>Årsak</th></tr>
                <tr><td><code>NullPointerException</code></td><td>Bruker en variabel som er null</td></tr>
                <tr><td><code>ArrayIndexOutOfBoundsException</code></td><td>Ugyldig array-index</td></tr>
                <tr><td><code>NumberFormatException</code></td><td>Parser ikke-tall som tall</td></tr>
                <tr><td><code>ArithmeticException</code></td><td>Divisjon med null</td></tr>
            </table>
            <div class="tip">
                <strong>💡 e.getMessage()</strong><br>
                Gir en lesbar beskrivelse av hva som gikk galt. Svært nyttig for debugging!
            </div>
            """);
        l.setCodeExampleTitle("try-catch i praksis");
        l.setCodeExample("""
            public class FeilHåndtering {
                public static void main(String[] args) {
                    // NumberFormatException
                    try {
                        int tall = Integer.parseInt("ikke et tall");
                    } catch (NumberFormatException e) {
                        System.out.println("Feil: " + e.getMessage());
                    }

                    // ArithmeticException
                    try {
                        int resultat = 10 / 0;
                    } catch (ArithmeticException e) {
                        System.out.println("Kan ikke dele på null!");
                    } finally {
                        System.out.println("Ferdig med divisjon.");
                    }

                    // Fange flere feiltyper
                    String[] arr = {"a", "b"};
                    try {
                        System.out.println(arr[5]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Index finnes ikke!");
                    }
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva skjer hvis en exception kastes og det IKKE er noen try-catch?",
            List.of(
                "Programmet fortsetter som normalt",
                "Feilen ignoreres",
                "Programmet krasjer og skriver ut en stack trace",
                "Java prøver automatisk på nytt"
            ),
            2, "Uten try-catch propagerer exception opp og krasjer programmet med en stack trace.",
            "Riktig! Uten try-catch krasjer programmet.", "Uten try-catch: programmet stopper og stack trace vises.");

        Exercise e2 = CourseData.mc(r,
            "Hva gjør finally-blokken?",
            List.of(
                "Kjøres bare hvis det oppstår en feil",
                "Kjøres bare hvis det IKKE oppstår en feil",
                "Kjøres alltid, uansett om det feiler eller ikke",
                "Fanger alle feiltyper"
            ),
            2, "finally kjøres alltid – perfekt for opprydding som å lukke filer.",
            "Riktig! finally kjøres alltid.", "finally = kjøres alltid, uansett utfall.");

        Exercise e3 = CourseData.code(r,
            "Skriv et try-catch som prøver å parse \"hei\" til et tall med Integer.parseInt(), og fanger NumberFormatException.",
            "try { int n = Integer.parseInt(\"hei\"); } catch (NumberFormatException e) { System.out.println(e.getMessage()); }",
            "public class", List.of("try", "catch", "NumberFormatException", "Integer.parseInt"),
            "public class ParseTest {\n    public static void main(String[] args) {\n        try {\n            int n = Integer.parseInt(\"hei\");\n        } catch (NumberFormatException e) {\n            System.out.println(\"Ugyldig tall: \" + e.getMessage());\n        }\n    }\n}",
            "Bra! Du kan nå håndtere exceptions! ⚠️",
            "Husk: try { Integer.parseInt(...) } catch (NumberFormatException e) { ... }");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson11_2(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(25); l.setModuleId(11); l.setTitle("throws og egne exceptions");
        l.setGuideContent("""
            <h2>throws og egendefinerte exceptions</h2>
            <h3>throws – si at metoden kan kaste exception</h3>
            <div class="note">
                <code>public void åpneFil(String sti) throws IOException {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>// kode som kan kaste IOException</code><br>
                <code>}</code><br><br>
                Nå MÅ den som kaller åpneFil() håndtere IOException.
            </div>
            <h3>throw – kast en exception manuelt</h3>
            <div class="note">
                <code>public void setAlder(int alder) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>if (alder &lt; 0) throw new IllegalArgumentException("Alder kan ikke være negativ");</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>this.alder = alder;</code><br>
                <code>}</code>
            </div>
            <h3>Egne exception-klasser</h3>
            <p>Du kan lage din egen exception ved å arve fra <code>Exception</code>:</p>
            <div class="note">
                <code>public class UgyldigAlderException extends Exception {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>public UgyldigAlderException(String melding) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>super(melding);</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>}</code><br>
                <code>}</code>
            </div>
            """);
        l.setCodeExampleTitle("throws, throw og egne exceptions");
        l.setCodeExample("""
            // Egendefinert exception
            public class UgyldigAlderException extends Exception {
                public UgyldigAlderException(String melding) {
                    super(melding);
                }
            }

            public class Person {
                private int alder;

                public void setAlder(int alder) throws UgyldigAlderException {
                    if (alder < 0 || alder > 150) {
                        throw new UgyldigAlderException("Ugyldig alder: " + alder);
                    }
                    this.alder = alder;
                }
            }

            // I main:
            Person p = new Person();
            try {
                p.setAlder(-5);
            } catch (UgyldigAlderException e) {
                System.out.println("Feil: " + e.getMessage());
                // Feil: Ugyldig alder: -5
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva er forskjellen mellom 'throw' og 'throws'?",
            List.of(
                "Det er ingen forskjell",
                "'throw' kaster en exception, 'throws' erklærer at metoden kan kaste exception",
                "'throws' kaster en exception, 'throw' er i metodesignaturen",
                "Begge brukes bare i try-catch"
            ),
            1, "throw = kast en exception nå. throws = advar kaller om at dette kan skje.",
            "Riktig! throw kaster, throws erklærer.", "throw i kroppen: kast nå. throws i signaturen: kan skje.");

        Exercise e2 = CourseData.mc(r,
            "Fra hvilken klasse arver du når du lager en egen exception?",
            List.of("Error", "RuntimeException", "Exception", "Throwable"),
            2, "Egne checked exceptions arver fra Exception. Unchecked arver fra RuntimeException.",
            "Riktig! Arv fra Exception for egne exceptions.", "Vanligste: extends Exception (checked) eller extends RuntimeException (unchecked).");

        Exercise e3 = CourseData.code(r,
            "Lag en egendefinert exception kalt 'TomListeException' som arver fra Exception.",
            "public class TomListeException extends Exception { public TomListeException(String m) { super(m); } }",
            "class TomListeException extends Exception", List.of("extends Exception", "super("),
            "public class TomListeException extends Exception {\n    public TomListeException(String melding) {\n        super(melding);\n    }\n}",
            "Flott! Du kan nå lage egne exceptions! 🚨",
            "Husk: class TomListeException extends Exception { og super(melding); i konstruktøren.");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    // ─── MODULE 12: Fil-lesing/-skriving ─────────────────────────────────────

    static CourseModule createModule12(CourseData.ExIdRef r) {
        CourseModule m = new CourseModule();
        m.setId(12); m.setTitle("Fil-lesing og -skriving");
        m.setDescription("Skriv og les filer, CSV- og JSON-format.");
        m.setIcon("📁"); m.setColor("#14b8a6"); m.setAvailable(true);
        m.setLessons(List.of(lesson12_1(r), lesson12_2(r), lesson12_3(r)));
        return m;
    }

    static Lesson lesson12_1(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(26); l.setModuleId(12); l.setTitle("Skrive til fil");
        l.setGuideContent("""
            <h2>Skrive til fil med FileWriter</h2>
            <p>Java har innebygd støtte for fil-I/O. For å skrive til en fil bruker du <code>FileWriter</code> eller <code>PrintWriter</code>.</p>
            <div class="note">
                <strong>PrintWriter – enkel skriving:</strong><br>
                <code>try (PrintWriter pw = new PrintWriter("logg.txt")) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>pw.println("Linje 1");</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>pw.println("Linje 2");</code><br>
                <code>}</code><br><br>
                <code>try-with-resources</code> lukker filen automatisk!
            </div>
            <div class="tip">
                <strong>💡 Legg til, ikke erstatt</strong><br>
                <code>new FileWriter("logg.txt", true)</code> – den andre parameteren <code>true</code> betyr «legg til» i stedet for å overskrive.
            </div>
            <div class="note">
                <strong>Import du trenger:</strong><br>
                <code>import java.io.PrintWriter;</code><br>
                <code>import java.io.FileWriter;</code><br>
                <code>import java.io.IOException;</code>
            </div>
            """);
        l.setCodeExampleTitle("Skrive til fil");
        l.setCodeExample("""
            import java.io.PrintWriter;
            import java.io.FileWriter;
            import java.io.IOException;

            public class SkrivFil {
                public static void main(String[] args) {
                    // Skriv til fil (overskriver eksisterende)
                    try (PrintWriter pw = new PrintWriter("studenter.txt")) {
                        pw.println("Ola Nordmann");
                        pw.println("Kari Hansen");
                        pw.println("Per Berg");
                        System.out.println("Fil skrevet!");
                    } catch (IOException e) {
                        System.out.println("Feil: " + e.getMessage());
                    }

                    // Legg til i eksisterende fil
                    try (FileWriter fw = new FileWriter("studenter.txt", true);
                         PrintWriter pw = new PrintWriter(fw)) {
                        pw.println("Ny student");
                    } catch (IOException e) {
                        System.out.println("Feil: " + e.getMessage());
                    }
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hvilken klasse er enklest å bruke for å skrive tekstlinjer til fil?",
            List.of("Scanner", "BufferedReader", "PrintWriter", "FileInputStream"),
            2, "PrintWriter har println()-metoden, akkurat som System.out – veldig praktisk for tekst.",
            "Riktig! PrintWriter er enklest for tekstlinjer.", "PrintWriter gir deg println() for filskriving.");

        Exercise e2 = CourseData.mc(r,
            "Hva betyr 'true' som andre parameter i new FileWriter(\"fil.txt\", true)?",
            List.of(
                "Filen krypteres",
                "Filen opprettes bare hvis den ikke finnes",
                "Ny tekst legges til i stedet for å overskrive",
                "Skrivingen er raskere"
            ),
            2, "true = append-modus. Ny tekst legges til på slutten av filen.",
            "Riktig! true = append – legger til uten å overskrive.", "FileWriter(fil, true) = append/legg-til modus.");

        Exercise e3 = CourseData.code(r,
            "Skriv kode som oppretter en PrintWriter og skriver teksten \"Hei fra Java!\" til en fil kalt \"test.txt\".",
            "try (PrintWriter pw = new PrintWriter(\"test.txt\")) { pw.println(\"Hei fra Java!\"); }",
            "import java.io.PrintWriter", List.of("PrintWriter", "test.txt", "println", "IOException"),
            "import java.io.PrintWriter;\nimport java.io.IOException;\npublic class Skriv {\n    public static void main(String[] args) {\n        try (PrintWriter pw = new PrintWriter(\"test.txt\")) {\n            pw.println(\"Hei fra Java!\");\n        } catch (IOException e) {\n            System.out.println(e.getMessage());\n        }\n    }\n}",
            "Bra! Du kan nå skrive til filer! 📝",
            "Husk: import, try (PrintWriter pw = new PrintWriter(\"test.txt\")), pw.println(...)");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson12_2(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(27); l.setModuleId(12); l.setTitle("Lese fra fil");
        l.setGuideContent("""
            <h2>Lese fra fil med Scanner eller BufferedReader</h2>
            <p>For å lese tekst fra en fil kan du bruke <code>Scanner</code> (enkel) eller <code>BufferedReader</code> (raskere for store filer).</p>
            <div class="note">
                <strong>Med Scanner:</strong><br>
                <code>try (Scanner sc = new Scanner(new File("logg.txt"))) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>while (sc.hasNextLine()) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>String linje = sc.nextLine();</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>System.out.println(linje);</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>}</code><br>
                <code>}</code>
            </div>
            <div class="note">
                <strong>Med BufferedReader:</strong><br>
                <code>try (BufferedReader br = new BufferedReader(new FileReader("logg.txt"))) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>String linje;</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>while ((linje = br.readLine()) != null) {</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>System.out.println(linje);</code><br>
                &nbsp;&nbsp;&nbsp;&nbsp;<code>}</code><br>
                <code>}</code>
            </div>
            <div class="tip">
                <strong>💡 Nyttig Java-snarvei (Java 11+)</strong><br>
                <code>List&lt;String&gt; linjer = Files.readAllLines(Path.of("logg.txt"));</code><br>
                Leser hele filen inn i en liste på én linje!
            </div>
            """);
        l.setCodeExampleTitle("Lese filer");
        l.setCodeExample("""
            import java.io.*;
            import java.util.Scanner;

            public class LesFil {
                public static void main(String[] args) {
                    // Med Scanner
                    try (Scanner sc = new Scanner(new File("studenter.txt"))) {
                        int nr = 1;
                        while (sc.hasNextLine()) {
                            System.out.println(nr + ": " + sc.nextLine());
                            nr++;
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("Fant ikke filen: " + e.getMessage());
                    }

                    // Lese og behandle
                    try (BufferedReader br = new BufferedReader(new FileReader("studenter.txt"))) {
                        String linje;
                        while ((linje = br.readLine()) != null) {
                            if (linje.startsWith("O")) {
                                System.out.println("Starter med O: " + linje);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Feil: " + e.getMessage());
                    }
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva returnerer BufferedReader.readLine() når filen er ferdig lest?",
            List.of("En tom String \"\"", "0", "null", "En IOException"),
            2, "readLine() returnerer null når det ikke er flere linjer. Typisk brukt som: while ((linje = br.readLine()) != null)",
            "Riktig! null betyr slutt på filen.", "readLine() = null når filen er ferdig.");

        Exercise e2 = CourseData.mc(r,
            "Hvilken exception bør du fange når du åpner en fil som kanskje ikke finnes?",
            List.of("NullPointerException", "IOException", "FileNotFoundException", "IllegalArgumentException"),
            2, "FileNotFoundException er en subtype av IOException – kastes når filen ikke finnes.",
            "Riktig! FileNotFoundException.", "Fil ikke funnet = FileNotFoundException (subtype av IOException).");

        Exercise e3 = CourseData.code(r,
            "Skriv kode som bruker Scanner til å lese en fil kalt 'data.txt' og skriver ut alle linjene.",
            "try (Scanner sc = new Scanner(new File(\"data.txt\"))) { while (sc.hasNextLine()) { System.out.println(sc.nextLine()); } }",
            "import java.io", List.of("Scanner", "data.txt", "hasNextLine", "nextLine"),
            "import java.io.*;\nimport java.util.Scanner;\npublic class Les {\n    public static void main(String[] args) {\n        try (Scanner sc = new Scanner(new File(\"data.txt\"))) {\n            while (sc.hasNextLine()) {\n                System.out.println(sc.nextLine());\n            }\n        } catch (FileNotFoundException e) {\n            System.out.println(e.getMessage());\n        }\n    }\n}",
            "Korrekt! Du kan nå lese filer i Java! 📖",
            "Husk: Scanner sc = new Scanner(new File(\"data.txt\")), while (sc.hasNextLine())");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }

    static Lesson lesson12_3(CourseData.ExIdRef r) {
        Lesson l = new Lesson();
        l.setId(28); l.setModuleId(12); l.setTitle("CSV og JSON");
        l.setGuideContent("""
            <h2>CSV og JSON – strukturerte dataformater</h2>
            <h3>CSV – Comma Separated Values</h3>
            <p>CSV er en enkel tekstfil der hvert felt er skilt med komma:</p>
            <div class="note">
                <strong>Eksempel (studenter.csv):</strong><br>
                <code>navn,alder,studie</code><br>
                <code>Ola Nordmann,22,Informatikk</code><br>
                <code>Kari Hansen,21,Matte</code>
            </div>
            <div class="note">
                <strong>Les og parse CSV:</strong><br>
                <code>String linje = "Ola Nordmann,22,Informatikk";</code><br>
                <code>String[] deler = linje.split(",");</code><br>
                <code>String navn = deler[0];   // Ola Nordmann</code><br>
                <code>int alder = Integer.parseInt(deler[1]);  // 22</code>
            </div>
            <h3>JSON – JavaScript Object Notation</h3>
            <p>JSON er et mer avansert format brukt i web-API-er:</p>
            <div class="note">
                <code>{</code><br>
                &nbsp;&nbsp;<code>"navn": "Ola Nordmann",</code><br>
                &nbsp;&nbsp;<code>"alder": 22,</code><br>
                &nbsp;&nbsp;<code>"studie": "Informatikk"</code><br>
                <code>}</code><br><br>
                For å lese JSON i Java bruker du biblioteker som <strong>Jackson</strong> eller <strong>Gson</strong>.
            </div>
            <div class="tip">
                <strong>💡 CSV vs JSON</strong><br>
                CSV: enkelt, lite, bra for regnearklignende data.<br>
                JSON: fleksibelt, støtter nøstede strukturer, standard for web-API-er.
            </div>
            """);
        l.setCodeExampleTitle("Les og skriv CSV");
        l.setCodeExample("""
            import java.io.*;
            import java.util.*;

            public class CSVEksempel {
                public static void main(String[] args) throws IOException {
                    // Skriv CSV
                    try (PrintWriter pw = new PrintWriter("studenter.csv")) {
                        pw.println("navn,alder,studie");
                        pw.println("Ola Nordmann,22,Informatikk");
                        pw.println("Kari Hansen,21,Matematikk");
                    }

                    // Les og parse CSV
                    try (Scanner sc = new Scanner(new File("studenter.csv"))) {
                        sc.nextLine(); // hopp over header-linjen

                        while (sc.hasNextLine()) {
                            String linje = sc.nextLine();
                            String[] deler = linje.split(",");

                            String navn = deler[0];
                            int alder = Integer.parseInt(deler[1]);
                            String studie = deler[2];

                            System.out.println(navn + " er " + alder + " år og studerer " + studie);
                        }
                    }
                }
            }
            """);

        Exercise e1 = CourseData.mc(r,
            "Hva gjør koden: String[] deler = linje.split(\",\");",
            List.of(
                "Sletter kommaene fra linjen",
                "Deler linjen opp i et array der hvert element er teksten mellom kommaene",
                "Teller antall kommaer",
                "Legger til kommaer"
            ),
            1, "split(\",\") deler String-en ved hvert komma og returnerer et String-array.",
            "Riktig! split(\",\") deler String ved komma.", "split(\",\") = del String ved komma → String-array.");

        Exercise e2 = CourseData.mc(r,
            "Hva er JSON?",
            List.of(
                "Et programmeringsspråk",
                "Et tekstformat for å representere strukturert data, mye brukt i web-API",
                "Et Java-bibliotek for filer",
                "En type database"
            ),
            1, "JSON = JavaScript Object Notation. Standard tekstformat for datautveksling, spesielt i web-API.",
            "Riktig! JSON er et standard dataformat for web-API.", "JSON er et tekstformat for strukturert data.");

        Exercise e3 = CourseData.code(r,
            "Parse CSV-linjen \"Tesla,2023,500\" med split(\",\") og skriv ut merket (første felt).",
            "String[] deler = \"Tesla,2023,500\".split(\",\"); System.out.println(deler[0]);",
            "public class", List.of("split(\",\")", "deler[0]", "System.out.println"),
            "public class ParseCSV {\n    public static void main(String[] args) {\n        String linje = \"Tesla,2023,500\";\n        String[] deler = linje.split(\",\");\n        System.out.println(deler[0]);\n    }\n}",
            "Bra! Du kan nå parse CSV! 📊",
            "String[] deler = linje.split(\",\"); og deler[0] for første felt.");

        l.setExercises(List.of(e1, e2, e3));
        return l;
    }
}
