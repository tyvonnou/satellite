package model;

import events.satellitebouge.SatelitteBouge;

public class Satellite extends ElementMobile
{

  public Satellite(int vitesse)
  {
    super(vitesse);
  }

  @Override
  public void bouge()
  {
    super.bouge();
    this.send(new SatelitteBouge(this));
  }
}
