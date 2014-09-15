import java.util.Properties;

import javax.jms.*;

import javax.naming.*;

public class QueueConsumer implements MessageListener {

	

	public static void main(String[] args) throws JMSException, NamingException {
		System.out.println("*** entering Queue Consumer example *** ");
		Context context = QueueConsumer.getInitialContext();
		QueueConnectionFactory factory = (QueueConnectionFactory) (context)
				.lookup("ConnectionFactory");
		Queue queue = (Queue) context.lookup("queue/testQueue");
		QueueConnection connection = factory.createQueueConnection();
		QueueSession session = connection.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);
		QueueReceiver queueReceiver = session.createReceiver(queue);
		queueReceiver.setMessageListener(new QueueConsumer());		
		System.out.println("*** ending Queue Consumer example *** ");
		connection.start();
	}

	public void onMessage(Message msg) {
		try {
			System.out.println(" incomming msges : in QueueConsumer >>>> " +((TextMessage)msg).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public static Context getInitialContext() throws JMSException,
			NamingException {
		Properties prop = new Properties();
		prop.setProperty("java.naming.factory.initial",	"org.jnp.interfaces.NamingContextFactory");
		prop.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming");
		prop.setProperty("java.naming.provider.url", "localhost:1099");
		Context context = new InitialContext(prop);
		return context;
	}

}
