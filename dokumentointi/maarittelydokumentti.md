# Määrittelydokumentti (alustava)

**Sisällysluettelo**

* [Sovelluksen tarkoitus](#tarkoitus)
* [Käyttäjät](#kayttajat)
* [Suunnitellut toiminnallisuudet](#kehitys)


## <a name="tarkoitus"></a>Sovelluksen tarkoitus

Sovelluksessa on tarkoitus pelata noppapeliä [Yatzy](https://fi.wikipedia.org/wiki/Yatzy) yhdellä tai kahdella pelaajalla. Yksin pelatessa oman tuloksensa voi tallentaa ennätyslistaan omalla nimimerkillä.

## <a name="kayttajat"></a>Käyttäjät ja käyttöroolit

Sovelluksessa on roolit *pelaajalle* ja myöhemmin lisättävälle *moderaattorille*.  

Pelaajat voivat pelata peliä, kaksinpelissä vuorotellen.

Moderaattorilla on pääsy ennätystauluun ja hän voi poistaa nimimerkkejä ja tuloksia tarpeiden mukaan. Moderaattorin henkilöllisyys varmennetaan salasanan avulla.

## <a name="kehitys"></a>Suunnitellut toiminnallisuudet

### Perusversio

* Pelaaja voi pelata Yatzya
* Pelaaja näkee koko ajan oman pistetilanteensa
* Klikkaamalla voi valita nopat ja poistaa valinnan(noppien kuvat)
* Jos pelaaja yksinpelatessaan ylittää ennätyksen, ennätystulos päivittyy vastaamaan tulosta.
	* *Miten toteutus? Kirjautuminen / Ylikirjoitus / Ei samoja nimimerkkejä*
* Kaksinpeluussa peliä pelataan vuorotellen, pelinäkymä erilainen

### Jatkoideat

* Ennätyslista
	* Useita tuloksia
	* Tuloksen yhteyteen on mahdollista lisätä nimimerkki
* Moderaattori hallinnoi ennätyslistaa
	* Asiattomien nimimerkkien poisto
* Nopan silmäluvun muodostumisen animointi
* Noppien heiton animointi
* Vaihtoehtojen (täyskäsi, viitoset jne.) viereen tulostuu tuloksen todennäköisyys
* Pistetaulukossa reaaliaikaisesti tulos, joka vahvistetaan vuoron päätyttyä
* Kaksinpeluuseen mahdollisuus pelata paras N:stä

