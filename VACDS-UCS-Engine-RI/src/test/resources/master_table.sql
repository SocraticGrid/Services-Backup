--SET SCHEMA VACDS;

-- ADDRESS TABLE
CREATE TABLE address
(
	address_id varchar(36) NOT NULL
	, address_type varchar(32) NOT NULL
	, address varchar(512)
	, service_id varchar(64)
	, name varchar(128)
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_address PRIMARY KEY (address_id)
);

-- DELIVERY ADDRESS TABLE
CREATE TABLE delivery_address
(
	delivery_address_id varchar(36) NOT NULL
	, address_id varchar(36) REFERENCES address (address_id)
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_delivery_address PRIMARY KEY (delivery_address_id)
);


-- MESSAGE_BODY TABLE
CREATE TABLE message_body
(
	message_body_id varchar(36) NOT NULL
	, content clob
	, tag varchar(64)
	, type varchar(32)
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_message_body PRIMARY KEY (message_body_id)
);


-- MESSAGE HEADER TABLE
CREATE TABLE message_header
(
	message_header_id varchar(36) NOT NULL
	, message_id varchar(36) NOT NULL
	, message_type varchar(32) NOT NULL
	, subject varchar(1024) 
	, related_conversation_id integer
	, create_date timestamp NOT NULL
	, last_modified_date timestamp
	, delivery_guarantee varchar(32)
	, dynamics varchar(32)
	, priority smallint NOT NULL
	, receipt_notification smallint NOT NULL
	, retain_fully_in_log smallint NOT NULL
	, timeout smallint 
	, properties varchar(1024)
	, related_message_id varchar(36)
	, reply_to_user_contact_info_id varchar(36)
	, conversation_id varchar(36)
	, alert_status varchar(32)
	, service_response_address varchar(512)
	, request_expires timestamp
	, reply_allowed smallint
	, deleted smallint DEFAULT 0
	, CONSTRAINT ix_message_header_message_id UNIQUE (message_id)
	, CONSTRAINT pk_message_header PRIMARY KEY (message_header_id)
);


-- USER CONTACT INFO TABLE
CREATE TABLE user_contact_info
(
	user_contact_info_id varchar(36) NOT NULL
	, name varchar(32) NOT NULL
	, endpoint varchar(512)
	, preferred_address_id varchar(36) REFERENCES address (address_id)
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_user_contact_info_id PRIMARY KEY (user_contact_info_id)
);


-- RECIPIENT TABLE
CREATE TABLE recipient
(
	recipient_id varchar(36) NOT NULL
	, delivery_address_id varchar(36) NOT NULL REFERENCES delivery_address (delivery_address_id)
	, delivery_receipt smallint
	, read_receipt smallint
	, role varchar(32) NOT NULL
	, visibility varchar(32)
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_recipient PRIMARY KEY (recipient_id)
);


-- DELIVERY STATUS TABLE
CREATE TABLE delivery_status
(
	delivery_status_id varchar(36) NOT NULL
	, recipient_id varchar(36) NOT NULL REFERENCES recipient (recipient_id)
	, delivery_address_id varchar(36) NOT NULL REFERENCES delivery_address (delivery_address_id)
	, status varchar(64)
	, action varchar(64)
	, time_stamp timestamp
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_delivery_status PRIMARY KEY (delivery_status_id)
);


-- CONVERSATION CHANNEL
CREATE TABLE conversation_channel
(
	conversation_channel_id varchar(36) NOT NULL
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_conversation_channel PRIMARY KEY (conversation_channel_id)
);

-- CONVERSATION
CREATE TABLE conversation
(
	conversation_id varchar(36) NOT NULL
	, owner_id varchar(36) NOT NULL REFERENCES user_contact_info (user_contact_info_id)
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_conversation PRIMARY KEY (conversation_id)
);

-- PROCESSING EXCEPTION
CREATE TABLE processing_exception
(
	processing_exception_id varchar(36) NOT NULL
	, fault varchar(2048)
	, generating_message_id varchar(36)
	, issuing_service varchar(512)
	, exception_type varchar(128) NOT NULL
	, type_specific_context varchar(512)
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_processing_exception PRIMARY KEY (processing_exception_id)
);

-- MESSAGE_HEADER_JOIN_DELIVERY_STATUS TABLE
CREATE TABLE message_header_join_delivery_status
(
	message_header_id varchar(36) NOT NULL
	, delivery_status_id varchar(36) NOT NULL
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_message_header_join_delivery_status
	PRIMARY KEY (message_header_id,delivery_status_id)
);
ALTER TABLE message_header_join_delivery_status
	ADD CONSTRAINT fk_message_header_message_header_join_delivery_status
		FOREIGN KEY (message_header_id)
		REFERENCES message_header (message_header_id);

ALTER TABLE message_header_join_delivery_status
	ADD CONSTRAINT fk_delivery_status_message_header_join_delivery_status
		FOREIGN KEY (delivery_status_id)
		REFERENCES delivery_status (delivery_status_id);


-- MESSAGE_HEADER_JOIN_RECIPIENT TABLE
CREATE TABLE message_header_join_recipient
(
	message_header_id varchar(36) NOT NULL
	, recipient_id varchar(36) NOT NULL
	, recipient_type varchar(24) NOT NULL
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_message_header_join_recipient
	PRIMARY KEY (message_header_id,recipient_id,recipient_type)
);
ALTER TABLE message_header_join_recipient
	ADD CONSTRAINT fk_message_header_message_header_join_recipient
		FOREIGN KEY (message_header_id)
		REFERENCES message_header (message_header_id);

ALTER TABLE message_header_join_recipient
	ADD CONSTRAINT fk_recipient_message_header_join_recipient
		FOREIGN KEY (recipient_id)
		REFERENCES recipient (recipient_id);


