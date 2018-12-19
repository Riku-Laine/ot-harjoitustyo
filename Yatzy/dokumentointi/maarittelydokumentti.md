# Vaatimusmäärittelyt

**Sisällysluettelo**

* [Sovelluksen tarkoitus](#tarkoitus)
* [Käyttäjät](#kayttajat)
* [Suunnitellut toiminnallisuudet](#kehitys)


## <a name="tarkoitus"></a>Sovelluksen tarkoitus

Sovelluksessa on tarkoitus pelata noppapeliä [Yatzy](https://fi.wikipedia.org/wiki/Yatzy) (Wikipedia) yhdellä tai useammalla pelaajalla. Pelin tuloksen voi halutessaan tallentaa ennätystauluun kirittämään seuraavia pelejä.

Tulosten kirjaamisessa noudatetaan ensisijaisesti suomalaisia yhdistelmiä, jotka löytyvät pelin Wikipedia-sivulta. Tällöin esimerkiksi pieni suora tarkoittaa noppayhdistelmää 1, 2, 3, 4 ja 5. Peliin on lisätty mahdollisuus käyttää myös amerikkalaisia yhdistelmiä, jolloin pieni suora on nopat 1 &#8211; 4, 2 &#8211; 5, tai 3 &#8211; 6.

## <a name="kayttajat"></a>Käyttäjät ja käyttöroolit

Sovelluksessa on roolit *pelaajalle* ja *moderaattorille*.  

Pelaajat voivat pelata peliä, moninpelissä vuorotellen. Aloittava pelaaja on vapaavalintainen.

Moderaattorilla on pääsy ennätystauluun ja hän voi poistaa nimimerkkejä ja tuloksia tarpeiden mukaan. Moderaattorin henkilöllisyys varmennetaan salasanan avulla.

## <a name="kehitys"></a>Suunnitellut toiminnallisuudet

### Perusversio

* Pelaaja voi tai pelaajat voivat pelata Yatzya, sisältäen
	* noppien heiton, eli viiden numeron generoinnin
	* noppien valinnan, eli heiton rajauksen
	* kirjanpitoon kirjattavan yhdistelmän valinnan
* Pelaaja näkee koko ajan oman pistetilanteensa
	* Graafisessa käyttöliittymässä toteutetaan taulukolla
* Klikkaamalla voi valita nopat ja poistaa valinnan
* Pelaajat näkevät kyseiseen pelityyppiin liittyvät ennätykseet pelinäkymässä.
	* Pelityyppi määritellään noppien lukumäärällä, suurimmalla silmäluvulla, heittojen määrällä sekä käytetyllä tuloskortilla.
* Moninpelissä peliä pelataan vuorotellen
* Moderaattori voi poistaa valitsemiaan ennätyksiä.

### Jatkoideat

* Valittujen ja valitsemattomien noppien parempi erottelu käyttöliittymässä.
* Noppien silmäluku kuvina.
* Nopan silmäluvun muodostumisen animointi
* Noppien heiton animointi
* Äänitehosteet heitoille, pisteille ja pelin loppumiselle.
* Pelaaja voi asettaa avatarin (kuvakkeen) itselleen
* Vaihtoehtojen (täyskäsi, viitoset jne.) viereen tulostuu tuloksen todennäköisyys
* Pistetaulukossa näkyy reaaliaikaisesti noppayhdistelmästä saatavat pisteet
* Moninpeluuseen mahdollisuus pelata paras N:stä
