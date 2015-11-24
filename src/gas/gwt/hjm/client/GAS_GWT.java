package gas.gwt.hjm.client;

import java.util.Date;

import gas.gwt.hjm.server.src.Model.Event;
import gas.gwt.hjm.server.src.Model.EventType;
import gas.gwt.hjm.server.src.Model.SchDay;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DatePicker;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GAS_GWT implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
//	private static final String SERVER_ERROR = "An error occurred while "
//			+ "attempting to contact the server. Please check your network "
//			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		final Label lDateField = new Label("Date");
		RootPanel.get("createButtonContainer").add(lDateField);
		final DatePicker tbDateField = new DatePicker();
		RootPanel.get("createButtonContainer").add(tbDateField);

		final Label lTimeField = new Label("Time");
		RootPanel.get("createButtonContainer").add(lTimeField);
		final DatePicker tbTimeField = new DatePicker();
		RootPanel.get("createButtonContainer").add(tbTimeField);

		final Label lAdNameField = new Label("Name");
		RootPanel.get("createButtonContainer").add(lAdNameField);
		final TextBox tbAdNameField = new TextBox();
		RootPanel.get("createButtonContainer").add(tbAdNameField);

		final Label lStatField = new Label("Stat");
		RootPanel.get("createButtonContainer").add(lStatField);
		final TextBox tbStatField = new TextBox();
		RootPanel.get("createButtonContainer").add(tbStatField);

		final Label lEventTypeField = new Label("EventType");
		RootPanel.get("createButtonContainer").add(lEventTypeField);
		final ListBox tbEventTypeField = new ListBox();
		tbEventTypeField.addItem("Sch.");
		tbEventTypeField.addItem("Fill");
		RootPanel.get("createButtonContainer").add(tbEventTypeField);

		final Label lStartField = new Label("Start - window");
		RootPanel.get("createButtonContainer").add(lStartField);
		final DatePicker tbStartField = new DatePicker();
		RootPanel.get("createButtonContainer").add(tbStartField);

		final Label lDurationField = new Label("duration - window");
		RootPanel.get("createButtonContainer").add(lDurationField);
		final DatePicker tbDurationField = new DatePicker();
		RootPanel.get("createButtonContainer").add(tbDurationField);

		final Label lBrkField = new Label("break - window");
		RootPanel.get("createButtonContainer").add(lBrkField);
		final TextBox tbBrkField = new TextBox();
		RootPanel.get("createButtonContainer").add(tbBrkField);

		final Label lPosField = new Label("position - window");
		RootPanel.get("createButtonContainer").add(lPosField);
		final TextBox tbPosField = new TextBox();
		RootPanel.get("createButtonContainer").add(tbPosField);

		final Label lLengthField = new Label("length - window");
		RootPanel.get("createButtonContainer").add(lLengthField);
		final DatePicker tbLengthField = new DatePicker();
		RootPanel.get("createButtonContainer").add(tbLengthField);

		final Label lActualTimeField = new Label("actual time");
		RootPanel.get("createButtonContainer").add(lActualTimeField);
		final DatePicker tbActualTimeField = new DatePicker();
		RootPanel.get("createButtonContainer").add(tbActualTimeField);

		final Label lActualLengthField = new Label("actual length");
		RootPanel.get("createButtonContainer").add(lActualLengthField);
		final TextBox tbActualLengthField = new TextBox();
		RootPanel.get("createButtonContainer").add(tbActualLengthField);

		final Label lActualPosField = new Label("actual position");
		RootPanel.get("createButtonContainer").add(lActualPosField);
		final TextBox tbActualPosField = new TextBox();
		RootPanel.get("createButtonContainer").add(tbActualPosField);
		
		final Label lfileNameField = new Label("file name");
		RootPanel.get("createButtonContainer").add(lfileNameField);
		final TextBox tbfileNameField = new TextBox();
		RootPanel.get("createButtonContainer").add(tbfileNameField);

		final Label lFileDateField = new Label("file date");
		RootPanel.get("createButtonContainer").add(lFileDateField);
		final TextBox tbFileDateField = new TextBox();
		RootPanel.get("createButtonContainer").add(tbFileDateField);

		final Label lFileZoneField = new Label("file zone");
		RootPanel.get("createButtonContainer").add(lFileZoneField);
		final TextBox tbFileZoneField = new TextBox();
		RootPanel.get("createButtonContainer").add(tbFileZoneField);
		
		final Label lFileChannelField = new Label("file network");
		RootPanel.get("createButtonContainer").add(lFileChannelField);
		final TextBox tbFileChannelField = new TextBox();
		RootPanel.get("createButtonContainer").add(tbFileChannelField);

		final Button createButton = new Button("Create");

//		RootPanel.get("createButtonContainer").add(new CreateEvent());

		// Add a handler to close the DialogBox
		createButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				Date date = null, time = null, start = null, dur = null, length = null;
				int brk = 0, pos = 0;
				String adName = null;
				//EventType eventType;
				String fileName = null, fileDate = null, fileZone = null, fileNetwork = null;

				date = tbDateField.getValue();
				time = tbTimeField.getValue();
				start = tbStartField.getValue();
				dur = tbDurationField.getValue();
				length = tbLengthField.getValue();
				brk = new Integer(tbBrkField.getValue());
				pos = new Integer(tbPosField.getValue());
				adName = tbAdNameField.getValue();
//				eventType = EventType.valueOf(EventType.getNameByValue(tbEventTypeField.getItemText(tbEventTypeField.getSelectedIndex())));
				
				fileName = tbfileNameField.getValue();
				fileDate = tbFileDateField.getValue();
				fileZone = tbFileZoneField.getValue();
				fileNetwork = tbFileChannelField.getValue();
				
				Event myEvent = new Event(date, time, start, dur, brk, pos, length, adName, EventType.SCHEDULED);
//				SchDay si = new SchDay("B1801001", "B1", "8010", "01");
				SchDay si = new SchDay(fileName, fileDate, fileZone, fileNetwork);

				greetingService.greetServer2(myEvent, si,
						new AsyncCallback<Event>() {

					@Override
					public void onSuccess(Event result) {
						final Label dialogBox = new Label("Success!!!!!!!!!!!!!");
						RootPanel.get("createButtonContainer").add(dialogBox);
					}

					@Override
					public void onFailure(Throwable caught) {
						final Label dialogBox = new Label("failed:(:(:(");
						RootPanel.get("createButtonContainer").add(dialogBox);
					}
				});
			}
		});

	}
}