-- MESSAGE_JOIN_PROCESSING_EXCEPTION
CREATE TABLE message_join_processing_exception
(
	message_id varchar(36) NOT NULL
	, processing_exception_id varchar(36) NOT NULL
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_message_join_processing_exception
	PRIMARY KEY (message_id,processing_exception_id)
);
ALTER TABLE message_join_processing_exception
	ADD CONSTRAINT fk_message_message_join_processing_exception
		FOREIGN KEY (message_id)
		REFERENCES message_header (message_id);
ALTER TABLE message_join_processing_exception
	ADD CONSTRAINT fk_processing_exception_message_join_processing_exception
		FOREIGN KEY (processing_exception_id)
		REFERENCES processing_exception (processing_exception_id);


-- MESSAGE_JOIN_MESSAGE_BODY TABLE
CREATE TABLE message_join_message_body
(
	message_id varchar(36) NOT NULL
	, message_body_id varchar(36) NOT NULL
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_message_join_message_body
	PRIMARY KEY (message_id,message_body_id)
);
ALTER TABLE message_join_message_body
	ADD CONSTRAINT fk_message_id_message_join_message_body
		FOREIGN KEY (message_id)
		REFERENCES message_header (message_id);

ALTER TABLE message_join_message_body
	ADD CONSTRAINT fk_message_body_id_message_join_message_body
		FOREIGN KEY (message_body_id)
		REFERENCES message_body (message_body_id);



-- USER_CONTACT_INFO_JOIN_ADDRESS
CREATE TABLE user_contact_info_join_address
(
	user_contact_info_id varchar(36) NOT NULL
	, address_id varchar(36) NOT NULL
	, type varchar(128)
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_user_contact_info_join_address
	PRIMARY KEY (user_contact_info_id,address_id)
);
ALTER TABLE user_contact_info_join_address
	ADD CONSTRAINT fk_user_contact_info_user_contact_info_join_address
		FOREIGN KEY (user_contact_info_id)
		REFERENCES user_contact_info (user_contact_info_id);
ALTER TABLE user_contact_info_join_address
	ADD CONSTRAINT fk_address_user_contact_info_join_address
		FOREIGN KEY (address_id)
		REFERENCES address (address_id);



-- CONVERSATION_JOIN_CONVERSATION_CHANNEL
CREATE TABLE conversation_join_conversation_channel
(
	conversation_id varchar(36) NOT NULL
	, conversation_channel_id varchar(36) NOT NULL
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_conversation_join_conversation_channel
	PRIMARY KEY (conversation_id,conversation_channel_id)
);
ALTER TABLE conversation_join_conversation_channel
	ADD CONSTRAINT fk_conversation_conversation_join_conversation_channel
		FOREIGN KEY (conversation_id)
		REFERENCES conversation (conversation_id);
ALTER TABLE conversation_join_conversation_channel
	ADD CONSTRAINT fk_conversation_channel_conversation_join_conversation_channel
		FOREIGN KEY (conversation_channel_id)
		REFERENCES conversation_channel (conversation_channel_id);



-- CONVERSATION_JOIN_PARTICIPANT
CREATE TABLE conversation_join_participant
(
	conversation_id varchar(36) NOT NULL
	, delivery_address_id varchar(36) NOT NULL
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_conversation_join_participant
	PRIMARY KEY (conversation_id,delivery_address_id)
);
ALTER TABLE conversation_join_participant
	ADD CONSTRAINT fk_conversation_conversation_join_participant
		FOREIGN KEY (conversation_id)
		REFERENCES conversation (conversation_id);
ALTER TABLE conversation_join_participant
	ADD CONSTRAINT fk_delivery_address_conversation_join_participant
		FOREIGN KEY (delivery_address_id)
		REFERENCES delivery_address (delivery_address_id);



-- MESSAGE_HEADER_ALERT_STATUS_JOIN_DELIVERY_ADDRESS TABLE
CREATE TABLE message_header_alert_status_join_delivery_address
(
	message_header_id varchar(36) NOT NULL
	, delivery_address_id varchar(36) NOT NULL
	, alert_status varchar(32)
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_message_header_alert_status_join_delivery_address
	PRIMARY KEY (message_header_id,delivery_address_id)
);
ALTER TABLE message_header_alert_status_join_delivery_address
	ADD CONSTRAINT fk_message_header_id_message_header_id_message_header_alert_status_join_delivery_address
		FOREIGN KEY (message_header_id)
		REFERENCES message_header (message_header_id);
ALTER TABLE message_header_alert_status_join_delivery_address
	ADD CONSTRAINT fk_delivery_address_id_message_header_alert_status_join_delivery_address
		FOREIGN KEY (delivery_address_id)
		REFERENCES delivery_address (delivery_address_id);
		
		
-- MESSAGE_HEADER_JOIN_SENDER TABLE 
CREATE TABLE message_header_join_sender
(
	message_header_id varchar(36) NOT NULL
	, delivery_address_id varchar(36) NOT NULL
	, deleted smallint DEFAULT 0
	, CONSTRAINT pk_message_header_sender_join_delivery_address
	PRIMARY KEY (message_header_id,delivery_address_id)
);
ALTER TABLE message_header_join_sender
	ADD CONSTRAINT fk_message_header_id_message_header_id_message_header_sender_join_delivery_address
		FOREIGN KEY (message_header_id)
		REFERENCES message_header (message_header_id);
ALTER TABLE message_header_join_sender
	ADD CONSTRAINT fk_delivery_address_id_message_header_sender_join_delivery_address
		FOREIGN KEY (delivery_address_id)
		REFERENCES delivery_address (delivery_address_id);


