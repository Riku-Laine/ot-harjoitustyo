# Yatzy

Sovelluksen avulla on mahdollista pelata noppapeliä Yatzy yksin tai kaverien kanssa. Ennen pelin alkua pelaajille asetetaan nimimerkit, joiden alle talletetaan pelaajan ennätys. Pelissä voi valita, kirjataanko tulokset skandinaaviseen vai amerikkalaistyyliseen pöytäkirjaan.

_Hauskoja pelihetkiä!_

## Dokumentaatio

* [Käyttöohje](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kayttoohje.md)
* [Vaatimusmäärittelyt](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/maarittelydokumentti.md)
* [Arkkitehtuurikuvaus](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/arkkitehtuuri.md)
* [Testausdokumentti](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/testausdokumentti.md)
* [Työaikakirjanpito](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/tuntikirjanpito.md)

## Releaset

Löydät kaikki releaset [täältä](https://github.com/Riku-Laine/ot-harjoitustyo/releases)!

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
