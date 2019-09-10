package vn.topica.itlab4.homework_9.exercise2;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;



public class ClientHandler extends Thread{
	private DataInputStream dis;
	private DataOutputStream dos;
	private Socket socket;
	private Status st;
	private User user;
	public ClientHandler(DataInputStream dis, DataOutputStream dos, Socket socket) {
		super();
		this.dis = dis;
		this.dos = dos;
		this.socket = socket;
		this.st=Status.INIT;
		this.user=new User();
	}
	@Override
	public void run() {
		while(true) {
			try {
				Process process=new Process();
				Convert c=new Convert();
				String regex="098[2-9][0-9]{6}";
				int length=dis.readInt();
			    byte[] messageByte = new byte[length];
			    dis.readFully(messageByte, 0, messageByte.length);//read byte array from client
			    
			    Message m = c.convertByteArrayToMessage(messageByte);//convert byte array to object Message
			    boolean match=false;
			    Short cmdCode=m.getCmdCode();//get cmd Code
				//Exercise 2.1: check format of phone number
			    for(Content cont: m.getListTVL()) {
			    	if(cont.getTags()==1) {
			    		if(cont.getValue().matches(regex)) {
			    			match=true;
			    			break;
			    		}else {
			    			process.createErrMessage(dos);
			    		}
			    	}
			    }
			    if(match) {
				    switch(cmdCode) {
				    //Exercise 2.2: Process message has cmdCode is AUTHEN		    
				    case 0: {
			    	   	if(process.processAuthen(m)) {
			    	   		process.createResponseMessage((short)0,dos, m,"OK");//send response message
					    	this.st=Status.READY;
				    	}else {
				    		process.createErrMessage(dos);//send response error message
				    	}
			    	   	break;
					 }
				    //Exercise 2.3: Process message has cmdCode is INSERT
				    case 1: {
				    	if(this.st==Status.READY) {
				    		this.user=process.addUser(m, this.user);//read message INSERT and add user
				    		process.createResponseMessage((short)1,dos, m, "OK");//send response message
					    	
					    }else {
					    	process.createResponseMessage((short)1,dos, m, "NOK");//send response message
					    }
				    	break;
				    }
				    //Exercise 2.3: Process message has cmdCode is COMMIT		    
				    case 2: {
				    	if(this.st==Status.READY) {
				    		process.createResponseMessage((short)2,dos, m, "OK");//send response message
				    		this.st=Status.SELECT;
					    }else {
					    	process.createResponseMessage((short)2,dos, m, "NOK");//send response message
					    }
				    	break;
				    }
				    //Exercise 2.4: Process message has cmdCode is SELECT
				    case 3: {
				    	if(this.st==Status.SELECT) {
				    		process.createResponseMessage((short)3,dos, m, "OK", user.getName());//send response message
				    	}else {
				    		process.createResponseMessage((short)3,dos, m, "NOK");//send response message
				    	}
				    	break;
				    }
				    default: break;
				    }
			    }
			    dos.flush();
			    
			} catch (EOFException e) {
				
			} catch (SocketException e) {
                if (e.toString().contains("Socket closed") || e.toString().contains("Connection reset")
                        || e.toString().contains("Broken pipe")) {
                } else {
                    stop();
                }	
			} catch (IOException e) {
		
				e.printStackTrace();
			}
			
		}

	}

}
