package simulation.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import events.memoirechange.MemoireChange;
import events.memoirechange.MemoireChangeListener;
import graphiclayer.GRect;
import graphiclayer.GString;
import model.Memoire;

public class GrMemoire extends GRect implements MemoireChangeListener
{

  private static final int FULL_SIZE_BAR = 60;

  GRect loadingBar;
  GString percentage;

  public GrMemoire()
  {
    this.setColor(Color.BLACK);
    this.setDimension(new Dimension(FULL_SIZE_BAR, 10));
    this.setPosition(new Point(0, 45));

    this.initLoadingBar();
    this.initPercentage();
  }

  private void initLoadingBar()
  {
    this.loadingBar = new GRect();
    this.loadingBar.setColor(new Color(0, 128, 0));
    this.loadingBar.setDimension(new Dimension(20, 10));

    this.addElement(loadingBar);
  }

  private void initPercentage()
  {
    String percent = "0%";
    this.percentage = new GString();
    this.percentage.setColor(Color.WHITE);
    this.percentage.setFont(new Font("Arial", Font.BOLD, 12));
    this.percentage.setString(percent);
    this.adaptPercentagePosition(percent);

    this.addElement(percentage);
  }

  private void adaptPercentagePosition(String percent)
  {
    this.percentage.setPosition(new Point(27 - (percent.length() * 2), -1));
  }

  // ================================================================================
  // Evenements
  // ================================================================================

  @Override
  public void onMemoireChange(MemoireChange event)
  {
    Memoire memoire = (Memoire) event.getSource();
    Double tailleBarre = FULL_SIZE_BAR / (double) memoire.getTailleMemoire() * memoire.getRemplissageMemoire();
    String percent = Math.round(((double) memoire.getRemplissageMemoire() / memoire.getTailleMemoire() * 100)) + "%";

    this.loadingBar.setDimension(new Dimension(tailleBarre.intValue(), 10));

    this.percentage.setString(percent);
    this.adaptPercentagePosition(percent);
  }

  @Override
  public void onMemoirePleine(MemoireChange event)
  {
    this.loadingBar.setColor(Color.RED);
    this.onMemoireChange(event);
  }

  @Override
  public void onMemoireVide(MemoireChange event)
  {
    this.loadingBar.setColor(new Color(0, 128, 0));
    this.onMemoireChange(event);
  }
}
