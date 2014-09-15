                                                                           
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
                                                                           
public class Receiver
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
                                                                          
       // create a queue receiver
       QueueReceiver queueReceiver = queueSession.createReceiver(queue);
                                                                          
       // start the connection
       queueConn.start();
                                                                          
       // receive a message
       TextMessage message = (TextMessage) queueReceiver.receive();
                                                                          
       // print the message
       System.out.println("received: " + message.getText());
                                                                          
       // close the queue connection
       queueConn.close();
    }
}