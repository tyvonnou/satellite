package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import graphiclayer.GBounded;
import graphiclayer.GRect;
import graphiclayer.GSpace;
import model.Balise;
import model.Satelitte;
import model.deplacement.DeplSatellite;
import model.deplacement.DeplVertical;
import model.deplacement.Deplacement;
import model.deplacement.DeplacementHorizontal;
import simulation.graphic.GrBalise;
import simulation.graphic.GrSatelitte;

public class Simulation
{

  Manager manager = new Manager();
  GSpace world = new GSpace("Satellite & Balises", new Dimension(800, 600));

  public void mainLoop()
  {
    while (true)
    {
      manager.tick();
      try
      {
        Thread.sleep(50);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  public void addBalise(GBounded sea, int memorySize, Point startPos, Deplacement depl)
  {
    Balise bal = new Balise(memorySize);
    bal.setPosition(startPos);
    bal.setDeplacement(depl);
    manager.addBalise(bal);
    GrBalise grbal = new GrBalise();
    grbal.setModel(bal);
    sea.addElement(grbal);
  }

  public void addSatelitte(GBounded sky, Point startPos, int vitesse)
  {
    Satelitte sat = new Satelitte();
    sat.setPosition(startPos);
    sat.setDeplacement(new DeplSatellite(-10, 1000, vitesse));
    manager.addSatellite(sat);
    GrSatelitte grSat = new GrSatelitte();
    grSat.setModel(sat);
    sky.addElement(grSat);
  }

  public void launch()
  {
    GRect sky = new GRect();
    sky.setColor(Color.WHITE);
    sky.setDimension(new Dimension(800, 300));
    GRect sea = new GRect();
    sea.setColor(Color.blue);
    sea.setDimension(new Dimension(800, 300));
    sea.setPosition(new Point(0, 300));
    this.world.addElement(sky);
    this.world.addElement(sea);
    this.addSatelitte(sky, new Point(10, 50), 2);
    this.addSatelitte(sky, new Point(100, 10), 1);
    this.addSatelitte(sky, new Point(400, 90), 3);
    this.addSatelitte(sky, new Point(500, 140), 4);
    this.addSatelitte(sky, new Point(600, 10), 1);
    this.addBalise(sea, 300, new Point(400, 200), new DeplacementHorizontal(50, 750));
    this.addBalise(sea, 400, new Point(100, 100), new DeplVertical(50, 200));
    this.addBalise(sea, 200, new Point(0, 160), new DeplacementHorizontal(0, 800));
    this.addBalise(sea, 500, new Point(200, 100), new DeplVertical(130, 270));
    this.addBalise(sea, 150, new Point(300, 100), new DeplacementHorizontal(200, 600));
    this.world.open();
    this.mainLoop();
  }

  public static void main(String[] args)
  {
    new Simulation().launch();
  }

}
