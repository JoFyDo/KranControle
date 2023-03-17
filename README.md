# KranControle

Das ist die Projektarbeit von Ole, Aaron und Jonas

Wir haben uns mit dem Kran beschäftigt.
Da das Testen mit dem physikalischen Kran eher ausgefallen ist, ist dies nur reine Theorie. Diese ist aber durchdacht und objektorientiert umgesetzt.<br>
<br>
Die Klasse Kran hat mehrere Enum Klassen, diese beinhalten States in der der Kran potenziell sein kann. Die Positionsstates haben zusätzlich Koordinaten zu denen der Kran fahren muss. Zudem sind dort auch unsere Bit Masken gespeichert, die wir benötigen um die richtigen Motoren anzusteuern oder Sensoren auszulesen. So müssen wir diese nur einmal deklarieren.<br>
<br>
Die Klasse Controle extends die Klasse Automat. Sie ist das Brain hinter dem Kran. Die Enums sind in einer Liste gespeichert. Durch diese läuft das Programm durch und führt alle States des Kran aus. So haben wir eine geregelte Abfolge. SetZero kalibriert den Kran zu seinem 0 Punkt von diesem bauen unsere Koordinaten auf. UpdateLocation updated die Koordinaten. Navigate bekommt Koordinaten übergeben, zu dem der Kran fahren muss. ToggleMagnet schaltet den Magnet ein oder aus.
<br>

Die Klasse TurnFinderHelp hilft uns einmalig die Koordinaten zu bestimmen zu dem der Kran sich bewegen muss.

<br>
Die Beteiligung im Projekt sah folgendermaßen aus(Dies sind nur grobe Zahlen, da wir zusammen in einem Team gearbeitet haben und einander geholfen haben. Aber sie wollten diese ja angegeben haben):

Ole:<br>
Zustandsdiagramm: 90%<br>
Code: 10%<br>
Automat + Dokumentation verstehen: 1/3<br>

Aaron:<br>
Code: 30%<br>
Automat + Dokumentation verstehen: 1/3<br>

Jonas:<br>
Zustandsdiagramm: 10%<br>
Code: 60%<br>
Automat + Dokumentation verstehen: 1/3<br>
README erstellt<br>
Mit ihnen den Automaten repariert ;)
  
