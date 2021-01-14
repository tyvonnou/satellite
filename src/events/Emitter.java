package events;

/**
 * Interface permettant l'envoi d'évènements.
 */
public interface Emitter
{
  public void send(AbstractEvent event);

  public void registerListener(Class<? extends AbstractEvent> eventType, Object listener);

  public void unregisterListener(Class<? extends AbstractEvent> eventType, Object listener);
}
