package simulation.graphic;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import events.memoirechange.MemoireChange;
import graphiclayer.GImage;
import model.ElementMobile;
import model.Memoire;
import model.balise.Balise;

public class GrBalise extends GrElementMobile
{
  GrMemoire grMemoire;

  public GrBalise()
  {
    this.grMemoire = new GrMemoire();
    File path = new File("balise.png");
    this.withoutBorder();
    this.withoutBackground();

    try
    {
      BufferedImage rawImage = ImageIO.read(path);
      this.addElement(new GImage(rawImage));
      this.addElement(this.grMemoire);
      this.setDimension(new Dimension(rawImage.getWidth(), rawImage.getHeight()));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void setModel(ElementMobile model)
  {
    super.setModel(model);
    Memoire memoire = ((Balise) model).getMemoire();
    memoire.registerListener(MemoireChange.class, this.grMemoire);
  }
}
