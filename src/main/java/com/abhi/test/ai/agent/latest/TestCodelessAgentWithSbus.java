package com.abhi.test.ai.agent.latest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.RetryPolicy;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;

public class TestCodelessAgentWithSbus {

	private static final String SBUS_QUEUE_NAME = "***";
	private static final String SBUS_NAME = "***";
	private static final String USERNAME = "RootManageSharedAccessKey";
	private static final String PASSWORD = "***";

	private static final Logger logger = LoggerFactory.getLogger(TestCodelessAgentWithSbus.class);

	public static void main(String[] args) throws Exception {

		TestCodelessAgentWithSbus test = new TestCodelessAgentWithSbus();

		test.sendMsgToSbus();
	}

	public void sendMsgToSbus() throws ServiceBusException, InterruptedException {
		logger.info("Start sending message to sbus");

		ConnectionStringBuilder connectionStringBuilder = new ConnectionStringBuilder(SBUS_NAME, SBUS_QUEUE_NAME,
				USERNAME, PASSWORD);
		// Setted no RETRY, for enabling Error in case of Failure
		connectionStringBuilder.setRetryPolicy(RetryPolicy.getNoRetry());

		com.microsoft.azure.servicebus.QueueClient queueClient = new com.microsoft.azure.servicebus.QueueClient(
				connectionStringBuilder, ReceiveMode.PEEKLOCK);

		com.microsoft.azure.servicebus.Message message = new Message("Hello World");

		queueClient.send(message);

		logger.info("Sent Message to queue '{}'", SBUS_QUEUE_NAME);

	}

}
