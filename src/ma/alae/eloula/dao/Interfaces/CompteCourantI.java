package ma.alae.eloula.dao.Interfaces;

import ma.alae.eloula.classes.CompteCourant;

public interface CompteCourantI extends Acounte{
    public int creerCompteCourant(CompteCourant compteCourant, int idClient);
    CompteCourant findCompteCourantById(int numeroCompteCourant);
    boolean updateCompteCourantEtCompte(CompteCourant compteCourant);
}
