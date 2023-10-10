# Metropolian Kouluravintola Simulaatio

## Projekti Kuvaus

Tämä simulaatioprojekti on toteutettu ryhmätyönä osana Metropolian ohjelmistotuotannon Java-kurssia. Simulaation aiheena on Metropolian kouluravintolan toiminta Myllypuron kampuksella.

## Toiminnallisuus
Sovelluksessa on käytetty seuraavia ohjelmointikieliä: Java, SQL ja CSS.


Projekti perustuu erilaisten palvelupisteiden toimintaan, jotka ovat koulutavintolan perus komponentteja. Näihin palvelupisteisiin kuuluvat:

1. **Tavallinen Jono**: Tässä jonossa asiakkaat voivat odottaa vuoroaan linjastolle, jossa tarjoillaan peruskouluruokaa.

2. **Grillijono**: Grillijono on erityinen palvelupiste, jossa valmistetaan erilaisia grilliruokia.

3. **Maksupääte**: Maksupääte vastaa ruokailun maksamisesta ja kassatoiminnasta.

4. **Pöydät**: Pöydät edustavat paikkoja, joissa asiakkaat voivat ruokailla.

5. **Astioiden palautus**: Astioiden palautuspisteeseen asiakkaat vievät käyttämänsä astiat aterioinnin lopuksi.



## Projektin tarkoitus
Projektin tarkoituksena on simuloida näiden palvelupisteiden toimintaa ja vuorovaikutusta. Simulaation avulla voimme arvioida kouluravintolan tehokkuutta ja suorituskykyä eri tilanteissa. Lisäksi voimme testata erilaisia skenaarioita ja parannusehdotuksia kouluravintolan toimintaan.

## Kuinka simulaattoria käytetään

Sovelluksen käynnistäessä avautuu simulaation käyttöliittymän, jossa on erilaisia toimintoja joilla voidaan muokata simulaation toimintaa:
- **Aika**: Tällä toiminnolla asetetaan simulaatiolle aika.
- **Viive**: Tällä toiminnolla asetetaan viive, mikä kuvaa asiakkaiden sisääntulofrekvenssiä simulaation.
- **Palvelupisteiden kapasiteetit**: Tässä toiminnossa voidaan asettaa ja muokata palvelupisteiden kapasiteetteja.
- **Palvelupisteiden palveluajat**: Tässä voidaan asettaa ja muokata palvelupisteiden palveluaikoja syöttämällä normaalin jakauman keskiarvo ja hajonta.
- **Nopeuta ja hidasta**: Tällä toiminnolla voidaan nopeuttaa ja hidastaa simulaation kulkua (viivettä).
- **Keskittymisavustaja**: Tämä toiminto auttaa käyttäjää keskittymään simulaation seuraamiseen.
- **Tulostukset**: Tähän tulostuu simulaation lopputulokset simulaatioajan loputtua.

Simulaation kulkua ja palvelupisteiden kuormittumista havainnollistetaan visuaalisesti canvasilla simulaation ajon aikana.


## Tekijät

- [eemelimu](https://github.com/eemelimu)
- [salopietari](https://github.com/salopietari)
- [zbr12](https://github.com/zbr12)
