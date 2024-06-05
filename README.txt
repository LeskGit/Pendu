

--------------------------------------------------------------------------------------------------------

--Configurer ClassPath

Dans VSCode, aller dans JAVA PROJECT en bas a gauche
Clicker sur les trois petit point puis Configure ClassPath
Aller ensuite dans Libraries puis ajouter les lib JAVA FX présent sur votre systeme

--Configuration launch.json
Aller dans le menu Run, puis Open Config
Ajouter : (en mettant le chemin d'acces pour les libraries a la place de "PATH") en dessous de "request"
"vmArgs": "--module-path PATH --add-modules javafx.controls,javafx.fxml",

--------------------------------------------------------------------------------------------------------



Maintenant, vous pouvez executer le programme avec la fleche en haut a droite.