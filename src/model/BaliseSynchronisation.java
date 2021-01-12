package model;

import model.deplacement.SynchronisationBalise;

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
    synchronisationBalise.synchronisation(balise);
    if (synchronisationBalise.synchronisationFini())
    {
      balise.setEtat(new BaliseDescente(balise));
    }
  }

}
