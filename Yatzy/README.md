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

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Jarin generointi

Komento

```
mvn package
```

deneroi hakemistoon _target_ suoritettavan jar-tiedoston _Yatzy-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_


### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
