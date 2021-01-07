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

  EventHandler eventHandler;
  Manager manager;

  public ElementMobile()
  {
    eventHandler = new EventHandler();
    this.position = new Point(0, 0);
  }

  public Deplacement deplacement()
  {
    return deplacement;
  }

  public void setManager(Manager manager)
  {
    this.manager = manager;

  }

  // enregistrement des listeners
  public void registerListener(Class<? extends AbstractEvent> whichEventType, Object listener)
  {
    eventHandler.registerListener(whichEventType, listener);
  }

  public void unregisterListener(Class<? extends AbstractEvent> whichEventType, Object listener)
  {
    eventHandler.unregisterListener(whichEventType, listener);
  }

  // envoi des evenements
  public void send(AbstractEvent event)
  {
    eventHandler.send(event);
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

  public void setPosition(Point position)
  {
    if (this.position.equals(position))
      return;
    this.position = position;
  }

  public Point getPosition()
  {
    return position;
  }

  public void setDeplacement(Deplacement depl)
  {
    this.deplacement = depl;
  }

  public Manager getManager()
  {
    return manager;
  }

}
