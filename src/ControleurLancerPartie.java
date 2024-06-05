import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import java.util.Optional;

/**
 * Contrôleur à activer lorsque l'on clique sur le bouton rejouer ou Lancer une partie
 */
public class ControleurLancerPartie implements EventHandler<ActionEvent> {
    /**
     * modèle du jeu
     */
    private MotMystere modelePendu;
    /**
     * vue du jeu
     **/
    private Pendu vuePendu;

    /**
     * @param modelePendu modèle du jeu
     * @param p vue du jeu
     */
    public ControleurLancerPartie(MotMystere modelePendu, Pendu vuePendu) {
        this.modelePendu = modelePendu;
        this.vuePendu = vuePendu;
    }

    /**
     * L'action consiste à recommencer une partie. Il faut vérifier qu'il n'y a pas une partie en cours
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        if (this.vuePendu.getChrono().getElapsedTime() > 0) {
            Optional<ButtonType> reponse = this.vuePendu.popUpPartieEnCours().showAndWait();
            // si la réponse est oui
            if (reponse.isPresent() && reponse.get().equals(ButtonType.YES)) {
                System.out.println("Nouvelle partie lancée !");
                this.vuePendu.lancePartie();
                this.modelePendu.setMotATrouver();
                this.vuePendu.majAffichage();
                this.vuePendu.getChrono().resetTime();
                this.vuePendu.getChrono().start();
                
            } else {
                System.out.println("Partie en cours maintenue.");
            }
        } else {
            this.vuePendu.lancePartie();
            this.vuePendu.getChrono().resetTime();
            this.vuePendu.getChrono().start();
        }
    }
}
