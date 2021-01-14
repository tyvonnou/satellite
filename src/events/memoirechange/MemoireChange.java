package events.memoirechange;

import events.AbstractEvent;
import model.Memoire;

public class MemoireChange extends AbstractEvent
{
  private static final long serialVersionUID = 480096146703824993L;

  public MemoireChange(Object source)
  {
    super(source);
  }

  @Override
  public void runOn(Object cible)
  {
    MemoireChangeListener listener = (MemoireChangeListener) cible;
    Memoire memoire = (Memoire) this.getSource();

    if (memoire.memoirePleine())
    {
      listener.onMemoirePleine(this);
    }
    else if (memoire.memoireVide())
    {
      listener.onMemoireVide(this);
    }
    else
    {
      listener.onMemoireChange(this);
    }
  }
}
