import ma.alae.eloula.classes.Client;
import ma.alae.eloula.classes.Employee;
import ma.alae.eloula.dao.implementation.ClientImp;
import ma.alae.eloula.dao.implementation.EmployeImp;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static int getInputAsInt(Scanner scanner) {
        int userInput = 0; // Initialisation avec une valeur par défaut

        boolean inputValid = false;
        while (!inputValid) {
            System.out.print("Entrez un nombre entier : ");
            String input = scanner.nextLine();

            try {
                userInput = Integer.parseInt(input);
                if (userInput > 0)
                    inputValid = true; // La conversion en int a réussi, l'entrée est valide
            }
            catch (NumberFormatException e) {
                System.out.println("Entrée non valide. Veuillez entrer un nombre entier.");
            }
        }

        return userInput;
    }
    public static void main(String[] args) {
        // Press Alt+Entrée with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        Scanner scanner=new Scanner(System.in);
        //Employee e1 =new Employee();
        EmployeImp eimp=new EmployeImp();
        ClientImp cimp = new ClientImp();
        //
       /* e1.setNom("alae");
        e1.setPrenom("alae");
        e1.setTel("06000000");
        e1.setDateNaissance(LocalDate.parse("2020-03-03"));
        e1.setDateRecrutement(LocalDate.parse("2022-03-03"));
        e1.setEmail("alaa@alaa.com");
        eimp.ajouterEmployee(e1);*/
        System.out.println("entreer id");
       // eimp.getEmployeeById();
        /*
        eimp.getEmployeeById(getInputAsInt(scanner)).
                ifPresent(employee -> {
            // Affichez les détails de l'employé trouvé
                    System.out.println("Employé trouvé :");
            System.out.println("ID : " + employee.getId());
            System.out.println("Nom : " + employee.getNom());
            System.out.println("Prénom : " + employee.getPrenom());
            System.out.println("Date de naissance : " + employee.getDateNaissance());
            System.out.println("Téléphone : " + employee.getTel());
            System.out.println("Email : " + employee.getEmail());
            System.out.println("Date de recrutement : " + employee.getDateRecrutement());
        });*/
        //eimp.SupprimerEmpl(1);
        Client c1 =new Client();
       c1.setNom("hehe");
        c1.setPrenom("alae");
        c1.setTel("06000000");
        c1.setDateNaissance(LocalDate.parse("2020-03-03"));
        c1.setAddress("fgvhbngfghj");
        cimp.ajouterClient(c1);

        /*int rowsDeleted = cimp.supprimerClient(10);;
        if (rowsDeleted > 0) {
            System.out.println("Client supprimé avec succès.");
        } else {
            System.out.println("Aucun client trouvé avec l'ID " );
        }*/

        int clientIdToFind = 1; // Remplacez par l'ID du client que vous souhaitez trouver
        Optional<Client> client = cimp.rechercherClient(5);

        if (client.isPresent()) {
            Client foundClient = client.get();
            // Affichez les détails du client trouvé
            System.out.println("Client trouvé :");
            System.out.println("ID : " + foundClient.getId());
            System.out.println("Nom : " + foundClient.getNom());
            System.out.println("Prénom : " + foundClient.getPrenom());
            System.out.println("Date de naissance : " + foundClient.getDateNaissance());
            System.out.println("Téléphone : " + foundClient.getTel());
            System.out.println("Adresse : " + foundClient.getAddress());
        } else {
            System.out.println("Aucun client trouvé avec l'ID " + clientIdToFind);
        }


    }
}