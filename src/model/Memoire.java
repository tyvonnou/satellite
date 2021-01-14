package model;

import events.AbstractEvent;
import events.Emitter;
import events.EventHandler;
import events.memoirechange.MemoireChange;

public class Memoire implements Emitter
{
  int tailleMemoire;
  int remplissageMemoire;
  EventHandler eventHandler;

  public Memoire(int tailleMemoire)
  {
    this.tailleMemoire = tailleMemoire;
    this.remplissageMemoire = 0;
    this.eventHandler = new EventHandler();
  }

  /**
   * Sauvegarde les données envoyé en paramètre à la mémoire.<br>
   * Si la mémoire est déjà pleine, ne fait rien.
   * 
   * @param donnees
   *          Nombre de données à sauvegarder.
   */
  public void ajoutDonnees(int donnees)
  {
    if (this.memoirePleine())
      return;

    this.remplissageMemoire += donnees;
    if (this.remplissageMemoire > this.tailleMemoire)
    {
      this.remplissageMemoire = this.tailleMemoire;
    }
    this.send(new MemoireChange(this));
  }

  /**
   * Enlève les données envoyé en paramètre de la mémoire.<br>
   * Si la mémoire est déjà vide, ne fait rien.
   * 
   * @param donnees
   *          Nombre de données à enlever.
   */
  public void enleveDonnees(int donnees)
  {
    if (this.remplissageMemoire == 0)
      return;

    this.remplissageMemoire -= donnees;
    if (this.remplissageMemoire < 0)
    {
      this.remplissageMemoire = 0;
    }
    this.send(new MemoireChange(this));
  }

  /**
   * Vide la mémoire actuelle en la remettant à 0.
   */
  public void resetMemoire()
  {
    this.remplissageMemoire = 0;
    this.send(new MemoireChange(this));
  }

  /**
   * Retourne <code>True</code> si la mémoire est pleine, <code>False</code> sinon.
   */
  public boolean memoirePleine()
  {
    return (this.remplissageMemoire == this.tailleMemoire);
  }

  /**
   * Retourne <code>True</code> si la mémoire est vide, <code>False</code> sinon.
   */
  public boolean memoireVide()
  {
    return (this.remplissageMemoire == 0);
  }

  // ================================================================================
  // Evenements
  // ================================================================================

  @Override
  public void send(AbstractEvent event)
  {
    this.eventHandler.send(event);
  }

  @Override
  public void registerListener(Class<? extends AbstractEvent> whichEventType, Object listener)
  {
    this.eventHandler.registerListener(whichEventType, listener);
  }

  @Override
  public void unregisterListener(Class<? extends AbstractEvent> whichEventType, Object listener)
  {
    this.eventHandler.unregisterListener(whichEventType, listener);
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public int getTailleMemoire()
  {
    return tailleMemoire;
  }

  public int getRemplissageMemoire()
  {
    return remplissageMemoire;
  }
}
