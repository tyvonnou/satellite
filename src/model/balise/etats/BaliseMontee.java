package model.balise.etats;

import model.balise.Balise;
import model.strategie.deplacement.DeplacementMontee;

/**
 * Etat de la balise utilisé lors de la remontée vers la surface.
 */
public class BaliseMontee implements EtatBalise
{

  DeplacementMontee deplacementMontee;

  public BaliseMontee(Balise balise)
  {
    this.deplacementMontee = new DeplacementMontee();
    balise.setDeplacement(this.deplacementMontee);
  }

  @Override
  public void action(Balise balise)
  {
    balise.bouge();

    if (this.deplacementMontee.isTermine())
    {
      this.etatSuivant(balise);
    }
  }

  @Override
  public void etatSuivant(Balise balise)
  {
    balise.setEtat(new BaliseSynchronisation(balise));
  }
}
