package model.strategie;

import events.satellitebouge.SatelitteBouge;
import events.satellitebouge.SatelitteBougeListener;
import events.synchronisation.Synchronisation;
import model.Memoire;
import model.Satellite;
import model.balise.Balise;

/**
 * Stratégie de synchronisation entre une balise et un satellite.<br>
 * Lorsqu'une balise trouve un satellite accessible pour effectuer une synchronisation, se synchronise avec lui.
 */
public class SynchronisationBalise extends Strategie implements SatelitteBougeListener
{
  private static final int DEBIT = 10;
  private static final int RAYON_DETECTION = 30;

  Balise balise;
  Satellite satelliteCible;

  public SynchronisationBalise(Balise balise)
  {
    balise.getManager().baliseReadyForSynchro(this);
    this.balise = balise;
  }

  /**
   * Transmission des données entre la balise et le satellite lié.
   */
  public void synchronisation()
  {
    if (!this.synchronisationStarted())
      return;

    Memoire memoireBalise = balise.getMemoire();
    memoireBalise.enleveDonnees(DEBIT);

    if (memoireBalise.getRemplissageMemoire() == 0)
    {
      this.stopSynchronisation();
      this.termine = true;
    }
  }

  /**
   * Lance la synchronisation.
   */
  private void startSynchronisation()
  {
    this.balise.send(new Synchronisation(this));
    this.satelliteCible.send(new Synchronisation(this));
  }

  /**
   * Stop la synchronisation.
   */
  private void stopSynchronisation()
  {
    Satellite satellite = this.satelliteCible;
    this.satelliteCible = null;

    this.balise.send(new Synchronisation(this));
    satellite.send(new Synchronisation(this));

    this.balise.getManager().baliseSynchroDone(this);
  }

  /**
   * Retourne <code>True</code> si la synchronisation avec un satellite a commencé, <code>False</code> sinon.
   */
  public boolean synchronisationStarted()
  {
    return this.satelliteCible != null;
  }

  /**
   * Retourne <code>True</code> si le satellite en paramètre est accessible, <code>False</code> sinon.
   */
  private boolean satelliteAccessible(Satellite satellite)
  {
    int satelliteX = satellite.getPosition().x;
    int baliseX = this.balise.getPosition().x;

    return satelliteX > baliseX - RAYON_DETECTION && satelliteX < baliseX + RAYON_DETECTION;
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  @Override
  public void onSatelitteBouge(SatelitteBouge event)
  {
    if (this.synchronisationStarted())
      return;

    Satellite satellite = (Satellite) event.getSource();
    if (this.satelliteAccessible(satellite))
    {
      this.satelliteCible = satellite;
      this.startSynchronisation();
    }
  }
}
