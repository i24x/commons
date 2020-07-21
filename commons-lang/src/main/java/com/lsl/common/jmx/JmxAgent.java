package com.lsl.common.jmx;

import com.lsl.common.util.ThreadUtils;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.*;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.util.Arrays;

/**
 * -Djava.rmi.server.hostname=127.0.0.1 -Dcom.sun.management.jmxremote.port=1000
 * -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false
 */
public class JmxAgent {

    public static void main(String[] args) throws JMException, Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("serverInfoMBean:name=serverInfo");
        server.registerMBean(new ServerInfo(), name);

        // 远端注册
        LocateRegistry.createRegistry(8081);

        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:8081/jmxrmi");
        JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, server);
        jcs.start();

        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mBeanServerConnection = jmxc.getMBeanServerConnection();
        // domains
        String[] domains = mBeanServerConnection.getDomains();
        System.out.println(Arrays.toString(domains));

        ThreadUtils.block();
    }
}
