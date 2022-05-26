V1 du projet - Lambert Baptiste

Etat projet :

Toute la v1 est implémentée, et la v2 est en cours, je l'enverrai après car je ne peux pas rester
sur un travail non achevé.

Compiler le projet dans le dossier courant :
javac *.java 

Executer le projet dans le dossier courant :
java Monde

Etat conception :

Premièrement, dans notre simulation il n'existe qu'un seul monde : d'où le pattern singleton utilisé :
une seule instance de monde est partagée pour toute la simulation.

La conception prévoit déjà l'implémentation des renards, ainsi qu'éventuellement d'autres territoires
comme des fôrets par exemple sur lesquelles pousseront d'autre vegetaux.

Elle prévoie aussi l'ajout d'autre animaux indépendants ou qui se déplacent avec leur mère (quand ils sont
enfants) pour alimenter la chaine alimentaire. 

À ce mot, les déplacements ne sont pas fixes :
un déplacement est propre à chaque animal, deux types ont été inplémentés :
- Independant : l'animal fait sa vie et se déplace sur les prairies qu'il trouve
la plus interessante en fonction de son état.
- Enfant : un enfant poursuit sa mère jusqu'au bout du monde, si cette dernière décède
alors l'enfant aussi

Ceci est possible grâce au pattern strategy IDeplacer qu'implémentent ici Independant et 
Enfant, qui permet de changer le comportement de déplacement d'un Animal.

De même, les régimes ne sont pas fixes, on peut être soit :
- Herbivore (et choisir les plantes qu'on veut manger)
- Carnivore (et choisir les animaux qu'on veut manger)

Cela est possible grâce au pattern strategy IRegime qu'implémentent ici Herbivore et Carnivore.
Grâce à ce pattern on peut chercher de la nourriture exclusivement en fonction de son régime
et des aliments qu'on peut manger, et ensuite manger. 
On peut aisément rajouter des régimes comme Omnivore par exemple.

Ensuite un animal est guidé par son instinct, il est soit :
- Affamé (il va chercher à manger à tout prix)
- Rassasie (il va checher à se reproduire à tout prix s'il est adulte)

Pour ce faire, j'ai implémenté un state design pattern afin d'avoir un automate (ici nommé instinct)
qui va se charger d'appeler les bonnes méthodes de l'animal en fonction de son état courant.
L'automate va aussi permettre de modifier l'état courant de l'animal pour faire évoluer son instinct :
"si j'ai assez d'energie je deviens rassasié et cherche donc à me reproduire pour assurer la survie
de mon espèce".

Ici c'est la fonction agir de l'animal qui est dictée par son instinct.

Enfin, j'ai implémenté une pile d'action dans mon Monde afin de pouvoir gérer les listes d'animaux
sans altérer le fonctionnement de la simulation (pour ne pas supprimer un animal alors qu'on itère sur la liste
par exemple)

Fonctionnement :

Le monde possède une Carte, qui est un graphe connexe de Territoire.
Dans le monde sont créés et ajoutés sur la cartes les territoires (ici des prairies).
La simulation commence avec 2 lapins de sexes opposés et dure 1000 tours (possible de changer la
valeur dans Monde.lancerSimulation). Le graphe quant à lui possède 10 sommets.

Il est intéressant de voir que les lapins colonisent tout le graphe ! Et qu'ils souffrent 
de cette croissance démographique car il n'y a pas assez de nourriture pour tous.

lancerSimulation de la classe Monde va appeler tous les jours (boucle for jusqu'à n) la méthode
simulerJour() sur la Carte qui va appeler simulerJour() sur ses Territoire qui vont appeler
survivre() sur chacun de leurs animaux.

Survivre() fait faire deux actions à l'animal :
- se deplacer (en fonction de sa strategy)
- agir (en fonction de son etat)

Si l'animal trouve son bonheur sur son territoire alors il peut y rester.
Cependant si il y a un prédateur sur sa carte il fuira.
Peu importe le déplacement, un animal indépendant évitera à tout prix de se 
deplacer sur la case d'un prédateur.

En parlant de ces derniers : ils arrivent !
