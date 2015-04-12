package br.com.onlinebuscas.util;

import java.io.PrintStream;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PhaseListenerSalina
  implements PhaseListener
{
  public void beforePhase(PhaseEvent fase)
  {
    System.out.println("Antes da fase: " + fase.getPhaseId());
    if (fase.getPhaseId().equals(PhaseId.RESTORE_VIEW))
    {
      Session session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      FacesContextUtil.setRequestSession(session);
    }
  }
  
  public void afterPhase(PhaseEvent fase)
  {
    System.out.println("Depois da fase: " + fase.getPhaseId());
    if (fase.getPhaseId().equals(PhaseId.RENDER_RESPONSE))
    {
      Session session = FacesContextUtil.getRequestSession();
      try
      {
        session.getTransaction().commit();
      }
      catch (Exception e)
      {
        if (session.getTransaction().isActive()) {
          session.getTransaction().rollback();
        }
      }
      finally
      {
        session.close();
      }
    }
  }
  
  public PhaseId getPhaseId()
  {
    return PhaseId.ANY_PHASE;
  }
}
