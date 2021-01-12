package simulation;

import java.util.ArrayList;

import events.satellitemoved.SatelitteMoved;
import model.Balise;
import model.Satelitte;
import model.deplacement.SynchronisationBalise;

public class Manager
{
  ArrayList<Satelitte> sats = new ArrayList<Satelitte>();
  ArrayList<Balise> bals = new ArrayList<Balise>();

  public void addBalise(Balise bal)
  {
    bals.add(bal);
    bal.setManager(this);
  }

  public void addSatellite(Satelitte sat)
  {
    this.sats.add(sat);
    sat.setManager(this);
  }

  public void tick()
  {
    for (Balise b : this.bals)
    {
      b.tick();
    }
    for (Satelitte s : this.sats)
    {
      s.tick();
    }
  }

  public void baliseReadyForSynchro(SynchronisationBalise b)
  {
    for (Satelitte s : this.sats)
    {
      s.registerListener(SatelitteMoved.class, b);
    }
  }

  public void baliseSynchroDone(SynchronisationBalise b)
  {
    for (Satelitte s : this.sats)
    {
      s.unregisterListener(SatelitteMoved.class, b);
    }
  }

}
