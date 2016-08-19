# SMLite

[TOC]

## Descrizione generale

Piccola app di utilità per la gestione delle spese al supermercato.
Dovrà essere possibile:

- Eseguire un login nell'applicazione;
- Censire le proprie categorie personalizzate, in aggiunta a quelle di default;
- Censire degli articoli rapidi, con categoria e descrizione predefiniti;
- Creare una spesa, eventualmente condivisibile, con possibilità di specificare
  data e negozio;
- Inserire articoli in una spesa, come traccia o pienamente detagliati
- Dettagliare gli articoli di spesa con marca, prezzo unitario/a quantità,  
  numero pezzi/quantità e importo totale (calcolato se forniti i dati precedenti
  o da specificare a mano);
- Reportistica varia ed eventuale.

## Navigazione

### Applicazione WEB

L'utente, dopo il login nel sistema, avrà accesso alla sua home page, da cui
sarà possibile raggiungere le varie sezioni dell'applicazione. Tramite un menu
laterale sarà possibile:

- Eseguire il logout;
- Accedere alla propria sezione `User Admin`;
- Accedere alla propria lista delle spese, tramite voce `Lista Spese`;
- Accedere alla sezione relativa alla reportistica, tramite voce `Report`.

Dalla home page dovrà essere possibile inoltre creare una nuova bozza di spesa,
eventualmente vincolata ad uno specifico negozio.

Le pagine condivideranno il menu laterale, che dovrà riportare le voci:

- `admin`;
- `report`;
- `spese`;
- `logoff`.


#### Home page

La home page dell'applicazione presenterà due pannelli a scomparsa in cui
verranno presentati:

- la lista delle bozze di spesa aperte, con un tasto apposito a fianco al titolo
  per la creazione di una nuova bozza; ogni elemento della lista bozze dovrà
  avere dei tasti di rimozione, di completamento bozza e di creazione spesa
  basata sulla bozza;

