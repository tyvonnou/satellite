package model.strategie.deplacement;

import java.awt.Point;

import model.ElementMobile;

public class DeplacementVertical extends DeplacementBalise
{
  Integer debut;
  Integer fin;
  boolean monte = false;

  public DeplacementVertical(Integer debut, Integer fin)
  {
    this.debut = debut;
    this.fin = fin;
  }

  @Override
  public void bouge(ElementMobile cible)
  {
    Point position = cible.getPosition();
    int y = position.y;

    if (this.monte)
    {
      y -= cible.getVitesse();
      if (y <= this.debut)
      {
        y = this.debut;
        this.monte = false;
      }
    }
    else
    {
      y += cible.getVitesse();
      if (y >= this.fin)
      {
        y = this.fin;
        this.monte = true;
      }
    }

    cible.setPosition(new Point(position.x, y));
  }
}
