import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

/**
 * Controleur du clavier
 */
public class ControleurLettres implements EventHandler<ActionEvent> {

    /**
     * modèle du jeu
     */
    private MotMystere modelePendu;
    /**
     * vue du jeu
     */
    private Pendu vuePendu;

    /**
     * @param modelePendu modèle du jeu
     * @param vuePendu vue du jeu
     */
    ControleurLettres(MotMystere modelePendu, Pendu vuePendu){
        this.modelePendu = modelePendu;
        this.vuePendu = vuePendu;
    }

    /**
     * Actions à effectuer lors du clic sur une touche du clavier
     * Il faut donc: Essayer la lettre, mettre à jour l'affichage et vérifier si la partie est finie
     * @param actionEvent l'événement
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        // Obtenir le bouton source de l'événement
        Button current = (Button) actionEvent.getSource();
        
        // Récupérer la lettre associée au bouton
        String lettre = current.getText();
        
        // Vérifier que la lettre n'est pas vide et a une longueur de 1
        char lettreChar = lettre.charAt(0); // Convertir la lettre en char

        // Essayer cette lettre dans le modèle
        this.modelePendu.essaiLettre(lettreChar);

        // Instructions de débogage (facultatif)
        System.out.println("Lettre essayée : " + lettreChar);
        System.out.println("Mot crypté : " + this.modelePendu.getMotCrypte());
        System.out.println("Erreurs restantes : " + this.modelePendu.getNbErreursRestants());

        // Mettre à jour l'affichage
        vuePendu.majAffichage();
        
        // Vérifier si la partie est gagnée
        if (this.modelePendu.gagne()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Gagné !");
            alert.setHeaderText(null);
            alert.setContentText("Félicitations, vous avez gagné !");
            alert.showAndWait();
            // Réinitialiser ou lancer une nouvelle partie
            this.vuePendu.getChrono().resetTime();
            this.vuePendu.getChrono().start();
            this.modelePendu.setMotATrouver();
            vuePendu.majAffichage();
        } 
        // Vérifier si la partie est perdue
        else if (this.modelePendu.perdu()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Perdu");
            alert.setHeaderText(null);
            alert.setContentText("Désolé, vous avez perdu. Le mot était : " + this.modelePendu.getMotATrouve());
            alert.showAndWait();
            // Réinitialiser ou lancer une nouvelle partie
            this.vuePendu.getChrono().resetTime();
            this.vuePendu.getChrono().start();
            this.modelePendu.setMotATrouver();
            vuePendu.majAffichage();
        }
    }
}
