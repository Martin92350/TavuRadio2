# TavuRadio

Voici l'application Tavu Radio !! Tavu Radio est une webradio qui a été créée par Anthony Montfray et Marilou Noël.
Aller visiter leur site : https://www.tavuradio.fr/
Cette application a pour objectif de reprendre quelques éléments du site internet et de les mettre sur une application mobile, 
pour que les auditeurs de Tavu Radio soient constamment connectés sur la radio du chat! (si vous voulez comprendre la blague,
checker leur site internet!)

# Utilisation

Sur cette application, l'utilisateur a le choix d'écouter de la musique via un lien stream sur la première activité de l'application. 
Ce lien d'écoute mp3 correspond aux enregistrements faits par le studio Tavu Radio lui-même. On y retrouve des musiques super-cool
d'aujourd'hui, des jingles, des bouts de sketch ou de one-man-show de célébrités. Néanmoins, si l'utilisateur est plutôt d'humeur à
lire, il peut se rendre sur "#TavuL'actu", ce qui lui permettra d'avoir accès à une liste d'actualités divers/people sur une autre 
activité de l'application. Une fois sur cette liste, l'utilisateur peut cliquer sur une des actualités de la liste pour avoir plus de 
détails, cela le renverra sur la page web, source de l'information, sur laquelle il y aura tout un article dessus. L'utilisateur a la
possibilité de switcher entre les deux activités (radio ou info). 

# Points techniques abordés : 

Les contraintes respectées :
 - affichage d'une liste d'élements dans un recyclerview
 - appel d’une API Rest en ligne  pour récupérer des données que l'on veut afficher dans cette liste
 - affichage du détail d'un élément de la liste, renvoie vers page web 
 - stockage de données en cache : sauvegarde de la liste des éléments en cache lors d'une erreur de réseau
- Gitflow 
Autres fonctionnalités :
- mis en place d'un lecteur de musique qui récupère l'enregistrement via un lien stream
- création du logo d'application Tavu Radio
 - création d'une activité pour une page d'accueil lors du démarrage de l'application 
- implémentation d’un swipeRefresh permettant de rafraîchir la liste 

# Fonctionnalités :

•	Icône de démarrage    
![image](https://user-images.githubusercontent.com/63060751/83967776-d6431b80-a8c4-11ea-85a2-582b6bdf813f.png)

•	Ecran de démarrage d’une durée de 2 secondes   
![image](https://user-images.githubusercontent.com/63060751/83967796-08547d80-a8c5-11ea-82b2-168d44391d98.png)

•	Ecran principale (lecteur radio + bouton « #TAVUL'ACTU » d’accès à l’ecran de la liste)

![image](https://user-images.githubusercontent.com/63060751/83967824-3df96680-a8c5-11ea-8d49-c2e1573540ef.png)  ![image](https://user-images.githubusercontent.com/63060751/83967835-4ce01900-a8c5-11ea-80d7-7b8c07616aec.png)



•	Ecran contenant la liste d’actualité 

![image](https://user-images.githubusercontent.com/63060751/83967858-6e410500-a8c5-11ea-8620-b72ffb2fc40c.png)


•	Ecran de page web reprenant l’image, la source et la date de l’élément sélectionné et qui déroule tout un article sur l’actualité choisie

![image](https://user-images.githubusercontent.com/63060751/83967889-92044b00-a8c5-11ea-8b29-d46e9203d1a6.png) ![image](https://user-images.githubusercontent.com/63060751/83967901-9df00d00-a8c5-11ea-87af-520e2f427713.png)

   
# Auteur

Martin Noël 
 
 
