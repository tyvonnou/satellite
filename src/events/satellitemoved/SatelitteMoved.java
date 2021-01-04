package events.satellitemoved;

import events.AbstractEvent;

public class SatelitteMoved extends AbstractEvent {
	private static final long serialVersionUID = 480096146703824993L;

	public SatelitteMoved(Object source) {
		super(source);
	}

	public void runOn(Object target) {
		SatelitteMovedListener listener = (SatelitteMovedListener) target;
		listener.whenSatelitteMoved(this);
	}
}
