# Arkkitehtuurikuvaus



## Rakenne

*Ohjelman rakenne noudattaa...*

Pakkauksista

- *yatzy.ui* tuottaa JAVAFX:llä totetutetun käyttöliittymän
- *yatzy.domain* sisältää sovelluslogiikasta vastaavan koodin
    - *yatzy.domain.scorecards* sisältää erilaisten tuloskorttien logiikan koodin
- *yatzy.dao* huolehtii tietojen pysyvästä tallennuksesta.

## Käyttöliittymä

Käyttöliittymä kostuu neljästä eri näkymästä:

* Aloitusnäkymästä
* Yhden pelaajan pelin aloitusnäkymästä
* Kahden pelaajan pelin aloitusnäkymästä
* Pelinäkymästä.
* (Moderaattorin näkymä TBA)

Eriytys tehty, kutsuu parametreilla Yatzyserviceä, pelisää melkein aina piirretään
pelinäkymä uusiksi joka toiminnon jälkeen
## Sovelluslogiikka

![Sovelluslogiikka](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kuvat/luokkakaavio.png)

### Päätoiminnallisuuksia

SKuvataan seuraavaksi muutamia sovelluksen sisäisiä ja ulosnäkyviä päätoiminnallisuksia sekä niiden osia.

#### Tulos- ja ennätystaulun luominen

Oheisessa sekvenssikaaviossa on kuvattu tulos- ja ennätystaulujen luomisprosessi kun niitä kutsutaan YatzyUi-luokan metodissa ```redrawGameArea()```.

![Sekvenssikaavio tulos- ja ennätystaulujen luomiseksi](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kuvat/sekvenssikaavio_getScoreBoard_ja_getRecordBoard_04DEC2018.png)

Sekvenssikaavion selite TBA.