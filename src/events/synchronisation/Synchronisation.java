package events.synchronisation;

import events.AbstractEvent;
import model.strategie.SynchronisationBalise;

public class Synchronisation extends AbstractEvent
{
  private static final long serialVersionUID = 480096146703824993L;

  public Synchronisation(Object source)
  {
    super(source);
  }

  @Override
  public void runOn(Object cible)
  {
    SynchronisationListener listener = (SynchronisationListener) cible;
    SynchronisationBalise synchro = (SynchronisationBalise) this.getSource();

    if (synchro.synchronisationStarted())
      listener.onSynchronisationStart(this);
    else
      listener.onSynchronisationStop(this);
  }
}
