Do rukou se Vám dostala didaktická hra Chemické 3D pexeso, která byla vytvořena mnou při psaní diplomové práce "Jednoduché počítačové hry pro výuku
 chemie - možnosti a limity" na PřF UK, Katedře učitelství a didaktiky chemie.

Obsah souboru:
1) Spuštění hry
2) Informace o hře
3) Možnost úpravy hry
4) Lokace zdrojového kódu 

1) Spuštění hry:
Hra se spustí dvojklikem na Chemicke_3D_pexeso.jar.
Pro spuštění hry je nutné mít nainstalovanou Javu verze min 1.7.
Pro zjištění, zda je nainstalována potřebná verze Javy udělejte následující:
	- zmáčkněte tlačítko Start
	- spustit nebo rovnou napište do vyhledávací řádky
	- napište: "cmd" a zmáčkněte enter. Otevře se textová konsole
	- napište: "java -version" a zmáčkněte enter
Pokud je Java nainstalována, vypíše se číslo verze, při chybové hlášce je nutné Javu doinstalovat. (stáhnutí: https://www.java.com/en/download/)

Po nainstalování Javy by se měla změnit ikonka spouštěcího souboru Chemicke_3D_pexeso.jar.
Pokud se nezmění, klikněte na něj pravým tlačítkem myši a zvolte 
"Otevřít v  programu.." a vyberte Java.

Pokud hra stále nelze spustit, obraťte se prosím na správce sítě či jinou pověřenou osobu.

2) Informace o hře
Jedná se o analogii pexesa, přičemž jedna kartička je vždy 3D model dané molekuly. Hrát může i 1 člověk - na body. Při zobrazení
3D modelu se také vypiše informační text o molekule.

K dispozici jsou 2 tematické okruhy 
	1. Organické názvosloví - je připraveno 42 dvojic, dvojice jsou uvedeny v souboru res/organickeNazvoslovi
	2. Přírodní látky - je připraveno 24 dvojic, dvojice jsou uvedeny v souboru res/prirodniLatky
	
3) Možnost úpravy hry
	1)Odebrat dvojici ze zásobárny dvojic lze snadno - v souboru res/organickeNazvoslovi (či res/prirodniLatky) se smaže příslušná řádka. Doporučuji si soubor zálohovat.
	2)Přidání dvojice do zásoábrny dvojic je složitější. Postup je uveden v diplomové práci.
	3)Změnit informační text u 3D modelu je snadné - např. pro acetylen se přepíše obsah souboru res/organicke nazvoslovi/acetylen.sdf.txt
	
4) Lokace zdrojového kódu
Kód je k dispozici na githubu: https://github.com/vitekfiala/Vitek_Github_repo


autor: Vít Fiala  - vit.fiala@gmail.com
06/2016
