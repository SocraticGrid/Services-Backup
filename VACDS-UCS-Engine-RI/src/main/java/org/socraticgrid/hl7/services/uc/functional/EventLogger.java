package org.socraticgrid.hl7.services.uc.functional;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.logging.DetailedLogEntry;
import org.socraticgrid.hl7.services.uc.logging.EventLevel;
import org.socraticgrid.hl7.services.uc.logging.LogEntry;
import org.socraticgrid.hl7.services.uc.logging.LogEntryGroup;
import org.socraticgrid.hl7.services.uc.logging.LogEntryMappings;
import org.socraticgrid.hl7.services.uc.logging.LogEntryType;
import org.socraticgrid.hl7.services.uc.logging.SummaryLogEntry;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;

public class EventLogger {
	private final Logger logger = LoggerFactory.getLogger(EventLogger.class);

	private static Map<LogEntryGroup, EventLevel> mapActiveEventLevels;

	static {
		mapActiveEventLevels = new HashMap<LogEntryGroup, EventLevel>();
		mapActiveEventLevels.put(LogEntryGroup.Authenication, EventLevel.all);
		mapActiveEventLevels.put(LogEntryGroup.Exception, EventLevel.all);
		mapActiveEventLevels.put(LogEntryGroup.Reply, EventLevel.all);
		mapActiveEventLevels.put(LogEntryGroup.Delivery, EventLevel.all);
		mapActiveEventLevels.put(LogEntryGroup.Conversation, EventLevel.all);
		mapActiveEventLevels.put(LogEntryGroup.Update, EventLevel.all);
		mapActiveEventLevels.put(LogEntryGroup.View, EventLevel.all);
		mapActiveEventLevels.put(LogEntryGroup.Operational, EventLevel.all);
		mapActiveEventLevels.put(LogEntryGroup.Action, EventLevel.all);
		mapActiveEventLevels.put(LogEntryGroup.Diagnostic, EventLevel.all);
		mapActiveEventLevels.put(LogEntryGroup.Send, EventLevel.all);
		mapActiveEventLevels.put(LogEntryGroup.Presence, EventLevel.all);
	}

	private  EventLevel findGroupLoggingLevel(LogEntryType entry) {
		LogEntryGroup grp = LogEntryMappings.getLogEntryGroup(entry);

		if ((grp != null) && (mapActiveEventLevels.containsKey(grp))) {
			return mapActiveEventLevels.get(grp);
		} else {
			logger.trace("Defaulting to degug event level for "+entry.toString());
			return EventLevel.debug;
		}
	}

	public boolean isLoggable(LogEntryType entry, EventLevel level) {
		EventLevel grpLvl = findGroupLoggingLevel(entry);
		int grpVal = grpLvl.getNumVal();
		int eventVal = level.getNumVal();
	
		logger.trace("Is "+level+"("+eventVal+") >= "+grpLvl+"("+grpVal+")");
		
		boolean willLog = level.getNumVal() >= (findGroupLoggingLevel(entry)
				.getNumVal());
		
		if (willLog == false) {
			logger.trace("Rejecting logging at Level " + level+" for "+entry);
		}
		return willLog;
	}

	private int eventBufferSize = 100;

	private CircularFifoQueue<LogEntry> eventQueue;

	public EventLogger() {

	}

	/**
	 * @return the eventBufferSize
	 */
	public int getEventBufferSize() {
		return eventBufferSize;
	}

	/**
	 * @return the eventQueue
	 */
	public CircularFifoQueue<LogEntry> getEventQueue() {
		return eventQueue;
	}

	@PostConstruct
	protected void initialize() {
		eventQueue = new CircularFifoQueue<LogEntry>(eventBufferSize);
	}

	public void logDetailedEvent(DetailedLogEntry entry) {
		if (isLoggable(entry.getType(), entry.getEventLevel())) {

			reportToSystemLog(entry);

			saveEntry(entry);
		}
	}

	public void logDetailedEvent(LogEntryType type, EventLevel level,
			String context, String event, String text) {
		if (isLoggable(type, level)) {
			DetailedLogEntry entry = new DetailedLogEntry();
			entry.setEvent(event);
			entry.setEventLevel(level);
			entry.setEventText(text);
			entry.setType(type);

			reportToSystemLog(entry);

			saveEntry(entry);
		}

	}

	public void logDetailedEvent(LogEntryType type, EventLevel level,
			String context, String event, String text, Message msg) {
		if (isLoggable(type, level)) {
			DetailedLogEntry entry = new DetailedLogEntry();
			entry.setEvent(event);
			entry.setEventLevel(level);
			entry.setEventText(text);
			entry.setType(type);
			entry.setMessage(msg);
			reportToSystemLog(entry);

			saveEntry(entry);
		}

	}

