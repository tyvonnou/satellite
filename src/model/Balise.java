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

  Integer tailleMemoire;
  Integer remplissageMemoire;

  @Override
  public void tick()
  {
    this.lectureCapteurs();
    if (this.memoirePleine())
    {
      Deplacement redescendre = new Redescendre(this.deplacement(), this.getProfondeur());
      Deplacement deplSynchro = new DeplSynchronisation(redescendre);
      Deplacement nextDepl = new MonteSurfacePourSynchro(deplSynchro);
      this.setDeplacement(nextDepl);
      this.resetMemoire();
    }
    super.tick();
  }

  public Balise(int tailleMemoire)
  {
    super();
    this.tailleMemoire = tailleMemoire;
    this.remplissageMemoire = 0;
  }

  protected void lectureCapteurs()
  {
    this.remplissageMemoire++;
  }

  protected void resetMemoire()
  {
    this.remplissageMemoire = 0;
  }

  protected boolean memoirePleine()
  {
    return (this.remplissageMemoire >= this.tailleMemoire);
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

  public int getTailleMemoire()
  {
    return this.tailleMemoire;
  }

  public int getRemplissageMemoire()
  {
    return this.remplissageMemoire;
  }
}
