# 1. Coding Convention and Guidelines
Lo sviluppo dell'applicazione Android utilizza due linguaggi principali: Java, per la logica dell'applicazione, e XML, per la definizione dei layout e della configurazione; in entrambi i casi, è fondamentale rispettare le relative code conventions.
Di seguito sono riportate informazioni su linee guida per Java e sui tag XML che gli sviluppatori dovranno seguire per la stesura del codice.

## 1.1 Java Guidelines

### 1.1.1 Naming Convention
È buona norma utilizzare nomi:
- Descrittivi;
- Di lunghezza medio-corta;
- Utilizzando solo caratteri consentiti (a-z, A-Z, 0-9).

### 1.1.2 Variabili
I nomi delle variabili devono iniziare con la lettera minuscola e rispettare la notazione camelCase. La dichiarazione delle variabili deve essere effettuata ad inizio blocco; in ogni riga vi possono essere una o più dichiarazioni di variabile e va effettuato l’allineamento per migliorare la leggibilità; 
In determinati casi, è possibile utilizzare il carattere underscore “_”, ad esempio quando si fa uso di variabili costanti oppure quando si fa uso di proprietà statiche. 
Per le variabili costanti bisogna utilizzare solamente lettere maiuscole.

### 1.1.3 Metodi
I nomi dei metodi devono seguire una convenzione di scrittura: iniziare con la lettera minuscola e rispettare la notazione camelCase. Di solito il nome del metodo è costituito da un verbo che identifica un’azione, seguito dal nome di un oggetto. Inoltre, i metodi che servono per accedere o modificare le variabili di una classe dovrebbero essere denominati utilizzando il prefisso "get" o "set", seguito dal nome della variabile in questione.
Nel caso in cui venga dichiarata una variabile all’interno di un metodo quest’ultima deve essere dichiarata appena prima del suo utilizzo e deve servire per un solo scopo, per facilitare la leggibilità. 
È importante che ogni metodo sia preceduto da un commento che ne spieghi lo scopo, i parametri che accetta e il valore di ritorno. Inoltre, i metodi dovrebbero essere organizzati in base alla loro funzionalità all'interno della classe.

### 1.1.4 Classi e Pagine
I nomi delle classi e delle pagine devono iniziare con la lettera maiuscola e devono rispettare la notazione CamelCase. Ogni file sorgente .java contiene una singola classe e dev’essere strutturata nel seguente modo:
- Dichiarazione delle librerie utilizzate;
- Dichiarazione della classe pubblica; 
- Dichiarazioni di costanti; 
- Dichiarazioni di variabili di classe; 
- Dichiarazioni di variabili d’istanza; 
- Costruttore/i; 
- Commento e dichiarazione metodi e variabili.

## 1.2 XML Guidelines

### 1.2.1 Uso dei tag corretti
Utilizzare tag standard e specifici di Android, non usare tag non validi o personalizzati senza una giustificazione chiara.

### 1.2.2 Indentazione e Formattazione
Usare un'indentazione consistente (di solito 2 o 4 spazi per ogni livello di annidamento).
Ogni nuovo elemento XML deve essere scritto su una nuova riga per migliorare la leggibilità.

#### 1.2.3 Attributi obbligatori
Alcuni attributi sono obbligatori, come android:layout_width e android:layout_height per i layout. Assicurarsi di includerli in ogni elemento di layout.

### 1.2.4 Nomi significativi per gli ID
Assegnare nomi descrittivi agli ID degli elementi, utilizzando la notazione camelCase.

### 1.2.5 Chiusura dei tag
Assicurarsi che tutti i tag siano correttamente chiusi.

### 1.2.6 Commenti XML
I commenti in XML devono essere inseriti con il formato <!-- commento -->.
Non usare commenti in modo eccessivo; usarli solo per chiarire porzioni complesse di layout.

## 1.3 Commit Guidelines
Di seguito sono riportate alcune buone pratiche generali da adottare quando si scrive un commit:

### 1.3.1 Struttura
Il messaggio di commit deve seguire questa struttura:
```
<tipo>: [titolo]
[descrizione]
```

### 1.3.2 Tipi principali
**fix**: Corregge un bug.

**feat**: Introduce una nuova funzionalità.

