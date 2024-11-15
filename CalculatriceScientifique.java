/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculatricescientifique;

/**
 *
 * @author User
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

interface Calculatrice {
    double addition(double a, double b);
    double soustraction(double a, double b);
    double multiplication(double a, double b);
    double division(double a, double b);
    double puissance(double base, double exposant);
    double racineCarree(double nombre);
    double sinus(double angle);
    double cosinus(double angle);
    double tangente(double angle);
    double pourcentage(double a);
    double logarithme(double a);
    double logarithmeNaturel(double a);
}

public class CalculatriceScientifique extends JFrame implements Calculatrice {
    private JTextField affichage;
    private double memoire = 0; // Pour la gestion de la mémoire

    public CalculatriceScientifique() {
        setTitle("Calculatrice Scientifique");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Champ d'affichage pour les résultats
        affichage = new JTextField();
        affichage.setEditable(false);
        affichage.setHorizontalAlignment(JTextField.RIGHT);
        affichage.setFont(new Font("Arial", Font.BOLD, 24));
        add(affichage, BorderLayout.NORTH);

        // Panneau pour les boutons
        JPanel panelBoutons = new JPanel();
        panelBoutons.setLayout(new GridLayout(5, 4, 5, 5));

        // Création des boutons d'opération
        String[] operations = {"Addition", "Soustraction", "Multiplication", "Division",
                               "Puissance", "Racine", "Sinus", "Cosinus", "Tangente", 
                               "%", "log", "ln", "π", "e", "M+", "M-", "MR", "MC", "←", "Effacer"};
        for (String op : operations) {
            JButton button = new JButton(op);
            button.setFont(new Font("Arial", Font.PLAIN, 16));
            button.addActionListener(new OperationListener());
            panelBoutons.add(button);
        }

        add(panelBoutons, BorderLayout.CENTER);
        setVisible(true);
    }

    // Classe interne pour gérer les événements de bouton
    private class OperationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String commande = e.getActionCommand();
            double resultat = 0;

            try {
                switch (commande) {
                    case "π":
                        affichage.setText("Résultat : " + Math.PI);
                        return;
                    case "e":
                        affichage.setText("Résultat : " + Math.E);
                        return;
                    case "Effacer":
                        affichage.setText("");
                        return;
                    case "←":
                        String currentText = affichage.getText();
                        affichage.setText(currentText.length() > 0 ? currentText.substring(0, currentText.length() - 1) : "");
                        return;
                    case "M+":
                        memoire += Double.parseDouble(affichage.getText().split(":")[1].trim());
                        return;
                    case "M-":
                        memoire -= Double.parseDouble(affichage.getText().split(":")[1].trim());
                        return;
                    case "MR":
                        affichage.setText("Mémoire : " + memoire);
                        return;
                    case "MC":
                        memoire = 0;
                        affichage.setText("Mémoire effacée");
                        return;
                    default:
                        break;
                }

                double a = Double.parseDouble(JOptionPane.showInputDialog("Entrez le nombre :"));
                double b = 0;
                if (!commande.equals("Racine") && !commande.equals("Sinus") &&
                    !commande.equals("Cosinus") && !commande.equals("Tangente") &&
                    !commande.equals("%") && !commande.equals("log") && !commande.equals("ln")) {
                    b = Double.parseDouble(JOptionPane.showInputDialog("Entrez le deuxième nombre :"));
                }

                switch (commande) {
                    case "Addition" -> resultat = addition(a, b);
                    case "Soustraction" -> resultat = soustraction(a, b);
                    case "Multiplication" -> resultat = multiplication(a, b);
                    case "Division" -> resultat = division(a, b);
                    case "Puissance" -> resultat = puissance(a, b);
                    case "Racine" -> resultat = racineCarree(a);
                    case "Sinus" -> resultat = sinus(a);
                    case "Cosinus" -> resultat = cosinus(a);
                    case "Tangente" -> resultat = tangente(a);
                    case "%" -> resultat = pourcentage(a);
                    case "log" -> resultat = logarithme(a);
                    case "ln" -> resultat = logarithmeNaturel(a);
                }

                affichage.setText("Résultat : " + resultat);
            } catch (NumberFormatException ex) {
                affichage.setText("Erreur : Entrée non valide.");
            }
        }
    }

    // Implémentation des méthodes de l'interface Calculatrice
    @Override
    public double addition(double a, double b) { return a + b; }
    @Override
    public double soustraction(double a, double b) { return a - b; }
    @Override
    public double multiplication(double a, double b) { return a * b; }
    @Override
    public double division(double a, double b) {
        if (b == 0) {
            affichage.setText("Erreur : Division par zéro!");
            return Double.NaN;
        }
        return a / b;
    }
    @Override
    public double puissance(double base, double exposant) { return Math.pow(base, exposant); }
    @Override
    public double racineCarree(double nombre) {
        if (nombre < 0) {
            affichage.setText("Erreur : Racine carrée d'un nombre négatif!");
            return Double.NaN;
        }
        return Math.sqrt(nombre);
    }
    @Override
    public double sinus(double angle) { return Math.sin(Math.toRadians(angle)); }
    @Override
    public double cosinus(double angle) { return Math.cos(Math.toRadians(angle)); }
    @Override
    public double tangente(double angle) { return Math.tan(Math.toRadians(angle)); }
    @Override
    public double pourcentage(double a) { return a / 100; }
    @Override
    public double logarithme(double a) {
        if (a <= 0) {
            affichage.setText("Erreur : Logarithme d'un nombre négatif ou zéro!");
            return Double.NaN;
        }
        return Math.log10(a);
    }
    @Override
    public double logarithmeNaturel(double a) {
        if (a <= 0) {
            affichage.setText("Erreur : Logarithme naturel d'un nombre négatif ou zéro!");
            return Double.NaN;
        }
        return Math.log(a);
    }

    // Point d'entrée principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatriceScientifique::new);
    }
}
