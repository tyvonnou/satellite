package events.memoirechange;

public interface MemoireChangeListener
{
  /**
   * Evenement lancé lorsque la mémoire subit un changement.
   * 
   * @param event
   *          Evenement.
   */
  public void onMemoireChange(MemoireChange event);

  /**
   * Evenement lancé lorsque la mémoire est pleine.
   * 
   * @param event
   *          Evenement.
   */
  public void onMemoirePleine(MemoireChange event);

  /**
   * Evenement lancé lorsque la mémoire est vide.
   * 
   * @param event
   *          Evenement.
   */
  public void onMemoireVide(MemoireChange event);
}
