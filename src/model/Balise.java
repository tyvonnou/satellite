package model;

import events.satellitemoved.SatelitteMovedListener;
import model.deplacement.DeplSynchronisation;
import model.deplacement.Deplacement;
import model.deplacement.DeplacementBalise;
import model.deplacement.MonteSurfacePourSynchro;
import model.deplacement.Redescendre;
import events.satellitemoved.SatelitteMoved;

public class Balise extends ElementMobile implements SatelitteMovedListener{
	
	public Balise(int memorySize) {
		super(memorySize);
	}
	
	public int profondeur() { 
		return this.getPosition().y; 
	}
	
	protected void readSensors() {
		this.dataSize++;
	}
	
	public void tick() {
		this.readSensors();
		if (this.memoryFull()) {
			Deplacement redescendre = new Redescendre(this.deplacement(), this.profondeur());
			Deplacement deplSynchro = new DeplSynchronisation(redescendre);
			Deplacement nextDepl = new MonteSurfacePourSynchro(deplSynchro);
			this.setDeplacement(nextDepl);
			this.resetData();
		} 
		super.tick();
	}

	@Override
	public void whenSatelitteMoved(SatelitteMoved arg) {
		DeplacementBalise dp = (DeplacementBalise) this.depl;
		dp.whenSatelitteMoved(arg, this);
	}


}