### 1.3.3  Regole aggiuntive
La descrizione del commit offre dettagli aggiuntivi delle modifiche apportate.
Ogni commit dovrebbe avere una singola responsabilità per un singolo cambiamento (atomicità).
Limitare la descrizione a 72 caratteri.
Usare il tempo verbale impersonale nel soggetto.

## 1.4 Widget Guidelines

### 1.4.1 ID Widget
Gli ID dei widget UI (come TextView, Button, ImageView, ecc.) dovrebbero seguire uno schema chiaro, di solito descrittivo, che indichi la funzione o la posizione del widget.
Usa prefissi come:
- **btn**: per i pulsanti (Button);
- **tv**: per le etichette di testo (TextView);
- **img**: per le immagini (ImageView);
- **et**: per i campi di testo (EditText);
- **lv**: per le liste (ListView o RecyclerView);
- **chk**: per i checkbox (CheckBox);
- **sw**: per gli switch (Switch).

Utilizzare la sintassi: *tipoNome*

Esempi: **btnLogin**, **tvInfo**, **etPassword**.

### 1.4.2 Risorse di Layout
Le risorse di layout (file XML che definiscono la struttura della UI) sono generalmente denominate in modo descrittivo rispetto alla loro funzione o alla schermata che rappresentano.
Utilizzare la sintassi: _activity_nome_, _fragment_nome_, _view_nome_.
Esempi: **activity_main.xml**, **fragment_settings.xml**.

### 1.4.3 Stringhe
Le risorse di stringhe, che vengono usate per il testo nell'interfaccia utente, devono seguire una convenzione chiara per essere facilmente identificate.

Utilizzare la sintassi: _descrizioneElementoAzione_

Esempi:

```
<string name="btnLogin">Login</string>,
<string name="txtUsernamePlaceholder">Enter your username</string>
```

### 1.4.4 Stili e Temi
Gli stili e i temi sono usati per definire l'aspetto visivo dei componenti UI, come colori, dimensioni, margini, e altre proprietà estetiche. La convenzione di denominazione per questi dovrebbe seguire una sintassi che descriva chiaramente l'elemento visivo e il suo scopo.
Usa prefissi come:
- **Button**: per gli stili che riguardano i pulsanti;
- **TextView**: per gli stili legati alla tipografia;
- **EditText**: per stili che si applicano ai campi di testo.

Utilizzare la sintassi: _TipoElementoAzione_

Esempi: **ButtonPrimaryStyle**, **EditTextHintStyle**

### 1.4.5 Variabili nel codice
Le variabili nel codice seguono le convenzioni Java citate sopra (utilizzando quindi la notazione camelCase), e usando i prefissi citati nella sezione 1.1.4.1.

Esempi: **btnLogin**, **tvInfo**, **etPassword**.

## 1.5 DataBase Guidelines
Sono riportate di seguito le convenzioni usate nell’impostazione del DB Firestore.

### 1.5.1 Nomi delle Raccolte
Tutti i nomi delle raccolte iniziano con la **lettera maiuscola**.

### 1.5.2 Nomi dei Campi
Tutti i nomi dei campi all’interno delle raccolte sono scritti **interamente in minuscolo**, senza alcun tipo di variazioni come spazi o caratteri speciali.

## 1.6 Checkstyle Guidelines
I suggerimenti e le conseguenti modifiche apportate al codice seguendo il CheckStyle, nel dettaglio Google Check, sono:
 - Riordinamento degli import;
 - Miglioramento di indentazione;
 - Corretto naming delle variabili;
 - Rientro nello spazio pagina;
 - Rimozione spazi inutili.


# 2. Struttura delle directory
Il sistema è stato organizzato seguendo l’architettura Fat Client, che centralizza la logica applicativa e la gestione dei dati sul client, garantendo maggiore efficienza operativa e riducendo la dipendenza dal server. La struttura delle directory, conforme agli standard Android e Gradle, include la gestione dell’interfaccia utente e delle interazioni nel livello **Presentation**, la logica applicativa e le regole di business nel livello **Application Logic**, e l’accesso ai dati locali con sincronizzazione remota nel livello **Data Management**.

## 2.1 Vista del progetto Concettuale (Android View)

### 2.1.1 Manifests
Contiene il file **AndroidManifest.xml**, che definisce:
- La configurazione generale dell’applicazione;
- La dichiarazione di **Activity**, **Service**, e permessi necessari.

