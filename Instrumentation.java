package package1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;
import java.util.ArrayList;
import java.util.stream.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.FileWriter; 
import java.io.IOException; 

public class Instrumentation {
	//Singleton Pattern
	 private static final Instrumentation ins = new Instrumentation();
	 private long activateTime;
	 private Stack<Long> startTime;
	 private Boolean activated = false;
	 private Stack<String> indents;
	 private ArrayList<String> log;
	 private String commentIndent;
	 
	 private Instrumentation() {
		 startTime = new Stack();
		 indents = new Stack();
		 log = new ArrayList();
	 }
	 public static Instrumentation getInstance() {
		 return ins;
	 }
	 
	 public void startTiming(String comment) {
		 if(activated) {
			 startTime.push(System.currentTimeMillis());
			 commentIndent = IntStream.range(1,startTime.size()).mapToObj(i -> "|	").collect(Collectors.joining());
			 indents.push(commentIndent);
			 log.add(commentIndent+"STARTTIMING: "+" "+comment);
		 }
	 }
	 
	public void stopTiming(String comment) {
		if(activated) {
			commentIndent = indents.pop();
			log.add(commentIndent + "STOPTIMING: "+ comment +" "+ (System.currentTimeMillis()-startTime.pop())+"ms");

		}
	 }
	
	public void comment(String comment) {
		if(activated)
			log.add(commentIndent + "COMMENT: "+ " "+comment);
	}
	
	public void activate(Boolean onoff) {
		//check if not activate/deactivate again
		if(activated != onoff) {
			activated = onoff;
			activateTime = System.currentTimeMillis();
		}
	}
	
	public void dump() {
		dump(null);
	}
	public void dump(String filename) {
		if(activated) {
			log.add("TOTAL TIME: "+(System.currentTimeMillis()-activateTime)+"ms");
			if(filename == null) {
				SimpleDateFormat formatter = new SimpleDateFormat("ddyyMMHHmmss");
				filename = "instrumentation"+formatter.format(new Date())+".log";
			}
			try {
				FileWriter file = new FileWriter(filename);
				for(String line : log)
					file.write(line+ "\n");
				file.close();
			} catch (IOException e) {
				System.out.println("ERROR! CANNOT OPEN FILE.");
			    e.printStackTrace();
			}
		}
		
	}

}
