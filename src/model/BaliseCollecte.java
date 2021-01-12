package model;

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
    if (!balise.memoire.memoirePleine())
    {
      balise.lectureCapteurs();
    }
    else
    {
      balise.setEtat(new BaliseMontee(balise));
    }
  }
}