### 2.1.2 Java
Racchiude il codice sorgente che a partire dal package principale sarà suddiviso in ulteriori package per i vari sottosistemi:
- **Gestione Profilo Utente;**
- **Gestione Eventi e Sensibilizzazione;**
- **Gestione Smaltimento Rifiuti.**

Inoltre sono presenti altri due package relativi a classi di utilità e necessarie all’avvio del sistema:
- **Start;**
- **Utils.**

### 2.1.3 Res
Include tutte le risorse non relative al codice sorgente:
- **layout/**: File XML che definiscono la struttura delle schermate;
- **drawable/**: Immagini e risorse grafiche;
- **values/**: File XML contenenti stringhe, colori, stili, e altre risorse condivise;
- **raw/**: Risorse statiche come audio o video.

### 2.1.4 Gradle Scripts
Raccoglie tutti i file di configurazione e build, come build.gradle e settings.gradle.

## 2.2 Vista del progetto Fisica (Project View)
- **module-name/**: Cartella principale del modulo, contenente tutte le altre directory e file di configurazione;
- **build/**:  Generata automaticamente, raccoglie gli output della build;
- **libs/**:  Opzionale, può contenere librerie esterne aggiunte manualmente;
- **src/**: Questa directory contiene tutte le risorse principali del progetto. Nella sottocartella java si trovano il codice sorgente e i file di test, mentre nella sottocartella res sono organizzati i layout e i contenuti multimediali.

# 3. JavaDoc
Javadoc è uno strumento di Java che consente di generare automaticamente la documentazione per il codice, utilizzando dei commenti speciali, scritti direttamente nel codice. Inoltre crea pagine web che spiegano classi, metodi e variabili, rendendo più facile capire come funziona il programma. 

## 3.1 Consultare la JavaDoc
Oltre ai commenti relativi alle classi e al codice, nella Javadoc saranno presenti ulteriori indicazioni sulla suddivisione dei package e l’indicazione su come si potrebbero utilizzare due Design Pattern per l’implementazione del nostro sistema.

La JavaDoc generata è consultabile attraverso il seguente [link](https://tensa53.github.io/WasteGone/).

## 3.2 Generazione della JavaDoc
Per generare la Javadoc attraverso l’ambiente di sviluppo Android Studio, bisogna definire una nuova configurazione di build del progetto, che usa alcune dipendenze extra e comandi aggiuntivi, con path assoluti della macchina dove viene eseguita la generazione. Non è quindi necessario effettuare il commit and push di questa configurazione. Nonostante la procedura descritta nei passi seguenti restituisca vari errori e warning, la generazione va comunque a buon fine.

1. Tramite il menù hamburger in alto a sinistra, selezionare la voce **“Project Structure”** dalla sezione **“Files”**;
2. Recuperare il path del SDK nella sezione **“SDK Location”** e la versione del SDK usata dal progetto, alla voce **“Compile SDK Version”** della sezione **“Modules”**;
3. Posizionarsi sul file **build.gradle.kts** e aggiungere come dipendenza il path di SDK aumentato fino al .jar con il seguente comando:
    - **_implementation(files(“pathtoSdk”))_**
    - **_pathtoSdk_** d’esempio: **_/home/marco/Android/Sdk/platforms/android-34/android.jar_**
5. Effettuare il sync del nuovo file gradle tramite la voce **“Sync Now”** comparsa in alto a destra;
6. Sempre tramite l’hamburger menu, selezionare la voce **“Generate JavaDoc”** dalla sezione **“Tools”**;
7. Impostare i seguenti valori:
   - Custom Scope: **Project Source Files**;
   - Spuntare la casella **Include test sources**;
   - Spuntare la casella **Include JDK and library sources in -sourcepath**;
   - Scegliere una directory di output;
   - Visibility level: **private**;
8. In Command line arguments inserire il comando: 
   - **_-sourcepath <il path inserito al punto 3>_**, ad esempio: 
   - **_-sourcepath /home/marco/Android/Sdk/platforms/android-34/android.jar_**
9. Avviare la generazione della javadoc, cliccando sul pulsante “Generate”;
10. Per consentire nuovamente la build per l’esecuzione del progetto, commentare la dipendenza inserita al punto **3** e ripetere il punto **4**.
