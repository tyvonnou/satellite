package model.strategie;

/**
 * Représente une action a effectuée. Indique lorsque l'action a été faite.
 */
public abstract class Strategie
{
  boolean termine = false;

  public boolean isTermine()
  {
    return this.termine;
  }

  public void setTermine(boolean termine)
  {
    this.termine = termine;
  }
}
