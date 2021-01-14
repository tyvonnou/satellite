package events.satellitebouge;

import events.AbstractEvent;

public class SatelitteBouge extends AbstractEvent
{
  private static final long serialVersionUID = 480096146703824993L;

  public SatelitteBouge(Object source)
  {
    super(source);
  }

  @Override
  public void runOn(Object cible)
  {
    SatelitteBougeListener listener = (SatelitteBougeListener) cible;
    listener.onSatelitteBouge(this);
  }
}
