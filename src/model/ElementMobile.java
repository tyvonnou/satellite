package model;

import java.awt.Point;

import events.AbstractEvent;
import events.Emitter;
import events.EventHandler;
import events.positionchange.PositionChange;
import model.strategie.deplacement.Deplacement;
import simulation.Manager;

public class ElementMobile implements Emitter
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
    this.send(new PositionChange(this));
  }

  // ================================================================================
  // Evenements
  // ================================================================================

  @Override
  public void send(AbstractEvent event)
  {
    eventHandler.send(event);
  }

  @Override
  public void registerListener(Class<? extends AbstractEvent> eventType, Object listener)
  {
    eventHandler.registerListener(eventType, listener);
  }

  @Override
  public void unregisterListener(Class<? extends AbstractEvent> eventType, Object listener)
  {
    eventHandler.unregisterListener(eventType, listener);
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
