package events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class EventHandler {
	
	Map<Class<? extends AbstractEvent>,Set<Object>> registry = new HashMap<Class<? extends AbstractEvent>,Set<Object>>();
	
  public void send (AbstractEvent event) {
    Set<Object> objects = registry.get(event.getClass());
    if (objects == null) return;
    Object[] objectsArray = objects.toArray();
    for(int i = 0; i < objectsArray.length; i++){
      if(objectsArray[i] != null){
        event.runOn(objectsArray[i]);
      }
    }
  }

	public void registerListener(Class<? extends AbstractEvent> whichEventType, Object listener) {
		Set<Object> set = registry.get(whichEventType);
		if (set == null) {
			set = new HashSet<Object>();
			registry.put(whichEventType, set);
		}
		set.add(listener);
	}
	public void unregisterListener(Class<? extends AbstractEvent> whichEventType, Object listener) {
		Set<Object> set = registry.get(whichEventType);
		if (set == null) return;
		set.remove(listener);
	}

}
