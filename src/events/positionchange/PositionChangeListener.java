package events.positionchange;

public interface PositionChangeListener
{
  /**
   * Evenement lancé lorsque la position d'un élément mobile change.
   * 
   * @param event
   *          Evenement.
   */
  public void onPositionChange(PositionChange event);
}
