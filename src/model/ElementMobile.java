package model;

import java.awt.Point;

import events.AbstractEvent;
import events.EventHandler;
import events.positionchanged.PositionChanged;
import model.deplacement.Deplacement;
import simulation.Manager;

public class ElementMobile
{
  Deplacement deplacement;
  Point position;
  Integer vitesse;

  EventHandler eventHandler;
  Manager manager;

  public ElementMobile(int vitesse)
  {
    this.position = new Point(0, 0);
    this.vitesse = vitesse;
    this.eventHandler = new EventHandler();
  }

  public void tick()
  {
    this.bouge();
  }

  public void bouge()
  {
    this.deplacement.bouge(this);
    this.send(new PositionChanged(this));
  }

  // ================================================================================
  // Evenements
  // ================================================================================

  public void registerListener(Class<? extends AbstractEvent> whichEventType, Object listener)
  {
    eventHandler.registerListener(whichEventType, listener);
  }

  public void unregisterListener(Class<? extends AbstractEvent> whichEventType, Object listener)
  {
    eventHandler.unregisterListener(whichEventType, listener);
  }

  public void send(AbstractEvent event)
  {
    eventHandler.send(event);
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public Deplacement getDeplacement()
  {
    return this.deplacement;
  }

  public void setDeplacement(Deplacement deplacement)
  {
    this.deplacement = deplacement;
  }

  public Point getPosition()
  {
    return this.position;
  }

  public void setPosition(Point position)
  {
    this.position = position;
  }

  public Integer getVitesse()
  {
    return vitesse;
  }

  public void setVitesse(Integer vitesse)
  {
    this.vitesse = vitesse;
  }

  public Manager getManager()
  {
    return manager;
  }

  public void setManager(Manager manager)
  {
    this.manager = manager;
  }
}
