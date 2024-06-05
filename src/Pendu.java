import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.ButtonBar.ButtonData ;

import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;


/**
 * Vue du jeu du pendu
 */
public class Pendu extends Application {
    /**
     * modèle du jeu
     **/
    private MotMystere modelePendu;
    /**
     * Liste qui contient les images du jeu
     */
    private ArrayList<Image> lesImages;
    /**
     * Liste qui contient les noms des niveaux
     */    
    public List<String> niveaux;

    // les différents contrôles qui seront mis à jour ou consultés pour l'affichage
    /**
     * le dessin du pendu
     */
    private ImageView dessin;
    /**
     * le mot à trouver avec les lettres déjà trouvé
     */
    private String motCrypte;
    /**
     * la barre de progression qui indique le nombre de tentatives
     */
    private ProgressBar pg;
    /**
     * le clavier qui sera géré par une classe à implémenter
     */
    private Clavier clavier;
    /**
     * le text qui indique le niveau de difficulté
     */
    private Text leNiveau;
    /**
     * le chronomètre qui sera géré par une clasee à implémenter
     */
    private Chronometre chrono;
    /**
     * le panel Central qui pourra être modifié selon le mode (accueil ou jeu)
     */
    private BorderPane panelCentral;
    /**
     * le bouton Paramètre / Engrenage
     */
    private Button boutonParametres;
    /**
     * le bouton Accueil / Maison
     */    
    private Button boutonMaison;
    /**
     * le bouton qui permet de lancer ou relancer une partie
     */ 
    private Button bJouer;

    private Button boutonInfo;

    private String alpha;

    /**
     * initialise les attributs (créer le modèle, charge les images, crée le chrono ...)
     */
    @Override
    public void init() {
        this.modelePendu = new MotMystere("/usr/share/dict/french", 3, 10, MotMystere.FACILE, 10);
        this.lesImages = new ArrayList<Image>();
        this.chargerImages("img");
        this.chrono = new Chronometre();
        this.alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ-";
        this.clavier = new Clavier(alpha, new ControleurLettres(modelePendu, this));
        this.panelCentral = new BorderPane();
        this.bJouer = new Button("Lancer une partie");
        this.motCrypte = this.modelePendu.getMotCrypte();
        this.chrono = new Chronometre();
        this.pg = new ProgressBar(0);
        this.dessin = new ImageView("pendu0.png");
        this.leNiveau = new Text();
    }

   



    /**
     * @return  le graphe de scène de la vue à partir de methodes précédantes
     */
    private Scene laScene(){
        BorderPane fenetre = new BorderPane();
        fenetre.setTop(this.titre());
    
        this.panelCentral.setPadding(new Insets(20));
        fenetre.setCenter(panelCentral);


        
        return new Scene(fenetre, 800, 1000);
    }

    /**
     * @return le panel contenant le titre du jeu
     */
    private BorderPane titre(){
        // A implementer          
        BorderPane banniere = new BorderPane();
        banniere.setPadding(new Insets(20));
        banniere.setStyle("-fx-background-color: lightblue;");
        HBox right = new HBox();

        Text titre = new Text("Jeu du Pendu");
        this.boutonMaison = new Button();
        this.boutonParametres = new Button();
        this.boutonInfo = new Button();
        ImageView imageMaison = new ImageView("home.png");
        ImageView imageParam = new ImageView("parametres.png");
        ImageView imageInfo = new ImageView("info.png");
        imageMaison.setFitWidth(50);  // Largeur désirée
        imageMaison.setFitHeight(50); // Hauteur désirée
        imageParam.setFitWidth(50);  // Largeur désirée
        imageParam.setFitHeight(50); // Hauteur désirée
        imageInfo.setFitWidth(50);  // Largeur désirée
        imageInfo.setFitHeight(50); // Hauteur désirée

        titre.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        this.boutonMaison.setGraphic(imageMaison);
        this.boutonParametres.setGraphic(imageParam);
        this.boutonInfo.setGraphic(imageInfo);

        right.getChildren().addAll(this.boutonMaison, this.boutonParametres, this.boutonInfo);
        banniere.setRight(right);;
        banniere.setLeft(titre);
        return banniere;
    }

    // /**
     // * @return le panel du chronomètre
     // */
    // private TitledPane leChrono(){
        // A implementer
        // TitledPane res = new TitledPane();
        // return res;
    // }

