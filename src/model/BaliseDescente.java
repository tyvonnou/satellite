package model;

import model.deplacement.DeplacementDescente;

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
    if (this.deplacementDescente.profondeurAtteinte())
    {
      balise.setEtat(new BaliseCollecte(balise));
    }
  }
}
