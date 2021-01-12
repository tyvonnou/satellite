package events.synchroevent;

import events.AbstractEvent;
import model.deplacement.SynchronisationBalise;

public class SynchroEvent extends AbstractEvent {
	private static final long serialVersionUID = 480096146703824993L;

	public SynchroEvent(Object source) {
		super(source);
	}

	public void runOn(Object target) {
		SynchroEventListener listener = (SynchroEventListener) target;
		SynchronisationBalise depl = (SynchronisationBalise) this.getSource();
		if (depl.synchroStarted())
			listener.whenStartSynchro(this);
		else 
			listener.whenStopSynchro(this);
	}
}
