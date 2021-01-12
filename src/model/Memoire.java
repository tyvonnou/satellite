package model;

public class Memoire
{
  Integer tailleMemoire;
  Integer remplissageMemoire;

  public Memoire(int tailleMemoire)
  {
    this.tailleMemoire = tailleMemoire;
    this.remplissageMemoire = 0;
  }

  public void ajoutDonnees(int donnees)
  {
    if (this.remplissageMemoire + donnees <= this.tailleMemoire)
    {
      this.remplissageMemoire += donnees;
    }
    else
    {
      this.remplissageMemoire = this.tailleMemoire;
    }
  }

  public void resetMemoire()
  {
    this.remplissageMemoire = 0;
  }

  public boolean memoirePleine()
  {
    return (this.remplissageMemoire == this.tailleMemoire);
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public Integer getTailleMemoire()
  {
    return tailleMemoire;
  }

  public Integer getRemplissageMemoire()
  {
    return remplissageMemoire;
  }

}
