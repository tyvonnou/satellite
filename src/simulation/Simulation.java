package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import graphiclayer.GBounded;
import graphiclayer.GRect;
import graphiclayer.GSpace;
import model.Balise;
import model.BaliseCollecte;
import model.Memoire;
import model.Satelitte;
import model.deplacement.Deplacement;
import model.deplacement.DeplacementHorizontal;
import model.deplacement.DeplacementSatellite;
import model.deplacement.DeplacementVertical;
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

  public void addBalise(GBounded sea, Memoire memoire, Point startPos, Deplacement depl, int vitesse)
  {
    Balise balise = new Balise(memoire, vitesse);
    balise.setPosition(startPos);
    balise.setDeplacementCollecte(depl);
    balise.setProfondeurCollecte(startPos.y);
    balise.setEtat(new BaliseCollecte(balise));
    manager.addBalise(balise);
    GrBalise grbal = new GrBalise();
    grbal.setModel(balise);
    sea.addElement(grbal);
  }

  public void addSatelitte(GBounded sky, Point startPos, int vitesse)
  {
    Satelitte sat = new Satelitte(vitesse);
    sat.setPosition(startPos);
    sat.setDeplacement(new DeplacementSatellite(-10, 1000));
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
    this.addBalise(sea, new Memoire(100), new Point(400, 200), new DeplacementHorizontal(50, 750), 5);
    this.addBalise(sea, new Memoire(400), new Point(100, 100), new DeplacementVertical(50, 200), 5);
    this.addBalise(sea, new Memoire(200), new Point(0, 160), new DeplacementHorizontal(0, 800), 5);
    this.addBalise(sea, new Memoire(500), new Point(200, 100), new DeplacementVertical(130, 270), 5);
    this.addBalise(sea, new Memoire(150), new Point(300, 100), new DeplacementHorizontal(200, 600), 5);
    this.world.open();
    this.mainLoop();
  }

  public static void main(String[] args)
  {
    new Simulation().launch();
  }

}
