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
