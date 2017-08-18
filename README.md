# Application created as final project of JIMP2


# 1 Wstep teoretyczny
Wireworld to automat komórkowy zaproponowany przez Briana Silvermana
w roku 1987. Jest uzywany do symulacji elementów elektronicznych operujacych
na wartosciach bitowych. Pomimo prostoty reguł, jakie nim rzadza, za
pomoca Wireworld mozna stworzyc działajacy komputer. Symulacja odbywa
sie na tablicy dwuwymiarowej o okreslonym stanie poczatkowym (zakreslonych
liniach symbolizujacych przewodniki lub bramki logiczne).

Komórka w Wireworld moze znajdowac sie w jednym z czterech stanów:
- pusta EMPTY – biały,
- ogon elektrony TAIL – niebieski,
- głowa elektronu HEAD – czerwony,
- przewodnik CONDUCTOR – zółty.
Na podstawie danego stanu generalizacji mozna przewidziec, jaki bedzie nastepny
stan.

Jak we wszystkich automatach komórkowych upływ czasu przedstawiony jest
w postaci dyskretnych kroków czasowych, czyli generacji. Kolejne generacje
budowane sa z wykorzystaniem zestawu czterech zasad:
- komórka pusta zawsze pozostaje pusta (symbolizuje pusta przestrzen –
brak przewodnika),
- łowa elektronu przechodzi w ogon elektronu,
- ogon elektronu przechodzi w przewodnik,
- przewodnik przechodzi w głowe elektronu, tylko wtedy, gdy dokładnie 1 lub 2 komórki sasiadujace sa głowami elektronu.

# 2 Opis programu
Program obsługiwany bedzie przez interfejs graficzny korzystajacy z biblioteki
Swing. Uzytkownik bedzie miec mozliwosc wyboru:
 liczby generacji,
 pliku wejsciowego,
 przeskoczenie o jeden stan do przodu,
 rysowanie stanu poczatkowego mysza na ekranie.

# 3 Uzycie programu
Uzytkowanie programu bedzie odbywało sie w interfejsie graficznym korzystajacym
z biblioteki Swing. Uzytkownik bedzie mógł wybrac plik bedacy
stanem poczatkowym (zarysem obwodu), a nastepnie go załadowac.
Aby móc łatwiej budowac realne układy w celu doswiadczalnym zostanie
wprowadzony dodatkowa uzytecznosc aplikacji, a mianowicie mozliwosc dynamicznego
budowania poczatkowego stanu generacji. Po uruchomieniu aplikacji
uzytkownik wybiera czy chce załadowac plik, czy od razu przystapic do
„rysowania”.
Poczatkowo cała tablica jest pusta (brak przewodnika).
Uzytkownik lewym kliknieciem „zwieksza stan” komórki, a prawym kliknieciem
„zmniejsza stan” komórki.
Schemat przejscia:
brak przewodnika — przewodnik — głowa elektronu — ogon elektronu —
brak przewodnika
Nie trzeba nadawac kierunku ruchu elektronom, poniewaz o tym decyduje
ustawienie głowy elektronu.
# 4 Przechowywanie danych w programie
Dane beda przechowywane w tablicach dwuwymiarowych:
 class WorldGrid (zawierajaca pola WorldCell[][], int rows, int cols);
 metode getCell(int x, int y) – przechowujaca informacje o danym stanie.
Posiada dwa pola odpowiadajace współrzednym na planszy oraz
metode, zwracajaca biezacy stan.
# 5 Dane wejsciowe
Dane moga byc przekazywane do programu rysujac schemat w programie lub
wprowadzajac plik tekstowy, z nastepujacym formatem liczbowym, np:
00111111100
00100000100
00300000200
00200000300
00100000100
00100000100
00111111100

Przedstawiona powyzej figura bedzie obracac sie w nieskonczonosc w lewo.
Macierze wejsciowe nie moga przekraczac wymiarów 50 x 50. W przeciwnym
razie plik nie zostanie wczytany, a uzytkownik zostanie powiadomiony o tym
komunikatem.
# 6 Dane wyjsciowe
Kazda biezaca generacje mozna wyeksportowac do pliku wyjsciowego.
# 7 Scenariusz działania programu
1. Uzytkownik uruchamia aplikacje,
2. Uzytkownik wybiera z menu plik, który ma zostac potraktowany jako
poczatkowy stan generalizacji,
3. Uzytkownik wybiera ilosc pozadanych stanów, które maja zostac wygenerowane
i zatwierdza swój wybór,
4. System pobiera plik (stan poczatkowy),
5. System rozpoczyna iterowanie i generowanie kazdego ze stanów wyswietlajac
je na ekranie,
6. Uzytkownik moze wybrac opcje zapisu biezacego (ostatniego) stanu do
pliku tekstowego lub graficznego.
# 8 Testowanie
Program bedzie testowany przy uzyciu róznych zródłowych stanów poczatkowych
oraz przy róznych wymiarach. Program bedzie testowany recznie, przy
uzyciu mniejszych tablic, których zachowanie jest przewidywalne tj.:
- bramki AND,
- bramki OR,
- bramki NAND,
- bramki NOR,
- bramki XNOR,
- bramki XAND.
W razie przypadku, gdy uzytkownik poda błedny plik, taki do którego program
nie ma dostepu, w błednym formacie program sie nie wykona i zwróci
bład (zostanie to wyłapane przez wyjatek). Program bedzie wykonywac obsługe
błedów przed uruchomieniem generatora w blokach try/catch. Po podaniu
błednego pliku:
- błedne rozszerzenie,
- zbyt duzy rozmiar tablicy (max 50x50),
- tablica zawierajaca inne dane niz nalezace do zbioru 0,1, 2, 3,
plik nie bedzie pobierany, a uzytkownik zostanie powiadomiony o błedzie w
oknie.
