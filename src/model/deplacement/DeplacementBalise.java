package model.deplacement;

import events.satellitemoved.SatelitteMoved;
import model.Balise;
import model.ElementMobile;

public class DeplacementBalise implements Deplacement
{

  @Override
  public void bouge(ElementMobile cible)
  {
    this.bouge(cible);
  }

  // FIXME : Trouver alternative.
  public void whenSatelitteMoved(SatelitteMoved arg, Balise target)
  {}

}
