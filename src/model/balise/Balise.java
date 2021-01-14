package model.balise;

import model.ElementMobile;
import model.Memoire;
import model.balise.etats.EtatBalise;
import model.strategie.deplacement.Deplacement;

public class Balise extends ElementMobile
{

  private static final int TAILLE_DONNEES_CAPTEURS = 1;

  EtatBalise etat;
  Deplacement deplacementCollecte;
  Integer profondeurCollecte;
  Memoire memoire;

  public Balise(Memoire memoire, int vitesse)
  {
    super(vitesse);
    this.memoire = memoire;
  }

  @Override
  public void tick()
  {
    this.etat.action(this);
  }

  /**
   * Enregistrement des données récoltées par les capteurs de la balise.
   */
  public void lectureCapteurs()
  {
    this.memoire.ajoutDonnees(TAILLE_DONNEES_CAPTEURS);
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public EtatBalise getEtat()
  {
    return etat;
  }

  public void setEtat(EtatBalise etat)
  {
    this.etat = etat;
  }

  public Deplacement getDeplacementCollecte()
  {
    return deplacementCollecte;
  }

  public void setDeplacementCollecte(Deplacement deplacementCollecte)
  {
    this.deplacementCollecte = deplacementCollecte;
  }

  public Integer getProfondeurCollecte()
  {
    return profondeurCollecte;
  }

  public void setProfondeurCollecte(Integer profondeurCollecte)
  {
    this.profondeurCollecte = profondeurCollecte;
  }

  public Memoire getMemoire()
  {
    return memoire;
  }

  public void setMemoire(Memoire memoire)
  {
    this.memoire = memoire;
  }

  public int getProfondeur()
  {
    return this.getPosition().y;
  }
}
