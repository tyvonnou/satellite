package model.strategie.deplacement;

import java.awt.Point;

import model.ElementMobile;

public class DeplacementDescente extends DeplacementBalise
{
  private static final int VITESSE_DESCENTE = 3;

  int profondeur;

  public DeplacementDescente(int profondeur)
  {
    this.profondeur = profondeur;
  }

  @Override
  public void bouge(ElementMobile cible)
  {
    if (super.isTermine())
      return;

    Point p = cible.getPosition();
    int y = p.y;

    if (y < this.profondeur)
    {
      y += VITESSE_DESCENTE;
      if (y >= this.profondeur)
      {
        y = this.profondeur;
        super.setTermine(true);
      }
      cible.setPosition(new Point(p.x, y));
    }
  }

}
