# Määrittelydokumentti

**Sisällysluettelo**

* [Sovelluksen tarkoitus](#tarkoitus)
* [Käyttäjät](#kayttajat)
* [Suunnitellut toiminnallisuudet](#kehitys)


## <a name="tarkoitus"></a>Sovelluksen tarkoitus

Sovelluksessa on tarkoitus pelata noppapeliä [Yatzy](https://fi.wikipedia.org/wiki/Yatzy) yhdellä tai kahdella pelaajalla. Yksin pelatessa oman tuloksensa voi tallentaa ennätyslistaan omalla nimimerkillä.

Tulosten kirjaamisessa noudatetaan ensisijaisesti suomalaisia yhdistelmiä, jotka löytyvät pelin Wikipedia-sivulta. Tällöin esimerkiksi pieni suora tarkoittaa noppayhdistelmää 1, 2, 3, 4 ja 5.

## <a name="kayttajat"></a>Käyttäjät ja käyttöroolit

Sovelluksessa on roolit *pelaajalle* ja myöhemmin lisättävälle *moderaattorille*.  

Pelaajat voivat pelata peliä, kaksinpelissä vuorotellen.

Moderaattorilla on pääsy ennätystauluun ja hän voi poistaa nimimerkkejä ja tuloksia tarpeiden mukaan. Moderaattorin henkilöllisyys varmennetaan salasanan avulla.

## <a name="kehitys"></a>Suunnitellut toiminnallisuudet

### Perusversio

* Pelaaja voi pelata Yatzya, sisältäen
	* noppien heiton, eli viiden numeron generoinnin
	* noppien valinnan, eli heiton rajauksen
	* kirjanpitoon kirjattavan yhdistelmän valinnan
* Pelaaja näkee koko ajan oman pistetilanteensa
	* Graafisessa käyttöliittymässä toteutetaan taulukolla
* Klikkaamalla voi valita nopat ja poistaa valinnan (noppien kuvat)
* Jos pelaaja yksinpelatessaan ylittää ennätyksen, ennätystulos päivittyy vastaamaan tulosta.
* Kaksinpeluussa peliä pelataan vuorotellen, pelinäkymä erilainen

### Jatkoideat

* Ennätyslista
	* Useita tuloksia
	* Tuloksen yhteyteen on mahdollista lisätä nimimerkki
* Moderaattori hallinnoi ennätyslistaa
	* Asiattomien nimimerkkien poisto
* Nopan silmäluvun muodostumisen animointi
* Noppien heiton animointi
* Mielivaltainen määrä pelaajia
* Mielivaltainen määrä noppia
* Erikoisnopat (N-sivuinen)
* Vaihtoehtojen (täyskäsi, viitoset jne.) viereen tulostuu tuloksen todennäköisyys
* Pistetaulukossa reaaliaikaisesti tulos, joka vahvistetaan vuoron päätyttyä
* Moninpeluuseen mahdollisuus pelata paras N:stä

*Perusversion vaatimusmäärittelyjä tarkennettu 16.11. alakohdilla ja kuvaukseen lisätty maininta suomalaisista säännöistä.  
7.12. pieniä muokkauksia.*
