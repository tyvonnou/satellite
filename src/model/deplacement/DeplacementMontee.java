package model.deplacement;

import java.awt.Point;

import model.ElementMobile;

public class DeplacementMontee extends DeplacementBalise
{
  private static final int VITESSE_MONTEE = 3;

  boolean surface;

  public DeplacementMontee()
  {
    this.surface = false;
  }

  public boolean surfaceAtteinte()
  {
    return this.surface;
  }

  @Override
  public void bouge(ElementMobile cible)
  {
    if (!this.surfaceAtteinte())
    {
      Point p = cible.getPosition();
      int y = p.y;

      if (y > 0)
      {
        y -= VITESSE_MONTEE;
        if (y < 0)
        {
          y = 0;
          this.surface = true;
        }

        cible.setPosition(new Point(p.x, y));
      }
    }
  }
}
