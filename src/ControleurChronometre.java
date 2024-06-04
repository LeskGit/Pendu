import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Contrôleur du chronomètre
 */
public class ControleurChronometre implements EventHandler<ActionEvent> {
    /**
     * temps enregistré lors du dernier événement
     */
    private long tempsPrec;
    /**
     * temps écoulé depuis le début de la mesure
     */
    private long tempsEcoule;
    /**
     * Vue du chronomètre
     */
    private Chronometre chrono;

    /**
     * Constructeur du contrôleur du chronomètre
     * noter que le modèle du chronomètre est tellement simple
     * qu'il est inclus dans le contrôleur
     * @param chrono Vue du chronomètre
     */
    public ControleurChronometre (Chronometre chrono){
        this.chrono = chrono;
        this.tempsEcoule = -1;
        this.tempsPrec = 0;

    }

    /**
     * Actions à effectuer tous les pas de temps
     * essentiellement mesurer le temps écoulé depuis la dernière mesure
     * et mise à jour de la durée totale
     * @param actionEvent événement Action
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        long heureSystem = System.currentTimeMillis();
        if (this.tempsEcoule != -1) {
            long duree = heureSystem - this.tempsEcoule;
            this.tempsPrec += duree;
            this.chrono.setText(tempsPrec/1000 + "");
        }
        this.tempsEcoule = heureSystem;
    }

    /**
     * Remet la durée à 0
     */
    public void reset(){
        // A implémenter
    }
}
