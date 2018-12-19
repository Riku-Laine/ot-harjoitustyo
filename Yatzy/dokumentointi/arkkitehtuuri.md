# Arkkitehtuurikuvaus


## Rakenne

Ohjelman rakenne on jaettu kolmitasoiseksi oheisen kuvan mukaisesti.

![pakkauskaavio](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kuvat/pakkausrakenne.png)

Pakkauksista

- *yatzy.ui* tuottaa JAVAFX:llä totetutetun käyttöliittymän
- *yatzy.domain* sisältää sovelluslogiikasta vastaavan koodin
    - *yatzy.domain.scorecards* sisältää erilaisten tuloskorttien logiikan koodin
- *yatzy.dao* huolehtii tietojen pysyvästä tallennuksesta.

## Käyttöliittymä

Käyttöliittymä kostuu neljästä eri näkymästä:

* aloitusnäkymästä
* nimenantonäkymästä
* pelinäkymästä
* moderaattorin näkymästä.

Jokainen näistä on toteutettu erillisenä JavaFX:n [Scene-oliona](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html), joita käsitellään käyttöliittymän luokassa erillisillä metodeilla. Käyttäjä näkee kerrallaan vain yhden Scene-olion käyttöliittymässään, joka on eriytetty sovelluslogiikasta.

Aloitusnäkymä rakennetaan suoraviivaisesti suoraan [YatzyUi-luokassa](https://github.com/Riku-Laine/ot-harjoitustyo/blob/d44689853ba39f33dc907a60be90b26c00592419/Yatzy/src/main/java/yatzy/ui/YatzyUi.java#L100). Luokka on jaettu kommentoiduilla väliotsikoilla osiin, joissa jokaisessa konstruoidaan yhden edellä mainittu näkymän rakenne. Luokkamuuttujina olevien komponenttien alustus on pääosin hoidettu init()-metodissa.

YatzyUi-luokassa on erilliset metodit eri näkymien sisältöjen päivittämiseen sekä nimenantonäkymän luomiseen. Nimenantonäkymän luomiseksi on tehty erillinen metodi [```drawNameGivingScene```](https://github.com/Riku-Laine/ot-harjoitustyo/blob/d44689853ba39f33dc907a60be90b26c00592419/Yatzy/src/main/java/yatzy/ui/YatzyUi.java#L509) vastaamaan kustomoitavan pelin tarpeita.

## Sovelluslogiikka

Sovelluksen looginen datamalli on oheisen kuvan mukainen.

![Sovelluslogiikka](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kuvat/luokkakaavio_v2.png)

Muiden osien suhdetta kuvaava pakkauskaavio alla.

![pakkauskaavio](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kuvat/pakkausrakenne.png)

Scorecard-luokasta on tehty abstrakti, jotta sen tarjoamia päätoiminnalisuuksia tiettyjen yhdistelmien pisteiden laskemiseksi ei tarvitse toteuttaa sen aliluokissa. Metodeja tarjotaan esimerkiksi erilaisten monikkojen ja mielivaltaisten pituisten suorien laskemiseksi. Aliluokat on sijoitettu _yatzy.domain.scorecard_-pakkaukseen.

### Sovelluksen päätoiminnallisuuksia

Kuvataan seuraavaksi muutamia sovelluksen sisäisiä ja ulosnäkyviä päätoiminnallisuksia sekä niiden osia.

#### Tulos- ja ennätystaulun luominen

Oheisessa sekvenssikaaviossa on kuvattu tulos- ja ennätystaulujen luomisprosessi kun niitä kutsutaan YatzyUi-luokan metodissa ```redrawGameArea()```.

![Sekvenssikaavio tulos- ja ennätystaulujen luomiseksi](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kuvat/sekvenssikaavio_getScoreBoard_ja_getRecordBoard_04DEC2018.png)

Tuloskortin tulostava metodi ``getScoreBoard()`` hakee ensin kaikki pelaajat ja yhdistää niistä ensimmäisen otsikkorivin. Sen jälkeen vuorossa olevan pelaajan tuloskortista haetaan kyseisessä pelissä olevat yhdistelmät ja tuloksia aletaan kirjoittaa. Tuloksia kirjoitetaan riveittäin: jokaiselle riville tulostetaan ensin kyseinen yhdistelmä ja sitten jokaisen pelaajan pisteet järjestyksessä ja tämä toistetaan jokaiselle yhdistelmälle.

Ennätystaulu hakee RecordDao-luokan avulla ensin kaikki ennätykset ArrayList-muodossa, joka sisältää [Record](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/src/main/java/yatzy/domain/Record.java)-olioita. Jokainen olio sitten käsitellään merkkijonoksi ja palautetaan tulostettavaksi käyttöliittymään.

## Tietojen tallennus

Tietojen tallennuksesta vastaa luokka [RecordDao](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/src/main/java/yatzy/dao/RecordDao.java). Tietokanta on toteutettu SQL-tietokantana, jota käsitellään [SQLite-ohjelmalla](https://sqlite.org/index.html). Tietokanta sisältää yhden taulun, joka luodaan ``initDB()``-metodissa komennolla 

```SQL
CREATE TABLE IF NOT EXISTS Records (
	id integer PRIMARY KEY,  
	name varchar(200),  
	scorecard_type varchar(200),  
	dice_amount integer,  
	max_dice_number integer,  
	throws_amount integer,  
	points integer  
);
```

RecordDao-luokka toteuttaa [Dao-rajapinnan](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/src/main/java/yatzy/dao/Dao.java) ja [Dao-suunnittelumallin](https://en.wikipedia.org/wiki/Data_access_object) sekä tarjoaa toiminnallisuudet ennätysten
* löytämiseen
* päivittämiseen
* tallentamiseen
* poistamiseen.

## Sovelluksen toteutukseen jääneet heikkoudet

### Graafinen käyttöliittymä

Tällä hetkellä käyttöliittymä on toteutettu suoraan yhdessä luokassa, joka on pituutensa vuoksi varsin raskaslukuinen. Osan toimintoja voisikin eriyttää erilliseen luokkaan.

Tuloskorttien valinta on tällä hetkellä kovakoodattuna järjestelmään. Sitä pitäisi kehittää niin, että järjestelmä osaisi automaattisesti listata käytettävissä olevat tuloskortit, kunhan ne löytyvät jostain tietystä paikasta.
