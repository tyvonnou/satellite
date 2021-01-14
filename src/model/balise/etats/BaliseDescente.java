package model.balise.etats;

import model.balise.Balise;
import model.strategie.deplacement.DeplacementDescente;

/**
 * Etat de la balise utilisé lors de la redescente vers la profondeur de collecte de la balise.
 */
public class BaliseDescente implements EtatBalise
{

  DeplacementDescente deplacementDescente;

  public BaliseDescente(Balise balise)
  {
    this.deplacementDescente = new DeplacementDescente(balise.getProfondeurCollecte());
    balise.setDeplacement(this.deplacementDescente);
  }

  @Override
  public void action(Balise balise)
  {
    balise.bouge();

    if (this.deplacementDescente.isTermine())
    {
      this.etatSuivant(balise);
    }
  }

  @Override
  public void etatSuivant(Balise balise)
  {
    balise.setEtat(new BaliseCollecte(balise));
  }
}
