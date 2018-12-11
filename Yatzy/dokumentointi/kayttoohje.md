# Käyttöohje

Lataa uusin versio sovelluksesta [Yatzy-vX.X.jar](https://github.com/Riku-Laine/ot-harjoitustyo/releases).

## Peli

Käynnistä sovellus komennolla ```java -jar Yatzy-vX.X.jar``` korvaten X-kirjaimet kulloisenkin version nimellä.

### Aloitus

Sovellus käynnistyy aloitusnäkymään, jossa voit aloittaa tavallisen pikapelin tai asetuksiltaan kustomoidun pelin.

![aloitusnäkymä](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kuvat/aloitusn%C3%A4ytt%C3%B6.PNG)

Pikapeli on Yatzy yhdellä tai kahdella pelaajalla perinteiseen skandinaaviseen pöytäkirjaan. Napin painamisen jälkeen ohjelma siirtää sinut ikkunaan, jossa voit antaa pelaajille nimimerkit. Tulokset tallennetaan ja päivittyvät ennätyslistaan nimimerkin ja pelin asetusten perusteella.

Keskeltä voit kirjautua järjestelmänvalvojana ennätystietokantaan poistamaan ennätyksiä. Salasana on _salasana_.

Ikkunan alareunasta voit aloittaa täysin kustomoidun pelin haluamallasi määrällä pelaajia, noppia ja heittoja vuorossa. Voit myös valita käytätkö amerikkalaista vai skandinaavista tuloskorttia sekä mikä on noppien suurin mahdollinen silmäluku.

### Nimien anto

Pelin aloittamisen jälkeen siirrytään nimenantonäkymään. Oikealla olevien radiobuttonien avulla voit valita aloittavan pelaajan. Kenttiä tulee niin monta kuin pelaajia on valittu.

![nimenantoikkuna](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kuvat/nimenantoikkuna.PNG)

Paina ``Begin`` aloittaaksesi pelin ja siirtyäksesi pelinäkymään.

### Pelaaminen

![pelinäkymä](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kuvat/pelitila.PNG)

Pelinäkymässä vasemmalla voit valita ja heittää noppia. Näet sieltä myös käytettyjen heittojen määrän sekä vuorossa olevan pelajan nimen. Kun olet heittänyt noppia ensimmäisen kerran vuorollasi, keskellä olevat yhdistelmäpainikkeet aktivoituvat. Voit valita haluamasi yhdistelmän tuloskorttiin painimalla yhdistelmäpainiketta. Kirjausta ei voi perua.

Pelinäkymässä oikealla ylhäällä näkyy kyseisen pelin pistetilanne. Oikeassa alanurkassa näkyy kyseiseen pelityyppiin liittyvät ennätykset.

Kun peli on pelattu loppun, sovellus avaa automaattisesti dialogin tulosten tallentamiseksi.

#### Säännöt

Pelissä noudatetaan Wikipediaan kirjoitettuja sääntöjä. Skandinaavinen versio noudattaa [suomenkieliseltä](https://fi.wikipedia.org/wiki/Yatzy) sivulta löytyvää pistetytystä ja amerikkalainen vastaavasti [englanninkielistä](https://en.wikipedia.org/wiki/Yahtzee). Pelistä puuttuvat vielä välibonukset.

### Ennätysten hallinnointi

![ennätysten hallinnointi](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kuvat/adminn%C3%A4kym%C3%A4.PNG)

Kun olet kirjautunut järjestelmänvalvojana järjestelmään, voit poistaa haluamiasi ennätyksiä. Valitse poistettava ennätys ja paina ``Remove selected``. Varmenna valinta poistaaksesi tulokset. ``Remove all``-painike poistaa kaikki ennätykset. **Poistoja ei voi perua.**

Näkymä on tyhjä, jos tietokannassa ei ole ennätyksiä.
