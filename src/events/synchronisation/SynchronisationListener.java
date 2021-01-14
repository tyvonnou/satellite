package events.synchronisation;

public interface SynchronisationListener
{
  /**
   * Evenement lancé lorsqu'une synchronisation commence.
   * 
   * @param event
   *          Evenement.
   */
  public void onSynchronisationStart(Synchronisation event);

  /**
   * Evenement lancé lorsqu'une synchronisation s'arrête.
   * 
   * @param event
   *          Evenement.
   */
  public void onSynchronisationStop(Synchronisation event);
}
