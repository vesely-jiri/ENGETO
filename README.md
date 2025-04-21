# Main branch slouží jako template pro úkolové větve

## Poznámky autora:
### Popis druhé+třetí úkolové větve a úkolového zadání č.3(lekce 3 Hotel - evidence rezervací)
- Dle úkolu je zapotřebí vytvořit BookingManagera, který bude handlovat 
rezervace a jejich detaily.
-- Dle mého je v tomto případě lepší řadit rezervace pod objekty pokojů pro jejich efektivnější
separaci. Rezervace odlišných pokojů spolu nemají nic společného a nijak se tak nemohou překrývat
- Vytváření rezervací je řešeno přes constructor třídy RoomReservation a validace přes
přetížené metody reserveRoom ve třídě Room. Zároveň jsem vytvořil třídu RoomReservationRequest
pro flexibilní vytváření Prereservation objektů, které mají předdefinované hodnoty
- U statistik jsem zvolil oddělení pomocí nového package a nové třídy ReservationAnalytics pro
rozdělení odpovědností