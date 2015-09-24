/**
 * 
 */
package org.socraticgrid.hl7.services.uc.model;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.socraticgrid.hl7.services.uc.db.UCSDataConnection;
import org.socraticgrid.hl7.services.uc.db.UCSDataService;
import org.socraticgrid.hl7.services.uc.db.dto.DTOMessageType;
import org.socraticgrid.hl7.services.uc.db.dto.UCSDto;
import org.socraticgrid.hl7.services.uc.internal.idgenerators.TimeBasedIdGenerator;

/**
 * @author steven
 * @created Jan 23, 2014
 * 
 */

// So the Address can be inserted then selected then updated...ISUD w/o the D
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UCSDataService_MessageBody_TestIT {

	static MessageBody testMessageBody = new MessageBody();
	
	@BeforeClass
	public static void initAddress() {

		System.out.println("\n\n****************************");
		System.out.println("************* RUNNING - UCSDataService_MessageBody_TestIT");
		System.out.println("****************************\n\n");

		testMessageBody.setMessageBodyId(new TimeBasedIdGenerator().getNewId());
		testMessageBody.setMessageId(new TimeBasedIdGenerator().getNewId());
		URL url = Thread.currentThread().getContextClassLoader().getResource("Test-MessageBody.txt");
		File largeContent = new File(url.getFile());
		try(FileReader fileReader = new FileReader(largeContent); BufferedReader reader = new BufferedReader(fileReader);) {
			String line; StringBuffer buf = new StringBuffer();
			while((line = reader.readLine()) != null) {
				buf.append(line);
			}
			testMessageBody.setContent(buf.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void removeAddressFromDB() {
		String id = testMessageBody.getMessageBodyId();
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			Connection  con = sqlSession.getConnection();
			Statement stmt = con.createStatement(); 
			stmt.execute("DELETE FROM message_body WHERE message_body_id = '"+id+"'");
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	
	@Test //Insert the Address
	public void test1() {
		UCSDto<MessageBody> dto = new UCSDto<>();
		dto.getListT().add(testMessageBody);
		UCSDataService.insertMessageBody(dto);
		
		StringBuffer failures = new StringBuffer();
		for(DTOMessageType msgType : dto.getMsgs().keySet()){
			if(msgType.equals(DTOMessageType.ERROR) || msgType.equals(DTOMessageType.WARN)){
				failures.append(dto.getMsgs().get(msgType));
			}
		}
		if(failures.length()!=0){
			fail(failures.toString());
		}
	}
	
	
	@Test //Select the Address
	public void test2() {
		UCSDto<MessageBody> dto = new UCSDto<>();
		dto.getAdHocParams().put("messageBodyId",testMessageBody.getMessageBodyId());
		UCSDataService.selectMessageBodyById(dto);

		StringBuffer failures = new StringBuffer();
		for(DTOMessageType msgType : dto.getMsgs().keySet()){
			if(msgType.equals(DTOMessageType.ERROR) || msgType.equals(DTOMessageType.WARN)){
				failures.append(dto.getMsgs().get(msgType));
			}
		}
		if(failures.length()!=0){
			fail(failures.toString());
		}
		else{
			assertNotNull(dto.getListT().get(0));
		}
	}

	
	
	@Test //Update the MessageBody
	public void test3() {
		String type = "text/plain";
		String content = "new content";
		String messageId = new TimeBasedIdGenerator().getNewId();
		String tag = "new Tag";
		
		testMessageBody.setType(type);
		testMessageBody.setContent(content);
		testMessageBody.setMessageId(messageId);
		testMessageBody.setTag(tag);
		
		
		
		UCSDto<MessageBody> dto = new UCSDto<>();
		dto.getListT().add(testMessageBody);
		UCSDataService.updateMessageBody(dto);
		StringBuffer failures = new StringBuffer();
		for(DTOMessageType msgType : dto.getMsgs().keySet()){
			if(msgType.equals(DTOMessageType.ERROR) || msgType.equals(DTOMessageType.WARN)){
				failures.append(dto.getMsgs().get(msgType));
			}
		}
		if(failures.length()!=0){
			fail(failures.toString());
		}
		
		dto = new UCSDto<>();
		dto.getAdHocParams().put("messageBodyId",testMessageBody.getMessageBodyId());
		UCSDataService.selectMessageBodyById(dto);
		failures = new StringBuffer();
		for(DTOMessageType msgType : dto.getMsgs().keySet()){
			if(msgType.equals(DTOMessageType.ERROR) || msgType.equals(DTOMessageType.WARN)){
				failures.append(dto.getMsgs().get(msgType));
			}
		}
		if(failures.length()!=0){
			fail(failures.toString());
		}
		
		MessageBody messageBody = dto.getListT().get(0);
		assertTrue(type.equals(messageBody.getType()));
		assertTrue(content.equals(messageBody.getContent()));
		assertTrue(tag.equals(messageBody.getTag()));

	}

}
