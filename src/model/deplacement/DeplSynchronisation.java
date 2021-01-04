package model.deplacement;

import events.satellitemoved.SatelitteMoved;
import events.synchroevent.SynchroEvent;
import model.Balise;
import model.ElementMobile;
import model.Satelitte;

public class DeplSynchronisation extends DeplacementBalise
{
  private int synchroTime;
  private Satelitte synchro;

  public Boolean synchroStarted()
  {
    return this.synchro != null;
  }

  public DeplSynchronisation(Deplacement next)
  {
    super(next);
    this.synchroTime = 10;
    this.synchro = null;
  }

  @Override
  public void whenSatelitteMoved(SatelitteMoved arg, Balise target)
  {
    if (this.synchro != null)
      return;
    Satelitte sat = (Satelitte) arg.getSource();
    int satX = sat.getPosition().x;
    int tarX = target.getPosition().x;
    if (satX > tarX - 10 && satX < tarX + 10)
    {
      this.synchro = sat;
      target.send(new SynchroEvent(this));
      this.synchro.send(new SynchroEvent(this));
    }
  }

  @Override
  public void bouge(ElementMobile cible)
  {
    // La cible est forcément une balise.
    Balise balise = (Balise) cible;

    if (this.synchro == null)
      return;
    this.synchroTime--;
    if (synchroTime <= 0)
    {
      Satelitte sat = this.synchro;
      this.synchro = null;
      this.synchroTime = 10;
      balise.send(new SynchroEvent(this));
      sat.send(new SynchroEvent(this));
      balise.getManager().baliseSynchroDone(balise);
      balise.setDeplacement(deplacementSuivant);
    }
  }
}
