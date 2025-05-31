# Homework 2,3 - Hotel

This is the second and third homework for ENGETO java course

## Developer notes:
- Dle úkolu je zapotřebí vytvořit BookingManagera, který bude handlovat 
rezervace a jejich detaily.
-- Dle mého je v tomto případě lepší řadit rezervace pod objekty pokojů pro jejich efektivnější
separaci. Rezervace odlišných pokojů spolu nemají nic společného a nijak se tak nemohou překrývat
- Vytváření rezervací je řešeno přes constructor třídy RoomReservation a validace přes
přetížené metody reserveRoom ve třídě Room. Zároveň jsem vytvořil třídu RoomReservationRequest
pro flexibilní vytváření Prereservation objektů, které mají předdefinované hodnoty
- U statistik jsem zvolil novou třídu ReservationAnalytics pro rozdělení odpovědností