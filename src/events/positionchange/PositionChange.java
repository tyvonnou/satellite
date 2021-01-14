package events.positionchange;

import events.AbstractEvent;

public class PositionChange extends AbstractEvent
{
  private static final long serialVersionUID = 480096146703824993L;

  public PositionChange(Object source)
  {
    super(source);
  }

  @Override
  public void runOn(Object cible)
  {
    PositionChangeListener listener = (PositionChangeListener) cible;
    listener.onPositionChange(this);
  }
}
