package model;

import events.satellitemoved.SatelitteMoved;

public class Satelitte extends ElementMobile
{

  public Satelitte(int vitesse)
  {
    super(vitesse);
  }

  @Override
  public void bouge()
  {
    super.bouge();
    this.send(new SatelitteMoved(this));
  }
}
