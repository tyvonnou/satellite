package model.balise.etats;

import model.balise.Balise;

/**
 * Etat d'une balise, basé sur le State Pattern.
 */
public interface EtatBalise
{
  public void action(Balise balise);

  public void etatSuivant(Balise balise);
}
