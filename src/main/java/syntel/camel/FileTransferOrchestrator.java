package main.java.syntel.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileTransferOrchestrator {
	
	public static void main(String ...args) {
		System.out.println("Hello....");
		final CamelContext context = new DefaultCamelContext();
		try {
			context.addRoutes(new RouteBuilder() {

				@Override
				public void configure() throws Exception {
					// TODO Auto-generated method stub
					 
					from("ftp://192.168.56.1:21/test.txt").
		            to("file:C:\\input\\");
	                
				}

				}
				
			);
		
		context.start();
		System.out.println("Transfer was successfull....");
		Thread.sleep(5000);
		context.stop();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	
	}
	
	public static RouteBuilder createRouteBuilder() throws Exception {
	    return new RouteBuilder() {
	        public void configure() throws Exception {
	            // we use a delay of 60 minutes (eg. once pr. hour we poll the FTP server
	            //long delay = 3600000;
	        	
	        	onException(Exception.class).log("Sonthing Failed");
	        	
	        	System.out.println("Configuring the route!!");
	            
	            
	 
	            // from the given FTP server we poll (= download) all the files
	            // from the public/reports folder as BINARY types and store this as files
	            // in a local directory. Camel will use the filenames from the FTPServer
	 
	            // notice that the FTPConsumer properties must be prefixed with "consumer." in the URL
	            // the delay parameter is from the FileConsumer component so we should use consumer.delay as
	            // the URI parameter name. The FTP Component is an extension of the File Component.
	           /* from("ftp://RAHUL85/").
	                    to("file:C\\camel");*/
	            
	            from("file:C:\\input\\checking.txt?noop=true").
                to("file:C:\\output");
	        }
	    };
	}

}
