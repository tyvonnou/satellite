package model.strategie.deplacement;

import java.awt.Point;

import model.ElementMobile;

public class DeplacementSatellite implements Deplacement
{
  Integer debut;
  Integer fin;

  public DeplacementSatellite(Integer debut, Integer fin)
  {
    this.debut = debut;
    this.fin = fin;
  }

  @Override
  public void bouge(ElementMobile cible)
  {
    Point position = cible.getPosition();
    int x = position.x;

    x += cible.getVitesse();
    if (x >= this.fin)
    {
      x = debut;
    }

    cible.setPosition(new Point(x, position.y));
  }
}