	public <T extends Message> void logDetailedEvent(LogEntryType type,
			EventLevel level, String context, String event, String text,
			MessageModel<T> msg) {
		if (isLoggable(type, level)) {
			DetailedLogEntry entry = new DetailedLogEntry();
			entry.setEvent(event);
			entry.setEventLevel(level);
			entry.setEventText(text);
			entry.setType(type);
			entry.setMessage(msg.getMessageType());

			reportToSystemLog(entry);

			saveEntry(entry);
		}

	}

	public void logExceptionEvent(String context, Exception exp) {
		if (isLoggable(LogEntryType.System_InternalError, EventLevel.error)) {
			SummaryLogEntry entry = new SummaryLogEntry();
			entry.setEvent("Exception");
			entry.setContext(context);
			entry.setEventLevel(EventLevel.error);
			entry.setEventText(exp.getMessage());
			entry.setType(LogEntryType.System_InternalError);
			reportToSystemLog(entry, exp);

			saveEntry(entry);
		}
	}

	public void logExceptionEvent(String context, Exception exp, Message msg) {
		if (isLoggable(LogEntryType.System_InternalError, EventLevel.debug)) {
			SummaryLogEntry entry = new SummaryLogEntry();
			entry.setEvent("Exception");
			entry.setContext(context);
			entry.setEventLevel(EventLevel.error);
			entry.setEventText(exp.getMessage());
			entry.setType(LogEntryType.System_InternalError);
			entry.setMessageId(msg.getHeader().getMessageId());
			reportToSystemLog(entry, exp);

			saveEntry(entry);
		}
	}

	public <T extends Message> void logExceptionEvent(String context,
			Exception exp, MessageModel<T> msg) {
		if (isLoggable(LogEntryType.System_InternalError, EventLevel.debug)) {
			SummaryLogEntry entry = new SummaryLogEntry();
			entry.setEvent("Exception");
			entry.setContext(context);
			entry.setEventLevel(EventLevel.error);
			entry.setEventText(exp.getMessage());
			entry.setType(LogEntryType.System_InternalError);
			entry.setMessageId(msg.getMessageType().getHeader().getMessageId());
			reportToSystemLog(entry, exp);

			saveEntry(entry);
		}
	}

	public void logSummaryEvent(DetailedLogEntry detailedEntry) {
		if (isLoggable(detailedEntry.getType(), detailedEntry.getEventLevel())) {
			SummaryLogEntry entry = new SummaryLogEntry();
			entry.setEvent(detailedEntry.getEvent());
			entry.setEventLevel(detailedEntry.getEventLevel());
			entry.setEventText(detailedEntry.getEventText());
			entry.setType(detailedEntry.getType());
			reportToSystemLog(entry);

			saveEntry(entry);
		}

	}

	public void logSummaryEvent(LogEntry entry) {
		if (isLoggable(entry.getType(), entry.getEventLevel())) {

			reportToSystemLog(entry);

			saveEntry(entry);
		}

	}

	public void logSummaryEvent(LogEntryType type, EventLevel level,
			String context, String event, String text) {
		if (isLoggable(type, level)) {
			SummaryLogEntry entry = new SummaryLogEntry();
			entry.setEvent(event);
			entry.setEventLevel(level);
			entry.setEventText(text);
			entry.setType(type);

			reportToSystemLog(entry);

			saveEntry(entry);
		}
	}

	public void logSummaryEvent(LogEntryType type, EventLevel level,
			String context, String event, String text, Message msg) {
		if (isLoggable(type, level)) {

			SummaryLogEntry entry = new SummaryLogEntry();
			entry.setEvent(event);
			entry.setEventLevel(level);
			entry.setEventText(text);
			entry.setType(type);
			entry.setMessageId(msg.getHeader().getMessageId());
			reportToSystemLog(entry);

			saveEntry(entry);

		}
	}

	public <T extends Message> void logSummaryEvent(LogEntryType type,
			EventLevel level, String context, String event, String text,
			MessageModel<T> msg) {
		if (isLoggable(type, level)) {
			SummaryLogEntry entry = new SummaryLogEntry();
			entry.setEvent(event);
			entry.setEventLevel(level);
			entry.setEventText(text);
			entry.setType(type);
			entry.setMessageId(msg.getMessageType().getHeader().getMessageId());

			reportToSystemLog(entry);

			saveEntry(entry);
		}
	}

	public void logSummaryEvent(SummaryLogEntry entry) {
		if (isLoggable(entry.getType(), entry.getEventLevel())) {

			reportToSystemLog(entry);

			saveEntry(entry);
		}

	}

