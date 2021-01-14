package simulation.graphic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

import events.positionchange.PositionChangeListener;
import events.positionchange.PositionChange;
import events.synchronisation.Synchronisation;
import events.synchronisation.SynchronisationListener;
import graphiclayer.GRect;
import model.ElementMobile;

public class GrElementMobile extends GRect implements PositionChangeListener, SynchronisationListener
{
  ElementMobile model;
  boolean duringSynchro = false;

  Object getModel()
  {
    return this.model;
  }

  public void setModel(ElementMobile model)
  {
    this.model = model;
    model.registerListener(PositionChange.class, this);
    model.registerListener(Synchronisation.class, this);
    this.setPosition(this.model.getPosition());
    this.repaint();
  }

  @Override
  public void onSynchronisationStart(Synchronisation arg)
  {
    duringSynchro = true;
  }

  @Override
  public void onSynchronisationStop(Synchronisation arg)
  {
    duringSynchro = false;
  }

  @Override
  public void onPositionChange(PositionChange arg)
  {
    this.setPosition(this.model.getPosition());
    this.repaint();
  }

  @Override
  public void draw(Graphics2D g)
  {
    super.draw(g);
    if (duringSynchro)
    {
      Color c = g.getColor();
      Stroke s = g.getStroke();
      Rectangle bounds = this.getBounds();
      g.setColor(Color.ORANGE);
      g.setStroke(new BasicStroke(2));
      for (int i = 10; i < 150; i += 25)
      {
        g.drawOval(bounds.x - i, bounds.y - i, bounds.width + i + i, bounds.height + i + i);
      }
      g.setStroke(s);
      g.setColor(c);
    }
  }

}