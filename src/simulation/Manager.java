package simulation;

import java.util.ArrayList;

import events.satellitebouge.SatelitteBouge;
import model.Satellite;
import model.balise.Balise;
import model.strategie.SynchronisationBalise;

public class Manager
{
  ArrayList<Satellite> satellites = new ArrayList<>();
  ArrayList<Balise> balises = new ArrayList<>();

  public void addBalise(Balise balise)
  {
    balises.add(balise);
    balise.setManager(this);
  }

  public void addSatellite(Satellite satellite)
  {
    this.satellites.add(satellite);
    satellite.setManager(this);
  }

  public void tick()
  {
    for (Balise balise : this.balises)
    {
      balise.tick();
    }
    for (Satellite satellite : this.satellites)
    {
      satellite.tick();
    }
  }

  public void baliseReadyForSynchro(SynchronisationBalise synchroBalise)
  {
    for (Satellite satellite : this.satellites)
    {
      satellite.registerListener(SatelitteBouge.class, synchroBalise);
    }
  }

  public void baliseSynchroDone(SynchronisationBalise synchroBalise)
  {
    for (Satellite satellite : this.satellites)
    {
      satellite.unregisterListener(SatelitteBouge.class, synchroBalise);
    }
  }

}