	public void logUserExceptionEvent(String context, Exception exp) {
		if (isLoggable(LogEntryType.User_ExceptionUsingService, EventLevel.warn)) {
			SummaryLogEntry entry = new SummaryLogEntry();
			entry.setEvent("Exception");
			entry.setContext(context);
			entry.setEventLevel(EventLevel.warn);
			entry.setEventText(exp.getMessage());
			entry.setType(LogEntryType.User_ExceptionUsingService);

			reportToSystemLog(entry, exp);

			saveEntry(entry);
		}
	}

	public void logUserExceptionEvent(String context, Exception exp, Message msg) {
		if (isLoggable(LogEntryType.User_ExceptionUsingService,
				EventLevel.debug)) {
			SummaryLogEntry entry = new SummaryLogEntry();
			entry.setEvent("Exception");
			entry.setContext(context);
			entry.setEventLevel(EventLevel.warn);
			entry.setEventText(exp.getMessage());
			entry.setType(LogEntryType.User_ExceptionUsingService);

			reportToSystemLog(entry, exp);

			saveEntry(entry);
		}
	}

	public <T extends Message> void logUserExceptionEvent(
			String context, Exception exp, MessageModel<T> msg) {
		if (isLoggable(LogEntryType.User_ExceptionUsingService, EventLevel.warn)) {
			SummaryLogEntry entry = new SummaryLogEntry();
			entry.setEvent("Exception");
			entry.setContext(context);
			entry.setEventLevel(EventLevel.warn);
			entry.setEventText(exp.getMessage());
			entry.setType(LogEntryType.User_ExceptionUsingService);
			entry.setMessageId(msg.getMessageType().getHeader().getMessageId());
			reportToSystemLog(entry, exp);

			saveEntry(entry);
		}
	}

	private void logToSLF4J(EventLevel level, String msg) {
		switch (level) {

		case debug: {
			logger.debug(msg);
			break;
		}
		case info: {
			logger.info(msg);
			break;
		}
		case error: {
			logger.error(msg);
			break;
		}
		case warn: {
			logger.warn(msg);
			break;
		}
		default: {
			break;
		}
		}
	}

	private void logToSLF4J(EventLevel level, String msg, Throwable exp) {

		switch (level) {

		case debug: {
			logger.debug(msg, exp);
			break;
		}
		case info: {
			logger.info(msg, exp);
			break;
		}
		case error: {
			logger.error(msg, exp);
			break;
		}
		case warn: {
			logger.warn(msg, exp);
			break;
		}
		case trace: {
			logger.trace(msg, exp);
			break;
		}
		default: {
			logger.trace("Escaped: " + msg, exp);
			break;
		}
		}
	}

	private void reportToSystemLog(DetailedLogEntry ent) {

		logToSLF4J(ent.getEventLevel(),
				String.format("%s - %s", ent.getEvent(), ent.getEventText()));

		// getLogger().info(String.format("DE: %s - %s",
		// ent.getEvent(),ent.getEventText()));
	}

	private void reportToSystemLog(LogEntry ent) {
		logToSLF4J(ent.getEventLevel(),
				String.format("%s - %s", ent.getEvent(), ent.getEventText()));
		// getLogger().info(String.format("LE: %s - %s",
		// ent.getEvent(),ent.getEventText()));
	}

	private void reportToSystemLog(LogEntry ent, Throwable exp) {
		logToSLF4J(ent.getEventLevel(),
				String.format("%s - %s", ent.getEvent(), ent.getEventText()),
				exp);

		// getLogger().info(String.format("EE: %s - %s",
		// ent.getEvent(),ent.getEventText()));
	}

	private void reportToSystemLog(SummaryLogEntry ent) {

		logToSLF4J(ent.getEventLevel(),
				String.format("%s - %s", ent.getEvent(), ent.getEventText()));

		// getLogger().info(String.format("SE: %s - %s",
		// ent.getEvent(),ent.getEventText()));
	}

	private void saveEntry(LogEntry ent) {
		// TODO Save the entry

		// Grab Apache Commons and Get A Circular Buffer for now....
		eventQueue.add(ent);
	}

	/**
	 * @param eventBufferSize
	 *            the eventBufferSize to set
	 */
	public void setEventBufferSize(int eventBufferSize) {

		if (this.eventQueue != null
				&& (this.eventBufferSize != eventBufferSize)) {
			// Resize the buffer as required
			CircularFifoQueue<LogEntry> newQueue = new CircularFifoQueue<LogEntry>(
					eventBufferSize);
			// Move old entries to the new Buffer
			newQueue.addAll(eventQueue);
			eventQueue.clear();
			eventQueue = newQueue;
		}
		this.eventBufferSize = eventBufferSize;
	}

}
