package model.balise.etats;

import model.balise.Balise;

/**
 * Etat de la balise utilisé lors de la récupération d'informations.
 */
public class BaliseCollecte implements EtatBalise
{

  public BaliseCollecte(Balise balise)
  {
    balise.setDeplacement(balise.getDeplacementCollecte());
  }

  @Override
  public void action(Balise balise)
  {
    balise.bouge();
    balise.lectureCapteurs();

    if (balise.getMemoire().memoirePleine())
    {
      this.etatSuivant(balise);
    }
  }

  @Override
  public void etatSuivant(Balise balise)
  {
    balise.setEtat(new BaliseMontee(balise));
  }
}
