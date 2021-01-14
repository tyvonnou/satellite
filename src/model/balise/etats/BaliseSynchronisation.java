package model.balise.etats;

import model.balise.Balise;
import model.strategie.SynchronisationBalise;

/**
 * Etat de la balise utilisé lors de la synchronisation.
 */
public class BaliseSynchronisation implements EtatBalise
{

  SynchronisationBalise synchronisationBalise;

  public BaliseSynchronisation(Balise balise)
  {
    this.synchronisationBalise = new SynchronisationBalise(balise);
  }

  @Override
  public void action(Balise balise)
  {
    synchronisationBalise.synchronisation();
    if (synchronisationBalise.isTermine())
    {
      this.etatSuivant(balise);
    }
  }

  @Override
  public void etatSuivant(Balise balise)
  {
    balise.setEtat(new BaliseDescente(balise));
  }

}
