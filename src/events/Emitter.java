package events;

public interface Emitter
{
  public void unregisterListener(Class<? extends AbstractEvent> eventType, Object listener);

  public void registerListener(Class<? extends AbstractEvent> eventType, Object listener);

  public void send(AbstractEvent event);
}
