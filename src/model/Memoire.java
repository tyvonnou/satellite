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

  protected void ajoutDonnées(int données)
  {
    if (this.remplissageMemoire + données <= this.tailleMemoire)
    {
      this.remplissageMemoire += données;
    }
    else
    {
      this.remplissageMemoire = this.tailleMemoire;
    }
  }

  protected void resetMemoire()
  {
    this.remplissageMemoire = 0;
  }

  protected boolean memoirePleine()
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
