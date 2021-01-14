package events.satellitebouge;

public interface SatelitteBougeListener
{
  /**
   * Evenement lancé lorsqu'un satellite bouge.
   * 
   * @param event
   *          Evenement.
   */
  public void onSatelitteBouge(SatelitteBouge event);
}
