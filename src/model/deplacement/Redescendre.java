package model.deplacement;

import java.awt.Point;

import model.ElementMobile;

public class Redescendre extends DeplacementBalise
{
  int profondeur;

  public Redescendre(Deplacement next, int profondeur)
  {
    super(next);
    this.profondeur = profondeur;
  }

  @Override
  public void bouge(ElementMobile target)
  {
    Point p = target.getPosition();
    int y = p.y;
    if (y < this.profondeur)
    {
      y += 3;
      if (y > this.profondeur)
        y = this.profondeur;
      target.setPosition(new Point(p.x, y));
    }
    else
    {
      target.setDeplacement(deplacementSuivant);
    }
  }

}
