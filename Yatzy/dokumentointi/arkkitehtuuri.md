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

* Aloitusnäkymästä
* Nimenantonäkymästä
* Pelinäkymästä
* Moderaattorin näkymästä.

Jokainen näistä on toteutettu erillisenä JavaFX:n [Scene-oliona](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html), joita käsitellään käyttöliittymän luokassa erillisillä metodeilla. Käyttäjä näkee kerrallaan vain yhden Scene-olion käyttöliittymässään.


## Sovelluslogiikka

Sovelluksen looginen datamalli on oheisen kuvan mukainen.

![Sovelluslogiikka](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kuvat/luokkakaavio_v2.png)

Muiden osien suhdetta kuvaava pakkausjaavio

![pakkauskaavio](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kuvat/pakkausrakenne.png)

### Päätoiminnallisuuksia

Kuvataan seuraavaksi muutamia sovelluksen sisäisiä ja ulosnäkyviä päätoiminnallisuksia sekä niiden osia.

#### Tulos- ja ennätystaulun luominen

Oheisessa sekvenssikaaviossa on kuvattu tulos- ja ennätystaulujen luomisprosessi kun niitä kutsutaan YatzyUi-luokan metodissa ```redrawGameArea()```.

![Sekvenssikaavio tulos- ja ennätystaulujen luomiseksi](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kuvat/sekvenssikaavio_getScoreBoard_ja_getRecordBoard_04DEC2018.png)

Sekvenssikaavion selite TBA.


## Tietojen tallennus

Tiedoista vastaa luokka RecordDao.

Tietokanta Sql-tietokanta,käsitellään sqlite-ohjemalla. tietokanta sisältää vain yhhden taulun, joka on luotu komennolla 

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
