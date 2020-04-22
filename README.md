# Alap
Az ötlet egy payment system megvalósítása valamilyen belső rendszernek (pl. online játékba, nem a paypal-ra kell gondolni!). Ezt a payment systemet aztán több különböző projekt is tudná használni.
A rendszer lényegében egy REST API + egy minimalista swing UI (elsősorban demonstrációra). MySQL-hez fog kapcsolódni.
## Userek:
- Neve
- Email-je
- Kapcsolt fizetési metódusa (Kártya, számla, igazi PayPal)
- Saját "credit"-je
## Termékek
- Név
- Ár
- Megvásárolható?

# Megvalósítások:
- SOLID elvek
- Singleton: Adatbázis kapcsolat
- Template method: fizetési módok
- Observer: Fizetési értesítések
