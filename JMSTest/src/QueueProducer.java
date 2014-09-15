import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.NamingException;


public class QueueProducer {

		public static void main(String[] args) throws JMSException, NamingException {
			System.out.println("*** entering Queue Producer example *** ");
			Context context = QueueConsumer.getInitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory)context.lookup("ConnectionFactory");
			Queue queue = (Queue) context.lookup("queue/testQueue");
			QueueConnection connection = factory.createQueueConnection();
			QueueSession session = connection.createQueueSession(false,
					QueueSession.AUTO_ACKNOWLEDGE);			
			connection.start();
//		
			QueueProducer producer = new QueueProducer();
			producer.sendMsg(" Knok knok... messages arrived wao..testing ", session, queue);			
			System.out.println("*** ending Queue Producer example : msg send successfully *** ");
		}
	
		private void sendMsg(String text, QueueSession session, Queue queue) throws JMSException{
			QueueSender queueSender = session.createSender(queue);
			TextMessage msg = session.createTextMessage(text);
			queueSender.send(msg);
			queueSender.close();
			//return text;
			
		}
		
}
