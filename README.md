# Yatzy

Sovelluksen avulla on mahdollista pelata noppapeliä Yatzy joko yhdellä tai kahdella pelaajalla. Ennen pelin alkua pelaajille asetetaan nimimerkit, joiden alle talletetaan pelaajan ennätys. Pelissä noudatetaan ns. suomalaisen Yatzyn sääntöjä.

_Hauskoja pelihetkiä!_

## Dokumentaatio

* [Käyttöohje TBA](<!---https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kayttoohje.md ---->)
* [Vaatimusmäärittelyt](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/maarittelydokumentti.md)
* [Arkkitehtuurikuvaus](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/arkkitehtuuri.md)
* [Testausdokumentti TBA](<!---https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/urlitestausdokumenttiin.md --->)
* [Työaikakirjanpito](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/tuntikirjanpito.md)

## Releaset

Löydät viidennen viikon releasen [täältä](https://github.com/Riku-Laine/ot-harjoitustyo/releases/tag/Viikko_5)!

## Komentorivitoiminnot

### Testaus

Suorita testit komennolla ```mvn test``` ja luo testikattavuusraportti käyttämällä komentoa ```mvn jacoco:report```.

Voit tarkastella raporttia avaamalla tiedostoselaimella tiedosto _target/site/jacoco/index.html_, jolloin raportti avautuu haluamaasi verkkoselaimeen.

### Jarin generointi

Generoi _target_-hakemistoon suoritettava jar-tiedosto komennolla ```mvn package```. Tiedoston nimi on _Yatzy-1.0-SNAPSHOT.jar_.

### JavaDoc

Generoi projektin JavaDoc komennolla ```mvn javadoc:javadoc```. JavaDocia voit tarkastella etsimällä tiedostoselaimella tiedosto _target/site/apidocs/index.html_ ja avaamalla se haluamaasi selaimeen.

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/checkstyle.xml) määritellyt tarkistukset voit suorittaa komennolla ```mvn jxr:jxr checkstyle:checkstyle```.

Mahdolliset virheilmoitukset löytyvät tiedostosta _target/site/checkstyle.html_.