- La lista delle ultime spese (con filtri impostabili dall'utente), riportanti
  le informazioni di testata delle stesse. Ogni spesa dovrà avere dei tasti di
  rimozione e di apertura.

Di seguito una mini tabellina con tutte le chiamate rest possibili da questa
pagina:

<table>
    <tr>
        <th>Metodo</th><th>URL</th><th>Descrizione</th>
    </tr>
    <tr>
        <td>GET</td><td>/web/my</td><td>Vai a user admin (menu)</td>
    </tr>
    <tr>
        <td>GET</td><td>/web/report</td><td>Vai a pag. report (menu)</td>
    </tr>
    <tr>
        <td>POST</td><td>/web/logoff</td><td>Esegui logoff (menu)</td>
    </tr>
    <!-- OPERAZIONI SU BOZZE -->
    <tr>
        <td>POST</td><td>/web/drafts/</td><td>Crea bozza spesa (tasto)</td>
    </tr>
    <tr>
        <td>PUT</td><td>/web/drafts/:id?archive</td><td>Archivia bozza (tasto)</td>
    </tr>
    <tr>
        <td>DELETE</td><td>/web/drafts/:id</td><td>Rimuovi bozza spesa (tasto)</td>
    </tr>
    <tr>
        <td>GET</td><td>/web/drafts/:id</td><td>Apri bozza spesa (click su bozza)</td>
    </tr>    
    <!-- OPERAZIONI SU SPESE -->
    <tr>
        <td>POST</td><td>/web/expenses/</td><td>Crea spesa (tasto)</td>
    </tr>
    <!--tr>
        <td>PUT</td><td>/web/expenses/:id?archive</td><td>Archivia bozza (tasto)</td>
    </tr-->
    <tr>
        <td>DELETE</td><td>/web/expenses/:id</td><td>Rimuovi spesa (tasto)</td>
    </tr>
    <tr>
        <td>GET</td><td>/web/expenses/:id</td><td>Apri spesa (click su spesa)</td>
    </tr>    
</table>


#### Gestione bozze di spesa

TBD

#### Logout

TBD

#### Sezione `UserAdmin`

TBD

#### Sezione `Lista Spese`

TBD

#### Sezione `Report`

TBD

### Navigazione app android

Anche per android sarà prevista una prima fase di login, da cui poi si
raggiungerà, in caso di login ok, la home page. Sarà presente un menu laterale
a scomparsa, richiamabile dal tasto in alto a sinistra, che presenterà le
medesime scelte dell'applicazione web.
La home page presenterà le varie bozze di spesa aperte, metterà a disposizione
le funzionalità per la nuova creazione.

## Dettaglio funzionalità e logiche di base

### Gestione oggetti "fondamentali"

#### Oggetto `CategoryBean`

Rappresenta una categoria. Ad ogni categoria l'utente può associare uno
specifico colore, in modo da rendere facilmente riconoscibili i vari oggetti
appartenenti a quella categoria.

#### Oggetto `ArticleBean`

Rappresenta un articolo di acquisto. Esso avrà associata una ed una sola
categoria; per permettere diversi gradi di dettaglio, dovrà essere previsto un
campo che identifichi la marca o il brand dell'articolo; naturalmente,
l'articolo può anche rimanere il più generico possibile ma è buona norma evitare
che un articolo faccia le veci di una categoria.

#### Oggetto `DraftExpenseBean`

Rappresenta una bozza di spesa, con le sole informazioni degli articoli da
acquistare. Le bozze possono poi essere rimosse o prese in carico, generando
una spesa vera e propria identificata dall'oggetto `ExpenseBean`. In questo caso
la bozza viene considerata completata, e viene archiviata (o rimossa).

Questo oggetto dovrà avere i seguenti campi:

- Data creazione bozza (a mano, altrimenti valore di default);
- Descrizione bozza (a mano, eventualmente nullo);
- Flag completamento (falso in creazione, a true quando viene generata una
  spesa figlia);
- Lista articoli impattati;
- Lista categorie impattate, eventualmente vuote.  

#### Oggetto `ExpenseBean`

Rappresenta una spesa, che avrà pertanto associati una serie di informazioni di
testata (data, descrizione, negozio di spesa) ed una lista di articoli. Ogni
singolo elemento della lista dovrà riportare almeno l'articolo acquistato, più
una serie di campi opzionali del tipo:

- importo totale oppure quantità e importo singolo;
- negozio di acquisto dell'articolo, se non specificato nella testata;
- flag di presenza offerta sull'articolo, se negozio specificato nella
  spesa o nell'articolo;

Oggetti di questo genere potranno essere creati da zero oppure a partire da una
bozza di spesa: in questo caso la lista sarà prepopolata con gli articoli e le
categorie di spesa inseriti nella bozza.

#### Oggetto `ExpenseArticleBean`
Tabella di relazione tra gli articoli e la spesa. Contiene informazioni su:

- articolo coinvolto;
- spesa coinvolta;
- quantità e importo unitario;
- costo totale;
- flag di offerta attiva.

#### Oggetto `ShopBean`
Contiene i dettagli di un negozio di spesa. In prima battuta, non utilizzato.

#### Oggetto `BrandBean`
Contiene i dettagli di una marca di articoli. In prima battuta, non utilizzato.

## Utilità varie

Applicazione suddivisa in due moduli:

- `smlite-web`, contenente i servizi REST e la componente web dell'applicazione;
  la navigazione è basata sul framework Spring MVC, mentre la parte REST è
  affidata a Jersey;
- `smlite-engine`, contenente la logica di base dell'applicazione, i bean
  fondamentali e l'interfaccia allo strato di persistenza.

### Modulo web

La parte Spring del modulo web è articolata in:

- un file con i bean di spring, `spring-mvc-dispatcher-servlet.xml`, contenente
  tutte le definizioni dei bean che dovranno essere utilizzati;
- una serie di pagine `jsp` sotto la directory `WEB-INF/pages/`, che fungono da
  `view` per l'applicazione;
- una serie di classi java marcate con l'annotation `@Controller`, che fungono
  appunto da `controller` relativamente al pattern MVC.

Grosso modo, ogni path dell'interfaccia grafica corrisponde ad un path dei
servizi REST di Jersey, in modo da rendere comune a entrambe le modalità le
varie operazioni da eseguire.

I path e le view messi a disposizione dai controller sono i seguenti:

- `login`, su cui sono stati definiti i soli metodi `GET` e `POST`.
  Rispettivamente:
    - il primo passerà il controllo alla view relativa, con nome omonimo (e
      nome file `login.jsp`);
    - il secondo eseguirà il login vero e proprio all'applicazione, chiamando
      un metodo opportuno di ricerca utente e verifica password definito nel
      modulo `smlite-engine`. In base all'esito, si verrà rediretti verso le
      viste `error` o `home`;

- `error`, su cui è stato definito il singolo metodo `GET`. Con questa chiamata
  verranno gestiti tutti gli errori a runtime dell'applicazione web;

- `logout`, su cui verrà definito il solo metodo `POST` che permetterà il logoff
  dall'applicazione; tale operazione redirigerà poi verso la pagina di login,
  tramite `return` verso la vista relativa;

- `home`: pagina principale dell'applicazione, cui si accede dopo un login
  eseguito con successo. Su tale path è definito il metodo `GET`, che procederà
  a popolare la pagina con i dati dell'utente. In particolare, saranno da
  recuperare:
    - le bozze di spesa;
    - le ultime spese eseguite;
  Tali dati dovranno poi essere iniettati nel `model` della pagina, affinchè
  siano visibili all'utente.
