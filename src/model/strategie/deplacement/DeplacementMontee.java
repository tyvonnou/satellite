package model.strategie.deplacement;

import java.awt.Point;

import model.ElementMobile;

public class DeplacementMontee extends DeplacementBalise
{
  private static final int VITESSE_MONTEE = 3;

  @Override
  public void bouge(ElementMobile cible)
  {
    if (super.isTermine())
      return;

    Point p = cible.getPosition();
    int y = p.y;

    if (y > 0)
    {
      y -= VITESSE_MONTEE;
      if (y <= 0)
      {
        y = 0;
        super.setTermine(true);
      }
      cible.setPosition(new Point(p.x, y));
    }
  }
}
