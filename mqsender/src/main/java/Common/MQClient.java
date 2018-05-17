package Common;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public abstract class MQClient {

    protected Properties properties = new Properties();
    protected String channel = null;
    protected String host = null;
    protected int port = 1414;
    protected String manager = null;
    protected String destination = null;
    protected boolean isTopic = false;
    protected Connection connection = null;
    protected Session session = null;
    protected Destination dest = null;
    protected boolean array = false;
    protected String bodyfile = null;
    protected String headfile = null;

    public MQClient() {
    }

    protected void run(String args[]) {
        init();
        try {
            createSession();
            doRun(args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSession();
        }
    }

    protected abstract void doRun(String args[]);


    protected void createSession() throws Exception {
        // Create a connection factory
        JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
        JmsConnectionFactory cf = ff.createConnectionFactory();

        // Set the properties
        cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, host);
        cf.setIntProperty(WMQConstants.WMQ_PORT, port);
        cf.setStringProperty(WMQConstants.WMQ_CHANNEL, channel);
        cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
        cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, manager);

        // Create JMS objects
        connection = cf.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        dest = isTopic ? session.createTopic(destination) : session.createQueue(destination);
    }

    protected void closeSession() {
        if (session != null)
            try {
                session.close();
            } catch (JMSException jmsex) {
                System.out.println("Session could not be closed.");
            }

        if (connection != null)
            try {
                connection.close();
            } catch (JMSException jmsex) {
                System.out.println("Connection could not be closed.");
            }
    }

    protected static void processJMSException(JMSException jmsex) {
        System.out.println(jmsex);
        Throwable innerException = jmsex.getLinkedException();
        if (innerException != null) {
            System.out.println("Inner exception(s):");
        }
        while (innerException != null) {
            System.out.println(innerException);
            innerException = innerException.getCause();
        }
        return;
    }

    protected void init() {
        try {
            properties.load(new InputStreamReader(this.getClass().getResourceAsStream("/" + this.getClass().getSimpleName() + ".properties")));
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not load properties from file " + this.getClass().getSimpleName() + ".properties.");
        }

        host = properties.getProperty("mq.host", "localhost");

        try {
            port = Integer.parseInt(properties.getProperty("mq.port", "1414"));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Port number must have an integer value.");
        }

        channel = properties.getProperty("mq.channel", "SYSTEM.DEF.SVRCONN");

        manager = properties.getProperty("mq.manager");
        if (manager == null || manager.equals(""))
            throw new IllegalArgumentException("Queue manager name is not specified.");

        destination = properties.getProperty("mq.destination");
        if (destination == null || destination.equals(""))
            throw new IllegalArgumentException("Destination name is not specified.");

        isTopic = destination.startsWith("topic://");

        String sarray = properties.getProperty("mq.send.as.text", "no");
        array = !("YES".equals(sarray.toUpperCase()) || "1".equals(sarray) || "ON".equals(sarray.toUpperCase()));

        bodyfile = properties.getProperty("mq.body.file", "received-${index}-body.bin");
        headfile = properties.getProperty("mq.head.file", "received-${index}-head.bin");
    }
}
