package model.strategie.deplacement;

import java.awt.Point;

import model.ElementMobile;

public class DeplacementHorizontal extends DeplacementBalise
{
  Integer debut;
  Integer fin;
  boolean debutVersFin = true;

  public DeplacementHorizontal(Integer start, Integer fin)
  {
    this.debut = start;
    this.fin = fin;
  }

  @Override
  public void bouge(ElementMobile cible)
  {
    Point position = cible.getPosition();
    int x = position.x;

    if (this.debutVersFin)
    {
      x += cible.getVitesse();
      if (x >= fin)
      {
        x = this.fin;
        this.debutVersFin = false;
      }
    }
    else
    {
      x -= cible.getVitesse();
      if (x <= this.debut)
      {
        x = this.debut;
        debutVersFin = true;
      }
    }

    cible.setPosition(new Point(x, position.y));
  }
}
