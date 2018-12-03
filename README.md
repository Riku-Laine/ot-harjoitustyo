# Yatzy

**Sisällysluettelo:**

* [Dokumentaatio](#dokumentaatio)
* [Releaset](#releaset)
* [Komentorivitoiminnot](#komentorivitoiminnot)

## <a href="dokumentaatio"></a> Dokumentaatio

* [Käyttöohje TBA](<!---https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kayttoohje.md ---->)
* [Vaatimusmäärittelyt](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/maarittelydokumentti.md)
* [Arkkitehtuurikuvaus](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/arkkitehtuuri.md)
* [Testausdokumentti TBA](<!---https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/urlitestausdokumenttiin.md --->)
* [Työaikakirjanpito](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/tuntikirjanpito.md)

## <a href="releaset"></a> Releaset

## <a href="komentorivitoiminnot"></a> Komentorivitoiminnot

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
