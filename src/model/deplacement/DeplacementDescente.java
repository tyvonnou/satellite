package model.deplacement;

import java.awt.Point;

import model.ElementMobile;

public class DeplacementDescente extends DeplacementBalise
{
  private static final int VITESSE_DESCENTE = 3;

  int profondeur;
  boolean profondeurAtteinte;

  public DeplacementDescente(int profondeur)
  {
    this.profondeur = profondeur;
    this.profondeurAtteinte = false;
  }

  public boolean profondeurAtteinte()
  {
    return this.profondeurAtteinte;
  }

  @Override
  public void bouge(ElementMobile cible)
  {
    if (!this.profondeurAtteinte())
    {
      Point p = cible.getPosition();
      int y = p.y;

      if (y < this.profondeur)
      {
        y += VITESSE_DESCENTE;
        if (y > this.profondeur)
        {
          y = this.profondeur;
          this.profondeurAtteinte = true;
        }

        cible.setPosition(new Point(p.x, y));
      }
    }
  }
}
