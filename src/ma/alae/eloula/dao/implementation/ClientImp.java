package ma.alae.eloula.dao.implementation;

import connection.SingletonConnection;
import ma.alae.eloula.classes.Client;

import java.sql.*;
import java.util.Optional;

public class ClientImp {
    Connection connection = SingletonConnection.getConn();
    //@Override
    public Optional<Client> ajouterClient(Client client) {
        Optional<Client> addedClient = Optional.empty();
        try {
            // Commencez par insérer les données dans la table "Personel"
            String personelSql = "INSERT INTO Personel (nom, prenom, dateNaissance, tel) VALUES (?, ?, ?, ?)";
            PreparedStatement personelStatement = connection.prepareStatement(personelSql, Statement.RETURN_GENERATED_KEYS);
            personelStatement.setString(1, client.getNom());
            personelStatement.setString(2, client.getPrenom());
            personelStatement.setObject(3, client.getDateNaissance());
            personelStatement.setString(4, client.getTel());

            int personelRowsAffected = personelStatement.executeUpdate();

            if (personelRowsAffected == 1) {
                ResultSet generatedPersonelKeys = personelStatement.getGeneratedKeys();
                if (generatedPersonelKeys.next()) {
                    int personelId = generatedPersonelKeys.getInt(1);

                    // Ensuite, insérez les données dans la table "Client" avec l'ID de "Personel"
                    String clientSql = "INSERT INTO Client (id, address) VALUES (?, ?)";
                    PreparedStatement clientStatement = connection.prepareStatement(clientSql);
                    clientStatement.setInt(1, personelId); // Utilisez l'ID de Personel
                    clientStatement.setString(2, client.getAddress());

                    int clientRowsAffected = clientStatement.executeUpdate();

                    if (clientRowsAffected == 1) {
                        // Si tout s'est bien passé, retournez le client ajouté
                        addedClient = Optional.of(client);
                    }
                }
            }

            // Assurez-vous de libérer les ressources
            personelStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return addedClient;
    }


    public int supprimerClient(int clientId) {
        int rowsAffected = 0;
        try {
            // Supprimez d'abord l'enregistrement dans la table "Client"
            String clientSql = "DELETE FROM Client WHERE id = ?";
            PreparedStatement clientStatement = connection.prepareStatement(clientSql);
            clientStatement.setInt(1, clientId);

            rowsAffected += clientStatement.executeUpdate();

            // Ensuite, supprimez l'enregistrement correspondant dans la table "Personel"
            String personelSql = "DELETE FROM Personel WHERE id = ?";
            PreparedStatement personelStatement = connection.prepareStatement(personelSql);
            personelStatement.setInt(1, clientId);

            rowsAffected += personelStatement.executeUpdate();

            // Assurez-vous de libérer les ressources
            clientStatement.close();
            personelStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowsAffected;
    }

   // @Override
    public Optional<Client> rechercherClient(int clientId) {
        Optional<Client> clientFound = Optional.empty();
        try {
            // Créez une requête SQL pour récupérer les informations du client en fonction de son ID
            String sql = "SELECT c.id, c.address, p.nom, p.prenom, p.dateNaissance, p.tel FROM Client c " +
                    "INNER JOIN Personel p ON c.id = p.id " +
                    "WHERE c.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, clientId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Si un client correspondant est trouvé, créez un objet Client avec les informations
                int id = resultSet.getInt("id");
                String address = resultSet.getString("address");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                java.sql.Date dateNaissance = resultSet.getDate("dateNaissance");
                String tel = resultSet.getString("tel");

                // Créez un objet Client
                Client client = new Client(id, nom, prenom, dateNaissance.toLocalDate(), tel, address);

                clientFound = Optional.of(client);
            }

            // Assurez-vous de libérer les ressources
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return clientFound;
    }
}
