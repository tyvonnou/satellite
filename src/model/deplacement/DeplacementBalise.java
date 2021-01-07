package model.deplacement;

import events.satellitemoved.SatelitteMoved;
import model.Balise;
import model.ElementMobile;

public class DeplacementBalise implements Deplacement
{
  protected Deplacement deplacementSuivant;

  public DeplacementBalise(Deplacement deplacementSuivant)
  {
    this.deplacementSuivant = deplacementSuivant;
  }

  @Override
  public void bouge(ElementMobile cible)
  {
    this.bouge(cible);
  }

  // FIXME : Trouver alternative.
  public void whenSatelitteMoved(SatelitteMoved arg, Balise target)
  {}

}
