package model;

import model.deplacement.DeplacementMontee;

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
    if (this.deplacementMontee.surfaceAtteinte())
    {
      balise.setEtat(new BaliseSynchronisation(balise));
    }
  }
}
