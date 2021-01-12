package model.deplacement;

import events.satellitemoved.SatelitteMoved;
import events.satellitemoved.SatelitteMovedListener;
import events.synchroevent.SynchroEvent;
import model.Balise;
import model.Memoire;
import model.Satelitte;

public class SynchronisationBalise implements SatelitteMovedListener
{
  private static final int DEBIT = 50;
  private static final int RAYON_DETECTION = 30;

  Balise balise;
  Satelitte satelliteCible;
  Integer donneesTransmises;

  boolean synchronisationTermine;

  public SynchronisationBalise(Balise balise)
  {
    balise.getManager().baliseReadyForSynchro(this);
    this.balise = balise;
    this.donneesTransmises = 0;
    this.synchronisationTermine = false;
  }

  public Boolean synchroStarted()
  {
    return this.satelliteCible != null;
  }

  public void synchronisation(Balise balise)
  {
    if (this.satelliteCible == null)
      return;

    Memoire memoireBalise = balise.getMemoire();
    this.donneesTransmises += DEBIT;
    if (this.donneesTransmises >= memoireBalise.getRemplissageMemoire())
    {
      Satelitte satellite = this.satelliteCible;
      this.satelliteCible = null;
      memoireBalise.resetMemoire();
      balise.send(new SynchroEvent(this));
      satellite.send(new SynchroEvent(this));
      balise.getManager().baliseSynchroDone(this);
      this.synchronisationTermine = true;
    }
  }

  private void detecteSatellite(Satelitte satellite)
  {
    int satelliteX = satellite.getPosition().x;
    int baliseX = this.balise.getPosition().x;

    if (satelliteX > baliseX - RAYON_DETECTION && satelliteX < baliseX + RAYON_DETECTION)
    {
      this.satelliteCible = satellite;

      this.balise.send(new SynchroEvent(this));
      this.satelliteCible.send(new SynchroEvent(this));
    }
  }

  public boolean synchronisationFini()
  {
    return this.synchronisationTermine;
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  @Override
  public void whenSatelitteMoved(SatelitteMoved event)
  {
    if (this.satelliteCible == null)
    {
      this.detecteSatellite((Satelitte) event.getSource());
    }
  }
}