    // /**
     // * @return la fenêtre de jeu avec le mot crypté, l'image, la barre
     // *         de progression et le clavier
     // */
     private Pane fenetreJeu(){
       
         Pane res = new Pane();

         HBox main = new HBox();

         VBox first = new VBox();
         Label mc = new Label(this.motCrypte);
         mc.setFont(Font.font("Arial", 32));
         mc.setPadding(new Insets(0,0,0,160));

         VBox draw = new VBox();
         draw.getChildren().addAll(this.dessin);
         draw.setPadding(new Insets(10,0,0,60));
         this.pg.setPadding(new Insets(10,0,0,150));
         

         first.getChildren().addAll(mc, draw, this.pg, this.clavier);
        
         VBox second = new VBox();
         

         main.getChildren().addAll(first, second);
         res.getChildren().addAll(main);
         return res;
     }

     /**
      * @return la fenêtre d'accueil sur laquelle on peut choisir les paramètres de jeu
      */
     private Pane fenetreAccueil(){
        VBox res = new VBox();
        
        ControleurLancerPartie ctrlLp = new ControleurLancerPartie(modelePendu, this);
        ControleurNiveau ctrlNiv = new ControleurNiveau(modelePendu);
        VBox mid = new VBox();
        VBox bot = new VBox();
    
        
        this.bJouer.setOnAction(ctrlLp);
        TitledPane diff = new TitledPane();
        diff.setPadding(new Insets(20, 0, 0, 0));
        diff.setText("Niveau de difficulté");
        diff.setCollapsible(false);
        
        ToggleGroup tgl = new ToggleGroup();
        

        RadioButton easy = new RadioButton("Facile");
        easy.setToggleGroup(tgl);
        easy.setOnAction(ctrlNiv);
        RadioButton normal = new RadioButton("Normal");
        normal.setToggleGroup(tgl);
        normal.setOnAction(ctrlNiv);
        RadioButton hard = new RadioButton("Difficile");
        hard.setToggleGroup(tgl);
        hard.setOnAction(ctrlNiv);
        RadioButton expert = new RadioButton("Expert");
        expert.setToggleGroup(tgl);
        expert.setOnAction(ctrlNiv);
        easy.setPadding(new Insets(3,0,3,0));
        normal.setPadding(new Insets(3,0,3,0));
        hard.setPadding(new Insets(3,0,3,0));
        expert.setPadding(new Insets(3,0,3,0));

  

        bot.getChildren().addAll(easy, normal, hard, expert);
        diff.setContent(bot);
        mid.getChildren().addAll(this.bJouer, diff);
        res.getChildren().addAll(mid);
        return res;
     }

    /**
     * charge les images à afficher en fonction des erreurs
     * @param repertoire répertoire où se trouvent les images
     */
    private void chargerImages(String repertoire){
        for (int i=0; i<this.modelePendu.getNbErreursMax()+1; i++){
            File file = new File(repertoire+"/pendu"+i+".png");
            System.out.println(file.toURI().toString());
            this.lesImages.add(new Image(file.toURI().toString()));
        }
    }

    public void modeAccueil(){
        this.panelCentral.setCenter(fenetreAccueil());
    }
    
    public void modeJeu(){
        this.panelCentral.setCenter(fenetreJeu());
    }
    
    public void modeParametres(){
        // A implémenter
    }

    /** lance une partie */
    public void lancePartie(){
        
        this.modelePendu = new MotMystere("/usr/share/dict/french", 3, 10, MotMystere.FACILE, 10);
        modeJeu();
    }

    /**
     * raffraichit l'affichage selon les données du modèle
     */
    public void majAffichage(){
        // A implementer
    }

    /**
     * accesseur du chronomètre (pour les controleur du jeu)
     * @return le chronomètre du jeu
     */
    public Chronometre getChrono(){
        // A implémenter
        return this.chrono;
    }

    public Alert popUpPartieEnCours(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"La partie est en cours!\n Etes-vous sûr de l'interrompre ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Attention");
        return alert;
    }
        
    public Alert popUpReglesDuJeu(){
        // A implementer
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        return alert;
    }
    
    public Alert popUpMessageGagne(){
        // A implementer
        Alert alert = new Alert(Alert.AlertType.INFORMATION);        
        return alert;
    }
    
    public Alert popUpMessagePerdu(){
        // A implementer    
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        return alert;
    }

    /**
     * créer le graphe de scène et lance le jeu
     * @param stage la fenêtre principale
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("IUTEAM'S - La plateforme de jeux de l'IUTO");
        stage.setScene(this.laScene());
        this.modeAccueil();       
        stage.show();
    }

    /**
     * Programme principal
     * @param args inutilisé
     */
    public static void main(String[] args) {
        launch(args);
        
    }    
}
