package model;

import events.satellitemoved.SatelitteMoved;
import events.satellitemoved.SatelitteMovedListener;
import model.deplacement.DeplSynchronisation;
import model.deplacement.Deplacement;
import model.deplacement.DeplacementBalise;
import model.deplacement.MonteSurfacePourSynchro;
import model.deplacement.Redescendre;

public class Balise extends ElementMobile implements SatelitteMovedListener
{

  private static final int TAILLE_DONNEES_CAPTEURS = 1;

  Memoire memoire;

  public Balise(Memoire memoire)
  {
    super();
    this.memoire = memoire;
  }

  @Override
  public void tick()
  {
    this.lectureCapteurs();
    if (this.memoire.memoirePleine())
    {
      Deplacement redescendre = new Redescendre(this.deplacement(), this.getProfondeur());
      Deplacement deplSynchro = new DeplSynchronisation(redescendre);
      Deplacement nextDepl = new MonteSurfacePourSynchro(deplSynchro);
      this.setDeplacement(nextDepl);
      this.memoire.resetMemoire();
    }
    super.tick();
  }

  protected void lectureCapteurs()
  {
    this.memoire.ajoutDonnées(TAILLE_DONNEES_CAPTEURS);
  }

  // ================================================================================
  // Évenements
  // ================================================================================

  @Override
  public void whenSatelitteMoved(SatelitteMoved arg)
  {
    DeplacementBalise dp = (DeplacementBalise) this.deplacement;
    dp.whenSatelitteMoved(arg, this);
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public int getProfondeur()
  {
    return this.getPosition().y;
  }

  public Memoire getMemoire()
  {
    return memoire;
  }

  public void setMemoire(Memoire memoire)
  {
    this.memoire = memoire;
  }
}
