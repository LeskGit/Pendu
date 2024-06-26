import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle ;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Génère la vue d'un clavier et associe le contrôleur aux touches
 * le choix ici est d'un faire un héritié d'un TilePane
 */
public class Clavier extends TilePane{
    /**
     * il est conseillé de stocker les touches dans un ArrayList
     */
    private List<Button> clavier;

    /**
     * constructeur du clavier
     * @param touches une chaine de caractères qui contient les lettres à mettre sur les touches
     * @param actionTouches le contrôleur des touches
     * @param tailleLigne nombre de touches par ligne
     */
    public Clavier(String touches, EventHandler<ActionEvent> actionTouches) {

        this.clavier = new ArrayList<>();

       
         for (char c : touches.toCharArray()) {
            Button button = new Button(String.valueOf(c));
            button.setOnAction(actionTouches); 
            this.clavier.add(button);
            this.getChildren().add(button); 
        }

        // Configurer l'apparence du TilePane
        this.setPadding(new Insets(10));
        this.setHgap(5);
        this.setVgap(5);
     
      
    }

    /**
     * permet de désactiver certaines touches du clavier (et active les autres)
     * @param touchesDesactivees une chaine de caractères contenant la liste des touches désactivées
     */
    public void desactiveTouches(Set<String> touchesDesactivees) {
        for (Button button : this.clavier) {
            if (touchesDesactivees.contains(button.getText())) {
                button.setDisable(true);
            } else {
                button.setDisable(false);
            }
        }
    }
}
