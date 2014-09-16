import java.util.Enumeration;
                                                                           
import javax.naming.InitialContext;
                                                                           
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Message;
import javax.jms.QueueSession;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
                                                                           
public class Browser
{
    public static void main(String[] args) throws Exception
    {
       // get the initial context
    	 InitialContext ctx = (InitialContext) QueueConsumer.getInitialContext();
                                                                          
       // lookup the queue object
       Queue queue = (Queue) ctx.lookup("queue/testQueue");
                                                                          
       // lookup the queue connection factory
       QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.
           lookup("ConnectionFactory");
                                                                          
       // create a queue connection
       QueueConnection queueConn = connFactory.createQueueConnection();
                                                                          
       // create a queue session
       QueueSession queueSession = queueConn.createQueueSession(false,
           Session.AUTO_ACKNOWLEDGE);
                                                                          
       // create a queue browser
       QueueBrowser queueBrowser = queueSession.createBrowser(queue);
                                                                          
       // start the connection
       queueConn.start();
                                                                          
       // browse the messages
       Enumeration e = queueBrowser.getEnumeration();
       int numMsgs = 0;
                                                                          
       // count number of messages
       while (e.hasMoreElements()) {
          Message message = (Message) e.nextElement();
          numMsgs++;
       }
                                                                          
       System.out.println(queue + " has " + numMsgs + " messages");
                                                                          
       // close the queue connection
       queueConn.close();
    }
}