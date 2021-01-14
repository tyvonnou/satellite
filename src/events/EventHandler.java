package events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class EventHandler implements Emitter
{

  Map<Class<? extends AbstractEvent>, Set<Object>> registry;

  public EventHandler()
  {
    this.registry = new HashMap<>();
  }

  @Override
  public void send(AbstractEvent event)
  {
    Set<Object> listeners = registry.get(event.getClass());
    if (listeners != null)
    {
      Iterator<Object> listenersItor = listeners.iterator();
      while (listenersItor.hasNext())
      {
        event.runOn(listenersItor.next());
      }
    }
  }

  @Override
  public void registerListener(Class<? extends AbstractEvent> eventType, Object listener)
  {
    Set<Object> set = registry.computeIfAbsent(eventType, k -> new HashSet<>());
    set.add(listener);
  }

  @Override
  public void unregisterListener(Class<? extends AbstractEvent> eventType, Object listener)
  {
    Set<Object> set = registry.get(eventType);
    if (set == null)
      return;
    set.remove(listener);
  }
}
