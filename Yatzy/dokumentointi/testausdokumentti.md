# Testauksen dokumentaatio

Ohjelmaa testattiin manuaalisesti käyttöliittymää käyttäen yrittäen syöttää virheellisiä syötteitä ja tehden poikkeuksellisia toimintoja. Ohjelmaa on lisäksi testattu automatisoiduin yksikkö- ja integraatiotestein JUnitin avulla.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Sovelluslogiikkaa testaavat testi löytyvät testipakkauksesta, joka on tehty vastaavalla nimennällä kuin varsinaiset koodit. YatzyServicen toiminnallisuuksia testataan suoraan samassa testiluokassa - sen olisi voinut jakaa useampaankin osaan. 

Integraatiotestejä tiedon pysyväistallennukseen ei ole tehty ajanpuutteen vuoksi. Jää jatkokehitykseeen.

Sovelluslogiikan muille luokille on tehty kattavasti itsenäisiä yksikkötestejä, jotka testaavat niiden ydintoiminnallisuuksia.

### DAO-toteutus

DAO-toteutusta testatessa hyödynnettiin [JUnitin](https://junit.org/junit4/javadoc/latest/org/junit/rules/TemporaryFolder.html) tarjoamaa TemporaryFolder-toteutusta, jonka avulla luodaan jokaista testiä varten tilapäinen tietokantatiedosto.

### Testauskattavuus

Käyttöliittymä rajattiin testien ulkopuolelle ja sairastumiseni aiheuttamien rajoitteiden vuoksi testi- ja rivikattavuus on saatu rimaa hipoen 70%:iin.

![Testi- ja rivikatttavuus](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kuvat/testikattavuus.PNG)


## Järjestelmän testaaminen

Sovelluksen järjestelmän, eli käytännössä graafisen käyttöliittymän testit on suoritettu manuaalisesti.

### Sovelluksen konfiguraatiot ja asentaminen

Sovellus ladattiin ja testattiin [käyttöohjeiden](https://github.com/Riku-Laine/ot-harjoitustyo/blob/master/Yatzy/dokumentointi/kayttoohje.md) mukaisesti Windows 10-ympäristössä. Testaajalla on ollut ympäristöönsä järjestelmänvalvojan oikeudet.

### Toiminnallisuudet

Vaatimusmäärittelyjen perusversion ja käyttöohjeiden kuvaamat toiminnallisuudet on käyty läpi. Toimivuuksia on testattu virheellisillä, kuten tyhjillä, syötteillä.

## Sovellukseen jääneet laatuongelmat

* YatzyServicen ja Daon integraatiota ei ole testattu
* Joissakin luokissa syötteen oikeellisuutta ei enää varmenneta logiikkapäässä, vaan luotetaan siihen, että UI suodattaa tiedon.
