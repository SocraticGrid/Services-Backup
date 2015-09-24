SET SCHEMA UCOMM;

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