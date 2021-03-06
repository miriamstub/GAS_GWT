package gas.gwt.hjm.server.src.Deserializer;

import gas.gwt.hjm.server.src.API.GeneratorAPI;
import gas.gwt.hjm.server.src.Model.*;
import gas.gwt.hjm.server.src.global.Manager;
import gas.gwt.hjm.server.src.log.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;



/**
 * <img src="file.png" />
 */

/**
 * The CCMS Deserialize object read CCMS protocol and convert to java model.
 * @author eelmisha
 *
 */
public class CCMSDeserializer implements IDeserializer{

	private static CCMSDeserializer instance = new CCMSDeserializer();

	private CCMSDeserializer(){}

	public static CCMSDeserializer getInstance(){

		return instance;
	}

	Date date, time, start, dur, length;
	int brk, pos;
	String adName;
	EventType eventType;
	Logger log = Log.getInstance();

	public boolean validAndConvertEventDataParams(String[] rowObjs){     

		if(rowObjs.length < DeserializerConfiguration.CCMS_MIN_PARAMETERS)
			return false;

		ConvertAndValidateUtils.setIProperties(SchedulerInfoType.CCMS);

		date = ConvertAndValidateUtils.getDate(rowObjs[DeserializerConfiguration.CCMS_DATE_LOCATION]);
		time = ConvertAndValidateUtils.getTime(rowObjs[DeserializerConfiguration.CCMS_TIME_LOCATION]);
		start = ConvertAndValidateUtils.getStart(rowObjs[DeserializerConfiguration.CCMS_START_LOCATION]);
		dur = ConvertAndValidateUtils.getDuration(rowObjs[DeserializerConfiguration.CCMS_DURATION_LOCATION]);
		brk = ConvertAndValidateUtils.getBrk(rowObjs[DeserializerConfiguration.CCMS_BRK_LOCATION]);
		pos = ConvertAndValidateUtils.getPos(rowObjs[DeserializerConfiguration.CCMS_POS_LOCATION]);
		length = ConvertAndValidateUtils.getLength(rowObjs[DeserializerConfiguration.CCMS_LENGTH_LOCATION]);
		adName = ConvertAndValidateUtils.getAdName(rowObjs[DeserializerConfiguration.CCMS_ADNAME_LOCATION]);

		if(rowObjs.length < DeserializerConfiguration.CCMS_MAX_PARAMETERS){
			eventType = EventType.SCHEDULED;
		}
		else{
			eventType = ConvertAndValidateUtils.getEventType(rowObjs[DeserializerConfiguration.CCMS_MAX_PARAMETERS -1]);
		}

		return validateData(rowObjs);

	}

	public boolean validateData(String[] rowObjs) {

		boolean fReturn = true;

		if (!ConvertAndValidateUtils.isValidActualTime(rowObjs[DeserializerConfiguration.CCMS_ACTUAL_TIME_LOCATION])){
			fReturn = false;
			log.error("ActualTime invalid digits");
		}
		else if(!ConvertAndValidateUtils.isValidActualLength(rowObjs[DeserializerConfiguration.CCMS_ACTUAL_LENGTH_LOCATION])){
			fReturn = false;
			log.error("ActualLength invalid digits");
		}

		else if(!ConvertAndValidateUtils.isValidActualPos(rowObjs[DeserializerConfiguration.CCMS_ACTUAL_POS_LOCATION])){
			fReturn = false;
			log.error("ActualPos invalid digits");
		}

		else if(!ConvertAndValidateUtils.isValidStatusCode(rowObjs[DeserializerConfiguration.CCMS_STATUSE_CODE_LOCATION])){
			fReturn = false;
			log.error("StatusCode invalid digits");
		}

		else if(!ConvertAndValidateUtils.notNull(date ,time,start ,dur ,brk,pos ,length,adName ,eventType)){
			fReturn = false;
			log.error("Invalid event data");
		}

		return fReturn;
	}

	public void run(){

		BufferedReader br = null;
		ConvertAndValidateUtils.setIProperties(SchedulerInfoType.CCMS);

		try {

			String sCurrentLine;
			File folder = new File(DeserializerConfiguration.FOLDER_DESERIALIZER_PATH);
			Date date = null;

			for (File fileEntry : folder.listFiles()) {                                
				String schName = fileEntry.getName().substring(0, fileEntry.getName().lastIndexOf("."));
				if(ConvertAndValidateUtils.isValidSchedulerName(schName)){
					SchDay mySchDay = new SchDay(schName, schName.substring(0, 3), schName.substring(3, 5),schName.substring(5, 8));
					try {
						//check the file name
						date = new SimpleDateFormat(DateFormats.MMdd.toString()).parse(Integer.parseInt(fileEntry.getName().substring(0, 1), 16) + fileEntry.getName().substring(1, 3));

					} catch (ParseException e) {
						e.printStackTrace();
					}

					//create the file
					Manager.getInstance().addSchedulerInfo(mySchDay);

					log.info("New CCMS Scheduled Info " + mySchDay.getSchInfoName() + " created successfully");

					br = new BufferedReader(new FileReader(fileEntry.getPath()));
					while ((sCurrentLine = br.readLine()) != null) {

						String[] rowObjs = sCurrentLine.split("\\s+");
						
                   //"REM" say that it's only title
						if(!rowObjs[0].equals("REM")){
							if(validAndConvertEventDataParams(rowObjs)){
								Event event = new Event(date, time, start, dur, brk, pos, length, adName, eventType); 
								GeneratorAPI.createEvent(event, mySchDay);
							}
							else{
								log.error("Cant deserialize event: " + date + time + start + dur + brk + pos + length + adName + eventType);
							}     

						}
					}
				}
				else
					Log.getInstance().error("Can not create CCMS Scheduler Info " + fileEntry.getName() + ". SchedulerInfo name is invalid");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}


