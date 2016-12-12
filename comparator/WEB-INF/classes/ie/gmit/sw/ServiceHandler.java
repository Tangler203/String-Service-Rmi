package ie.gmit.sw;

import java.io.*;
import java.rmi.Naming;
import java.util.Map;
import java.util.concurrent.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class ServiceHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String remoteHost = null;
	private static long jobNumber = 0;
	private BlockingQueue<Job> jobqueue = new LinkedBlockingQueue<Job>();
	private static Map<Long, Resultator> outQueue = new ConcurrentHashMap<Long, Resultator>();
	Resultator r;

	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER"); //Reads the value from the <context-param> in web.xml
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//Initialise some request variables with the submitted form info. These are local to this method and thread safe...
		String algorithm = req.getParameter("cmbAlgorithm");
		String s = req.getParameter("txtS");
		String t = req.getParameter("txtT");
		String taskNumber = req.getParameter("frmTaskNumber");
		String result = null;


		out.print("<html><head><title>Distributed Systems Assignment</title>");		
		out.print("</head>");		
		out.print("<body>");
		
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			
			//Add job to in-queue
			Job j = new Job(s, t, algorithm, jobNumber);
			try {
				jobqueue.put(j);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			jobNumber++;
			try {
				work(j);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			//Check out-queue for finished job
			if(r.isProcessed() == true) {
				result = r.getResult();
			}
		}
		
		
		
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<div id=\"r\"></div>");
		
		out.print("<font color=\"#993333\"><b>");
		out.print("RMI Server is located at " + remoteHost);
		out.print("<br>Algorithm: " + algorithm);		
		out.print("<br>String <i>s</i> : " + s);
		out.print("<br>String <i>t</i> : " + t);
		out.print("</b></font>");

		if (result != null){
			out.print("<br><br><br>");
			out.print(result);
		}
		
		out.print("<script>");
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);");
		out.print("</script>");
				
		//You can use this method to implement the functionality of an RMI client
		
		//
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
	
	public void work(Job j) throws Exception{
		do {
			StringService strServ = (StringService) Naming.lookup("rmi://localhost:1099/stringService");
			
			jobqueue.poll();
			System.out.println(j.getS() + " " +j.getT() + " " + j.getAlgo());
			System.out.println("Help im " + new Compare(j.getS(), j.getT(), j.getAlgo(),new Result()));
			r = strServ.compare(j.getS(), j.getT(), j.getAlgo());
			
			outQueue.put(j.getJobNumber(), r); 
			
		} while(r.isProcessed() == false);
	}
}